package com.paulo.fiscalizabr.connection;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.paulo.fiscalizabr.ConveniosPesquisaDetalhada;
import com.paulo.fiscalizabr.core.Convenio;
import com.paulo.fiscalizabr.database.DatabaseController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by Paulo on 04/04/2016.
 */

// Baixa os convenios baseados nos parametros passados
public class DownloadConveniosParametros extends AsyncTask<String, Void, ArrayList<Convenio>> {

    private Context context;
    private String municipio;
    private String uf;
    private String valorMinimo;
    private String valorMaximo;
    private String inicioVigencia;
    private String fimVigencia;
    private String situacaoConvenio;
    private boolean isValor;
    private boolean isVigencia;
    private boolean isSituacao;

    public DownloadConveniosParametros(Context context, String municipio, String uf, String valorMinimo, String valorMaximo, String inicioVigencia, String fimVigencia, String situacaoConveio, boolean isValor, boolean isVigencia, boolean isSituacao) {
        this.municipio = municipio;
        this.uf = uf;
        this.valorMinimo = valorMinimo;
        this.valorMaximo = valorMaximo;
        this.inicioVigencia = inicioVigencia;
        this.fimVigencia = fimVigencia;
        this.situacaoConvenio = situacaoConveio;
        this.context = context;
        this.isValor = isValor;
        this.isVigencia = isVigencia;
        this.isSituacao = isSituacao;
    }

    // Retorna data no formato DD/MM/AAAA
    private String getReadableData(String date) {
        String data = date.substring(8, 10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4);
        return data;
    }

    private ArrayList<Convenio> getConveniosParametrosDataFromJson(String appJsonStr) throws JSONException {

        final String ID_CONVENIO = "convenios";
        final String NUMERO_CONVENIO = "numeroConvenio";
        final String OBJETO = "objeto";
        final String INICIO_VIGENCIA = "inicioVigencia";
        final String FIM_VIGENCIA = "fimVigencia";
        final String MUNICIPIO = "municipio";
        final String UF = "uf";
        final String NOME_PROPONENTE = "nomeProponente";
        final String VALOR_GLOBAL = "valorGlobal";
        final String SITUACAO_CONVENIO = "situacaoConvenio";

        JSONObject dataJson = new JSONObject(appJsonStr);
        JSONArray arrayConvenios = dataJson.getJSONArray(ID_CONVENIO);

        ArrayList<Convenio> result = new ArrayList<Convenio>();

        for(int i=0; i < arrayConvenios.length(); i++) {
            String numeroConvenio;
            String objeto;
            String inicioVigencia;
            String fimVigencia;
            String municipio;
            String uf;
            String nomeProponente;
            String valorGlobal;
            String situacaoConvenio;

            Convenio convenio = new Convenio();

            JSONObject convenioObject = arrayConvenios.getJSONObject(i);

            numeroConvenio = convenioObject.getString(NUMERO_CONVENIO);
            convenio.setNumeroConvenio(numeroConvenio);

            objeto = convenioObject.getString(OBJETO);
            convenio.setObjetoConvenio(objeto.toUpperCase());

            inicioVigencia = getReadableData(convenioObject.getString(INICIO_VIGENCIA));
            fimVigencia = getReadableData(convenioObject.getString(FIM_VIGENCIA));
            convenio.setVigencia(inicioVigencia + " a " + fimVigencia);
            // Inicio Vigencia
            convenio.setMesVigencia(Integer.parseInt(inicioVigencia.substring(3, 5)));
            convenio.setAnoVigencia(Integer.parseInt(inicioVigencia.substring(6)));

            convenio.setMesFinalVigencia(Integer.parseInt(fimVigencia.substring(3, 5)));
            convenio.setAnoFinalVigencia(Integer.parseInt(fimVigencia.substring(6)));

            municipio = convenioObject.getString(MUNICIPIO);
            convenio.setMunicipio(municipio);

            uf = convenioObject.getString(UF);
            convenio.setUf(uf);

            nomeProponente = convenioObject.getString(NOME_PROPONENTE);
            convenio.setNomeProponente(nomeProponente);

            valorGlobal = convenioObject.getString(VALOR_GLOBAL);
            convenio.setValorConvenio(valorGlobal);

            situacaoConvenio = convenioObject.getString(SITUACAO_CONVENIO);

            if(situacaoConvenio.equals("AGUARDANDO_PRESTACAO_CONTAS")) {
                convenio.setSituacaoConvenio(1);
            } else if(situacaoConvenio.equals("EM_EXECUCAO")) {
                convenio.setSituacaoConvenio(2);
            } else if(situacaoConvenio.equals("ASSINADO")) {
                convenio.setSituacaoConvenio(3);
            } else if(situacaoConvenio.equals("PRESTACAO_CONTAS_EM_ANALISE")) {
                convenio.setSituacaoConvenio(4);
            } else if(situacaoConvenio.equals("PRESTACAO_CONTAS_REJEITADA")) {
                convenio.setSituacaoConvenio(5);
            } else if(situacaoConvenio.equals("PRESTACAO_CONTAS_EM_COMPLEMENTACAO")) {
                convenio.setSituacaoConvenio(6);
            } else if(situacaoConvenio.equals("PRESTACAO_CONTAS_APROVADA")) {
                convenio.setSituacaoConvenio(7);
            } else if(situacaoConvenio.equals("PLANO_TRABALHO_COMPLEMENTADO_EM_ANALISE")) {
                convenio.setSituacaoConvenio(8);
            } else if(situacaoConvenio.equals("PLANO_TRABALHO_EM_COMPLEMENTACAO")) {
                convenio.setSituacaoConvenio(9);
            } else if(situacaoConvenio.equals("PROPOSTA_EM_ANALISE")) {
                convenio.setSituacaoConvenio(10);
            } else if(situacaoConvenio.equals("PLANO_TRABALHO_EM_ANALISE")) {
                convenio.setSituacaoConvenio(11);
            }

            result.add(convenio);
        }

        return result;
    }

