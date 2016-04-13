package com.paulo.fiscalizabr.connection;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import com.paulo.fiscalizabr.core.DadosConvenio;
import com.paulo.fiscalizabr.core.OrgaoConcedente;
import com.paulo.fiscalizabr.core.OrgaoSuperior;
import com.paulo.fiscalizabr.core.Proponente;
import com.paulo.fiscalizabr.core.Proposta;
import com.paulo.fiscalizabr.core.ValorConvenio;
import com.paulo.fiscalizabr.database.DatabaseController;

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
 * Created by Paulo on 01/04/2016.
 */

// Classe para resgatar os dados do convênio baseados no id do mesmo
public class DownloadConvenioId extends AsyncTask<String, Void, ArrayList<DadosConvenio>> {

    public String idConvenio;

    private Context context;

    public DownloadConvenioId(Context context, String idConvenio) {
        this.context = context;
        this.idConvenio = idConvenio;
    }

    // Retorna data no formato DD/MM/AAAA
    private String getReadableData(String date) {
        String data = date.substring(8, 10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4);
        return data;
    }

    private ArrayList<DadosConvenio> getConvenioCompletoDataFromJson(String appJsonStr) throws JSONException {

        final String ID_CONVENIO_COMPLETO = "convenio_completo";
        final String ANO_ASSINATURA = "anoAssinatura";
        final String ANO_CONVENIO = "anoConvenio";
        final String ANO_PUBLICACAO = "anoPublicacao";
        final String DATA_ASSINATURA = "dataAssinatura";
        final String DATA_PUBLICACACAO = "dataPublicacao";
        final String FIM_VIGENCIA = "fimVigencia";
        final String INICIO_VIGENCIA = "inicioVigencia";
        final String JUSTIFICATIVA = "justificativa";
        final String MES_ASSINATURA = "mesAssinatura";
        final String MES_PUBLICACAO = "mesPublicacao";
        final String MODALIDADE = "modalidade";
        final String NOME_PROGRAMA = "nomePrograma";
        final String NUMERO_CONVENIO = "numeroConvenio";
        final String NUMERO_PROCESSO = "numeroProcesso";
        final String OBJETO = "objeto";
        final String SITUACAO_PUBLICACAO_CONVENIO = "situacaoPublicacaoConvenio";
        // ORGAO CONCEDENTE
        final String ORGAO_CONCEDENTE = "orgaoConcedente";
        final String CARGO_RESPONSAVEL_CONCEDENTE = "cargoResponsavelConcedente";
        final String NOME_ORGAO_CONCEDENTE = "nomeOrgaoConcedente";
        final String NOME_RESPONSAVEL_CONCEDENTE = "nomeResponsavelConcedente";
        // ORGAO SUPERIOR
        final String ORGAO_SUPERIOR = "orgaoSuperior";
        final String CODIGO_ORGAO_SUPERIOR = "codigoOrgaoSuperior";
        final String NOME_ORGAO_SUPERIOR = "nomeOrgaoSuperior";
        // PROPONENTE
        final String PROPONENTE = "proponente";
        final String BAIRRO_PROPONENTE = "bairroProponente";
        final String CARGO_RESPONSAVEL_PROPONENTE = "cargoResponsavelProponente";
        final String CEP_PROPONENTE = "cepProponente";
        final String CODIGO_RESPONSAVEL_PROPONENTE = "codigoResponsavelProponente";
        final String ENDERECO_PROPONENTE = "enderecoProponente";
        final String ESFERA_ADMINISTRATIVA = "esferaAdministrativa";
        final String IDENTIFICACAO_PROPONENTE = "identificacaoProponente";
        final String MUNICIPIO = "municipio";
        final String NOME_PROPONENTE = "nomeProponente";
        final String QUALIFICACAO = "qualificacao";
        final String REGIAO = "regiao";
        final String UF = "uf";
        final String NOME_RESPONSAVEL_PROPONENTE = "nomeResponsavelProponente";
        // PROPOSTA
        final String PROPOSTA = "proposta";
        final String ANO_PROPOSTA = "anoProposta";
        final String DATA_INCLUSAO_PROPOSTA = "dataInclusaoProposta";
        final String IDENTIFICACAO_PROPOSTA = "identificacaoProposta";
        final String NUMERO_PROPOSTA = "numeroProposta";
        // ACABA LISTA DE PROPOSTA
        final String QUANTIDADE_ADITIVOS = "quantidadeAditivos";
        final String QUANTIDADE_EMPENHOS = "quantidadeEmpenhos";
        final String QUANTIDADE_PRORROGAS = "quantidadeProrrogas";
        final String SITUACAO_CONVENIO = "situacaoConvenio";
        final String SUBSITUACAO_CONVENIO = "subsituacaoConvenio";
        final String ULTIMO_PAGAMENTO = "ultimoPagamento";
        // VALOR CONVENIO
        final String VALOR = "valor";
        final String VALOR_CONTRAPARTIDA_FINANCEIRA_BENS_E_SERVICOS = "valorContrapartidaFinanceiraBensEServicos";
        final String VALOR_DESEMBOLSADO = "valorDesembolsado";
        final String VALOR_EMPENHADO = "valorEmpenhado";
        final String VALOR_GLOBAL = "valorGlobal";
        final String VALOR_REPASSE_UNIAO = "valorRepasseUniao";
        final String VALOR_TOTAL_CONTRAPARTIDA = "valorTotalContrapartida";
        final String VALOR_CONTRAPARTIDA_FINANCEIRA = "valorContrapartidaFinanceira";

        JSONObject dataJson = new JSONObject(appJsonStr);
        JSONObject convenioObject = dataJson.getJSONObject(ID_CONVENIO_COMPLETO);

        // Esta URL só retorna um resultado, logo não precisa das iterações do laço for
        ArrayList<DadosConvenio> result = new ArrayList<DadosConvenio>();

        Integer anoAssinatura;
        Integer anoConvenio;
        Integer anoPublicacao;
        String dataAssinatura;
        String dataPublicacao;
        String fimVigencia;
        String inicioVigencia;
        String justificativa;
        Integer mesAssinatura;
        Integer mesPublicacao;
        String modalidade;
        String nomePrograma;
        String numeroConvenio;
        String numeroProcesso;
        String objeto;
        String situacaoPublicacaoConvenio;
        // Orgão Concedente
        String cargoResponsavelConcedente;
        String nomeOrgaoConcedente;
        String nomeResponsavelConcedente;
        //ORGÃO SUPERIOR
        String codigoOrgaoSuperior;
        String nomeOrgaoSuperior;
        // PROPONENTE
        String bairroProponente;
        String cargoResponsavelProponente;
        String cepProponente;
        String codigoResponsavelProponente;
        String enderecoProponente;
        String esferaAdministrativa;
        String identificacaoProponente;
        String municipio;
        String nomeProponente;
        String nomeResponsavelProponente;
        String qualificacao;
        String regiao;
        String uf;
        // PROPOSTA
        Integer anoProposta;
        String dataInclusaoProposta;
        String identificacaoProposta;
        String numeroProposta;

        Integer quantidadeAditivos;
        Integer quantidadeEmpenhos;
        Integer quantidadeProrrogas;
        String situacaoConvenio;
        String subsituacaoConvenio;
        String ultimoPagamento;
        // VALOR
        String valorContrapartidaFinanceiraBensEServicos;
        String valorDesembolsado;
        String valorEmpenhado;
        String valorGlobal;
        String valorRepasseUniao;
        String valorTotalContrapartida;
        String valorContrapartidaFinanceira;

        DadosConvenio convenio = new DadosConvenio();

        anoAssinatura = convenioObject.getInt(ANO_ASSINATURA);
        convenio.setAnoAssinatura(anoAssinatura);
        anoConvenio = convenioObject.getInt(ANO_CONVENIO);
        convenio.setAnoConvenio(anoConvenio);
        anoPublicacao = convenioObject.getInt(ANO_PUBLICACAO);
        convenio.setAnoPublicacao(anoPublicacao);
        dataAssinatura = convenioObject.getString(DATA_ASSINATURA);
        convenio.setDataAssinatura(getReadableData(dataAssinatura));
        dataPublicacao = convenioObject.getString(DATA_PUBLICACACAO);
        convenio.setDataPublicacao(getReadableData(dataPublicacao));
        fimVigencia = convenioObject.getString(FIM_VIGENCIA);
        convenio.setFimVigencia(getReadableData(fimVigencia));
        inicioVigencia = convenioObject.getString(INICIO_VIGENCIA);
        convenio.setInicioVigencia(getReadableData(inicioVigencia));
        justificativa = convenioObject.getString(JUSTIFICATIVA);
        convenio.setJustificativa(justificativa);
        mesAssinatura = convenioObject.getInt(MES_ASSINATURA);
        convenio.setMesAssinatura(mesAssinatura);
        mesPublicacao = convenioObject.getInt(MES_PUBLICACAO);
        convenio.setMesPublicacao(mesPublicacao);
        modalidade = convenioObject.getString(MODALIDADE);
        convenio.setModalidade(modalidade);
        nomePrograma = convenioObject.getString(NOME_PROGRAMA);
        convenio.setNomePrograma(nomePrograma);
        numeroConvenio = convenioObject.getString(NUMERO_CONVENIO);
        convenio.setNumeroConvenio(numeroConvenio);
        numeroProcesso = convenioObject.getString(NUMERO_PROCESSO);
        convenio.setNumeroProcesso(numeroProcesso);
        objeto = convenioObject.getString(OBJETO);
        convenio.setObjeto(objeto);
        situacaoPublicacaoConvenio = convenioObject.getString(SITUACAO_PUBLICACAO_CONVENIO);
        convenio.setSituacaoPublicacaoConvenio(situacaoPublicacaoConvenio);

        // ORGÃO CONCEDENTE

        JSONObject orgaoConcedenteObject = convenioObject.getJSONObject(ORGAO_CONCEDENTE);

            OrgaoConcedente orgaoConcedente = new OrgaoConcedente();

            cargoResponsavelConcedente = orgaoConcedenteObject.getString(CARGO_RESPONSAVEL_CONCEDENTE);
            orgaoConcedente.setCargoResponsavelConcedente(cargoResponsavelConcedente);
            nomeOrgaoConcedente = orgaoConcedenteObject.getString(NOME_ORGAO_CONCEDENTE);
            orgaoConcedente.setNomeOrgaoConcedente(nomeOrgaoConcedente);
            nomeResponsavelConcedente = orgaoConcedenteObject.getString(NOME_RESPONSAVEL_CONCEDENTE);
            orgaoConcedente.setNomeResponsavelConcedente(nomeResponsavelConcedente);

            convenio.setOrgaoConcedente(orgaoConcedente);

        // ORGÃO SUPERIOR

        JSONObject orgaoSuperiorObject = convenioObject.getJSONObject(ORGAO_SUPERIOR);

            OrgaoSuperior orgaoSuperior = new OrgaoSuperior();

            codigoOrgaoSuperior = orgaoSuperiorObject.getString(CODIGO_ORGAO_SUPERIOR);
            orgaoSuperior.setCodigoOrgaoSuperior(Integer.parseInt(codigoOrgaoSuperior));
            nomeOrgaoSuperior = orgaoSuperiorObject.getString(NOME_ORGAO_SUPERIOR);
            orgaoSuperior.setNomeOrgaoSuperior(nomeOrgaoSuperior);

        convenio.setOrgaoSuperior(orgaoSuperior);

        // PROPONENTE

        JSONObject proponenteObject = convenioObject.getJSONObject(PROPONENTE);

            Proponente proponente = new Proponente();

            bairroProponente = proponenteObject.getString(BAIRRO_PROPONENTE);
            proponente.setBairroProponente(bairroProponente);
            cargoResponsavelProponente = proponenteObject.getString(CARGO_RESPONSAVEL_PROPONENTE);
            proponente.setCargoResponsavelProponente(cargoResponsavelProponente);
            cepProponente = proponenteObject.getString(CEP_PROPONENTE);
            proponente.setCepProponente(cepProponente);
            codigoResponsavelProponente = proponenteObject.getString(CODIGO_RESPONSAVEL_PROPONENTE);
            proponente.setCodigoResponsavelProponente(codigoResponsavelProponente);
            enderecoProponente = proponenteObject.getString(ENDERECO_PROPONENTE);
            proponente.setEnderecoProponente(enderecoProponente);
            esferaAdministrativa = proponenteObject.getString(ESFERA_ADMINISTRATIVA);
            proponente.setEsferaAdministrativa(esferaAdministrativa);
            identificacaoProponente = proponenteObject.getString(IDENTIFICACAO_PROPONENTE);
            proponente.setIdentificacaoProponente(identificacaoProponente);
            municipio = proponenteObject.getString(MUNICIPIO);
            proponente.setMunicipio(municipio);
            nomeProponente = proponenteObject.getString(NOME_PROPONENTE);
            proponente.setNomeProponente(nomeProponente);
            nomeResponsavelProponente = proponenteObject.getString(NOME_RESPONSAVEL_PROPONENTE);
            proponente.setNomeResponsavelProponente(nomeResponsavelProponente);
            qualificacao = proponenteObject.getString(QUALIFICACAO);
            proponente.setQualificacao(qualificacao);
            regiao = proponenteObject.getString(REGIAO);
            proponente.setRegiao(regiao);
            uf = proponenteObject.getString(UF);
            proponente.setUf(uf);

            convenio.setProponente(proponente);

        // PROPOSTA

        JSONObject propostaObject = convenioObject.getJSONObject(PROPOSTA);

            Proposta proposta = new Proposta();

            anoProposta = propostaObject.getInt(ANO_PROPOSTA);
        proposta.setAnoProposta(anoProposta);
        dataInclusaoProposta = propostaObject.getString(DATA_INCLUSAO_PROPOSTA);
            proposta.setDataInclusao(getReadableData(dataInclusaoProposta));
            identificacaoProposta = propostaObject.getString(IDENTIFICACAO_PROPOSTA);
            proposta.setIdentificacaoProposta(identificacaoProposta);
            numeroProposta = propostaObject.getString(NUMERO_PROPOSTA);
            proposta.setNumeroProposta(numeroProposta);

        convenio.setProposta(proposta);

        quantidadeAditivos = convenioObject.getInt(QUANTIDADE_ADITIVOS);
        convenio.setQuantidadeAditivos(quantidadeAditivos);
        quantidadeEmpenhos = convenioObject.getInt(QUANTIDADE_EMPENHOS);
        convenio.setQuantidadeEmpenhos(quantidadeEmpenhos);
        quantidadeProrrogas = convenioObject.getInt(QUANTIDADE_PRORROGAS);
        convenio.setQuantidadeProrrogas(quantidadeProrrogas);
        situacaoConvenio = convenioObject.getString(SITUACAO_CONVENIO);
        convenio.setSituacaoConvenio(situacaoConvenio);
        subsituacaoConvenio = convenioObject.getString(SUBSITUACAO_CONVENIO);
        convenio.setSubsituacaoConvenio(subsituacaoConvenio);
        ultimoPagamento = convenioObject.getString(ULTIMO_PAGAMENTO);
        convenio.setUltimoPagamento(getReadableData(ultimoPagamento));

        // VALOR

        JSONObject valorObject = convenioObject.getJSONObject(VALOR);

            ValorConvenio valor = new ValorConvenio();

            valorContrapartidaFinanceiraBensEServicos = valorObject.getString(VALOR_CONTRAPARTIDA_FINANCEIRA_BENS_E_SERVICOS);
            valor.setValorContrapartidaFinanceiraBensEServicos(valorContrapartidaFinanceiraBensEServicos);
            valorDesembolsado = valorObject.getString(VALOR_DESEMBOLSADO);
            valor.setValorDesembolsado(valorDesembolsado);
            valorEmpenhado = valorObject.getString(VALOR_EMPENHADO);
            valor.setValorEmpenhado(valorEmpenhado);
            valorGlobal = valorObject.getString(VALOR_GLOBAL);
            valor.setValorGlobal(valorGlobal);
            valorRepasseUniao = valorObject.getString(VALOR_REPASSE_UNIAO);
            valor.setValorRepasseUniao(valorRepasseUniao);
            valorTotalContrapartida = valorObject.getString(VALOR_TOTAL_CONTRAPARTIDA);
            valor.setValorTotalContrapartida(valorTotalContrapartida);
            valorContrapartidaFinanceira = valorObject.getString(VALOR_CONTRAPARTIDA_FINANCEIRA);
            valor.setValorContrapartidaFinanceira(valorContrapartidaFinanceira);

            convenio.setValorConvenio(valor);

        result.add(convenio);

        return result;
    }

    @Override
    protected ArrayList<DadosConvenio> doInBackground(String... params) {

        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String dataJsonStr = "{\"convenio_completo\":";

        try {
            final String CONVENIO_URL = "http://fiscalizabr-dccufla.rhcloud.com/rest/convenios/" + idConvenio;

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
                    return getConvenioCompletoDataFromJson(dataJsonStr);
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
    protected void onPostExecute(ArrayList<DadosConvenio> result) {
        if(!result.isEmpty()) {
            DatabaseController database = new DatabaseController(context);
            for(int i=0; i<result.size(); i++) {
                DadosConvenio resultado = (DadosConvenio) result.get(i);
                database.addConvenioCompleto(resultado);
            }
        }
    }

}