    @Override
    protected ArrayList<Convenio> doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String dataJsonStr = "{\"convenios\":";

        try {

            String CONVENIO_URL = "http://fiscalizabr-dccufla.rhcloud.com/rest/convenios?" +
                    "mun=" + municipio +
                    "&uf=" + uf;

            if(isVigencia == true) {
                    CONVENIO_URL += "&iniPer=" + inicioVigencia +
                            "&fimPer=" + fimVigencia;
            }
            if(isValor == true) {
                    CONVENIO_URL += "&minV=" + valorMinimo +
                            "&maxV=" + valorMaximo;
            } if(isSituacao == true) {
                    CONVENIO_URL += "&sit=" + situacaoConvenio;
            }

            Uri builtUri = Uri.parse(CONVENIO_URL);

            URL url = new URL(builtUri.toString());

            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            } else {
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
            }

            if (buffer.length() == 0) {
                return null;
            } else {
                dataJsonStr += buffer.toString() + "}";
                try {
                    return getConveniosParametrosDataFromJson(dataJsonStr);
                } catch(JSONException e) {
                    e.printStackTrace();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Convenio> result) {
        if(result != null) {
            if (!result.isEmpty()) {
                DatabaseController database = new DatabaseController(context);
                ConveniosPesquisaDetalhada.listaConvenios.clear();

                for (int i = 0; i < result.size(); i++) {
                    Convenio resultado = result.get(i);

                    resultado.setIsFavorito(database.isFavorito(resultado.getNumeroConvenio()));
                    ConveniosPesquisaDetalhada.listaConvenios.add(result.get(i));
                    ConveniosPesquisaDetalhada.setUpConvenios();
                }
            } else {
                ConveniosPesquisaDetalhada.listaConvenios.clear();
                ConveniosPesquisaDetalhada.setUpConvenios();
            }
        } else {
            ConveniosPesquisaDetalhada.listaConvenios.clear();
            ConveniosPesquisaDetalhada.setUpConvenios();
        }
    }

}
