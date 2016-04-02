package com.paulo.fiscalizabr.connection;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.paulo.fiscalizabr.core.DadosConvenio;
import com.paulo.fiscalizabr.core.OrgaoConcedente;
import com.paulo.fiscalizabr.core.OrgaoSuperior;
import com.paulo.fiscalizabr.core.Proponente;
import com.paulo.fiscalizabr.core.Proposta;
import com.paulo.fiscalizabr.core.ValorConvenio;

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
 * Created by Paulo on 01/04/2016.
 */

// Classe para resgatas os dados do convênio baseados no id do mesmo
// http://fiscalizabr-dccufla.rhcloud.com/rest/convenios/idConvenio
public class DownloadConvenioId extends AsyncTask<String, Void, ArrayList<DadosConvenio>> {

    private Integer idConvenio;

    private Context context;

    public DownloadConvenioId(Context context, Integer idConvenio) {
        this.context = context;
        this.idConvenio = idConvenio;
    }

    // Retorna data no formato DD/MM/AAAA
    private String getReadableData(String date) {
        //2016-12-31T00:00:00-05:00
        String data = date.substring(8, 10) + "/" + date.substring(5, 7) + "/" + date.substring(0, 4);
        return data;
    }

    private ArrayList<DadosConvenio> getConvenioCompletoDataFromJson(String appJsonStr) throws JSONException {

        final String ID_CONVENIO_COMPLETO = "convenio_completo";
        final String ANO_ASSINATURA = "anoAssinatura";
        final String ANO_CONVENIO = "anoConvenio";
        final String ANO_PUBLICACAO = "anoPublicacao";
        final String CODIGO_ACAO = "codigoAcao";
        final String CODIGO_PROGRAMA = "codigoPrograma";
        final String DATA_ASSINATURA = "dataAssinatura";
        final String DATA_PUBLICACACAO = "dataPublicacao";
        final String ESTA_ASSINADO = "estaAssinado";
        final String ESTA_EMPENHADO = "estaEmpenhado";
        final String ESTA_PUBLICADO = "estaPublicado";
        final String FIM_VIGENCIA = "fimVigencia";
        final String IDENTIFICACAO_CONVENIO = "identificacaoConvenio";
        final String IDENTIFICACAO_PROPOSTA_PROGRAMA = "identificacaoPropostaPrograma";
        final String INICIO_VIGENCIA = "inicioVigencia";
        final String JUSTIFICATIVA = "justificativa";
        final String MES_ASSINATURA = "mesAssinatura";
        final String MES_PUBLICACAO = "mesPublicacao";
        final String MODALIDADE = "modalidade";
        final String NOME_PROGRAMA = "nomePrograma";
        final String NUMERO_CONVENIO = "numeroConvenio";
        final String NUMERO_INTERNO = "numeroInterno";
        final String NUMERO_PROCESSO = "numeroProcesso";
        final String OBJETO = "objeto";
        // ORGAO CONCEDENTE
        final String ORGAO_CONCEDENTE = "orgaoConcedente";
        final String CARGO_RESPONSAVEL_CONCEDENTE = "cargoResponsavelConcedente";
        final String CODIGO_ORGAO_CONCEDENTE = "codigoOrgaoConcedente";
        final String CODIGO_RESPONSAVEL_CONCEDENTE = "codigoResponsavelConcedente";
        final String NOME_ORGAO_CONCEDENTE = "nomeOrgaoConcedente";
        final String NOME_RESPONSAVEL_CONCEDENTE = "nomeResponsavelConcedente";
        // ORGAO SUPERIOR
        final String ORGAO_SUPERIOR = "orgaoSuperior";
        final String CODIGO_ORGAO_SUPERIOR = "codigoOrgaoSuperior";
        final String NOME_ORGAO_SUPERIOR = "nomeOrgaoSuperior";
        final String PERMITE_AJUSTE_NO_CRONOGRAMA_FISICO = "permiteAjusteNoCronogramaFisico";
        final String POSSUI_ADITIVO = "possuiAditivo";
        final String POSSUI_PRORROGA_DE_OFICIO = "possuiProrrogaDeOficio";
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
        final String NOME_RESPOSAVEL_PROPONENTE = "nomeResponsavelProponente";
        final String QUALIFICACAO = "qualificacao";
        final String REGIAO = "regiao";
        final String UF = "uf";
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
        final String SITUACAO_PUBLICACAO_CONVENIO = "situacaoPublicacaoConvenio";
        final String SUBSITUACAO_CONVENIO = "subsituacaoConvenio";
        final String ULTIMO_EMPENHO = "ultimoEmpenho";
        final String ULTIMO_PAGAMENTO = "ultimoPagamento";
        // VALOR CONVENIO
        final String VALOR = "valor";
        final String VALOR_CONTRAPARTIDA_FINANCEIRA_BENS_E_SERVICOS = "valorContrapartidaFinanceiraBensEServicos";
        final String VALOR_DESEMBOLSADO = "valorDesembolsado";
        final String VALOR_EMPENHADO = "valorEmpenhado";
        final String VALOR_GLOBAL = "valorGlobal";
        final String VALOR_REPASSE_UNIAO = "valorRepasseUniao";
        final String VALOR_TOTAL_CONTRAPARTIDA = "valorTotalContrapartida";

        JSONObject dataJson = new JSONObject(appJsonStr);
        JSONObject convenioObject = dataJson.getJSONObject(ID_CONVENIO_COMPLETO);

        // Esta URL só retorna um resultado, logo não precisa das iterações do laço for
        ArrayList<DadosConvenio> result = new ArrayList<DadosConvenio>();

        Integer anoAssinatura;
        Integer anoConvenio;
        Integer anoPublicacao;
        String codigoAcao;
        String codigoPrograma;
        String dataAssinatura;
        String dataPublicacao;
        boolean estaAssinado;
        boolean estaEmpenhado;
        boolean estaPublicado;
        String fimVigencia;
        Integer identificacaoConvenio;
        Integer identificacaoPropostaPrograma;
        String inicioVigencia;
        String justificativa;
        Integer mesAssinatura;
        Integer mesPublicacao;
        String modalidade;
        String nomePrograma;
        Integer numeroConvenio;
        String numeroInterno;
        String numeroProcesso;
        String objeto;
        // Orgão Concedente
        String cargoResponsavelConcedente;
        Integer codigoOrgaoConcedente;
        String codigoResponsavelConcedente;
        String nomeOrgaoConcedente;
        String nomeResponsavelConcedente;
        //ORGÃO SUPERIOR
        Integer codigoOrgaoSuperior;
        String nomeOrgaoSuperior;
        boolean permiteAjusteNoCronogramaFisico;
        boolean possuiAditivo;
        boolean possuiProrrogaDeOficio;
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
        Integer identificacaoProposta;
        Integer numeroProposta;

        Integer quantidadeAditivos;
        Integer quantidadeEmpenhos;
        Integer quantidadeProrrogas;
        String situacaoConvenio;
        String subsituacaoConvenio;
        String ultimoEmpenho;
        String ultimoPagamento;
        // VALOR
        Integer valorContrapartidaFinanceiraBensEServicos;
        Integer valorDesembolsado;
        Integer valorEmpenhado;
        Integer valorGlobal;
        Integer valorRepasseUniao;
        Integer valorTotalContrapartida;

        DadosConvenio convenio = new DadosConvenio();

        anoAssinatura = convenioObject.getInt(ANO_ASSINATURA);
        convenio.setAnoAssinatura(anoAssinatura);
        anoConvenio = convenioObject.getInt(ANO_CONVENIO);
        convenio.setAnoConvenio(anoConvenio);
        anoPublicacao = convenioObject.getInt(ANO_PUBLICACAO);
        convenio.setAnoPublicacao(anoPublicacao);
        codigoAcao = convenioObject.getString(CODIGO_ACAO);
        convenio.setCodigoAcao(codigoAcao);
        codigoPrograma = convenioObject.getString(CODIGO_PROGRAMA);
        convenio.setCodigoPrograma(codigoPrograma);
        dataAssinatura = convenioObject.getString(DATA_ASSINATURA);
        convenio.setDataAssinatura(getReadableData(dataAssinatura));
        dataPublicacao = convenioObject.getString(DATA_PUBLICACACAO);
        convenio.setDataPublicacao(getReadableData(dataPublicacao));
        estaAssinado = convenioObject.getBoolean(ESTA_ASSINADO);
        convenio.setEstaAssinado(estaAssinado);
        estaEmpenhado = convenioObject.getBoolean(ESTA_EMPENHADO);
        convenio.setEstaEmpenhado(estaEmpenhado);
        estaPublicado = convenioObject.getBoolean(ESTA_PUBLICADO);
        convenio.setEstaPublicado(estaPublicado);
        fimVigencia = convenioObject.getString(FIM_VIGENCIA);
        convenio.setFimVigencia(getReadableData(fimVigencia));
        identificacaoConvenio = convenioObject.getInt(IDENTIFICACAO_CONVENIO);
        convenio.setIdentificacaoConvenio(identificacaoConvenio);
        identificacaoPropostaPrograma = convenioObject.getInt(IDENTIFICACAO_PROPOSTA_PROGRAMA);
        convenio.setIdentificacaoPropostaPrograma(identificacaoPropostaPrograma);
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
        numeroConvenio = convenioObject.getInt(NUMERO_CONVENIO);
        convenio.setNumeroConvenio(numeroConvenio);
        numeroInterno = convenioObject.getString(NUMERO_INTERNO);
        convenio.setNumeroInterno(numeroInterno);
        numeroProcesso = convenioObject.getString(NUMERO_PROCESSO);
        convenio.setNumeroProcesso(numeroProcesso);
        objeto = convenioObject.getString(OBJETO);
        convenio.setObjeto(objeto);

        // ORGÃO CONCEDENTE

        JSONObject orgaoConcedenteObject = convenioObject.getJSONObject(ORGAO_CONCEDENTE);

            OrgaoConcedente orgaoConcedente = new OrgaoConcedente();

            cargoResponsavelConcedente = orgaoConcedenteObject.getString(CARGO_RESPONSAVEL_CONCEDENTE);
            orgaoConcedente.setCargoResponsavelConcedente(cargoResponsavelConcedente);
            codigoOrgaoConcedente = orgaoConcedenteObject.getInt(CODIGO_ORGAO_CONCEDENTE);
            orgaoConcedente.setCodigoOrgaoConcedente(codigoOrgaoConcedente);
            codigoResponsavelConcedente = orgaoConcedenteObject.getString(CODIGO_RESPONSAVEL_CONCEDENTE);
            orgaoConcedente.setCodigoResponsavelConcedente(codigoResponsavelConcedente);
            nomeOrgaoConcedente = orgaoConcedenteObject.getString(NOME_ORGAO_CONCEDENTE);
            orgaoConcedente.setNomeOrgaoConcedente(nomeOrgaoConcedente);
            nomeResponsavelConcedente = orgaoConcedenteObject.getString(NOME_RESPONSAVEL_CONCEDENTE);
            orgaoConcedente.setNomeResponsavelConcedente(nomeResponsavelConcedente);

            convenio.setOrgaoConcedente(orgaoConcedente);

        // ORGÃO SUPERIOR

        JSONObject orgaoSuperiorObject = convenioObject.getJSONObject(ORGAO_SUPERIOR);

            OrgaoSuperior orgaoSuperior = new OrgaoSuperior();

            codigoOrgaoSuperior = orgaoSuperiorObject.getInt(CODIGO_ORGAO_SUPERIOR);
            orgaoSuperior.setCodigoOrgaoSuperior(codigoOrgaoSuperior);
            nomeOrgaoSuperior = orgaoSuperiorObject.getString(NOME_ORGAO_SUPERIOR);
            orgaoSuperior.setNomeOrgaoSuperior(nomeOrgaoSuperior);

            convenio.setOrgaoSuperior(orgaoSuperior);

        permiteAjusteNoCronogramaFisico = convenioObject.getBoolean(PERMITE_AJUSTE_NO_CRONOGRAMA_FISICO);
        convenio.setPermiteAjusteNoCronogramaFisico(permiteAjusteNoCronogramaFisico);
        possuiAditivo = convenioObject.getBoolean(POSSUI_ADITIVO);
        convenio.setPossuiAditivo(possuiAditivo);
        possuiProrrogaDeOficio = convenioObject.getBoolean(POSSUI_PRORROGA_DE_OFICIO);
        convenio.setPossuiProrrogaDeOficio(possuiProrrogaDeOficio);

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
            nomeResponsavelProponente = proponenteObject.getString(NOME_RESPOSAVEL_PROPONENTE);
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
            proposta.setDataInclusaoProposta(getReadableData(dataInclusaoProposta));
            identificacaoProposta = propostaObject.getInt(IDENTIFICACAO_PROPOSTA);
            proposta.setIdentificacaoProposta(identificacaoProposta);
            numeroProposta = propostaObject.getInt(NUMERO_PROPOSTA);
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
        ultimoEmpenho = convenioObject.getString(ULTIMO_EMPENHO);
        convenio.setUltimoEmpenho(getReadableData(ultimoEmpenho));
        ultimoPagamento = convenioObject.getString(ULTIMO_PAGAMENTO);
        convenio.setUltimoPagamento(getReadableData(ultimoPagamento));

        // VALOR

        JSONObject valorObject = convenioObject.getJSONObject(VALOR);

            ValorConvenio valor = new ValorConvenio();

            valorContrapartidaFinanceiraBensEServicos = valorObject.getInt(VALOR_CONTRAPARTIDA_FINANCEIRA_BENS_E_SERVICOS);
            valor.setValorContrapartidaFinanceiraBensEServicos(valorContrapartidaFinanceiraBensEServicos);
            valorDesembolsado = valorObject.getInt(VALOR_DESEMBOLSADO);
            valor.setValorDesembolsado(valorDesembolsado);
            valorEmpenhado = valorObject.getInt(VALOR_EMPENHADO);
            valor.setValorEmpenhado(valorEmpenhado);
            valorGlobal = valorObject.getInt(VALOR_GLOBAL);
            valor.setValorGlobal(valorGlobal);
            valorRepasseUniao = valorObject.getInt(VALOR_REPASSE_UNIAO);
            valor.setValorRepasseUniao(valorRepasseUniao);
            valorTotalContrapartida = valorObject.getInt(VALOR_TOTAL_CONTRAPARTIDA);
            valor.setValorTotalContrapartida(valorTotalContrapartida);

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
            final String CONVENIO_URL = "http://fiscalizabr-dccufla.rhcloud.com/rest/convenios/" + String.valueOf(idConvenio);

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
            // Adiciona produtos no banco de dados

            //DatabaseController database = new DatabaseController(context);

            for(int i=0; i<result.size(); i++) {
                // Mostra o Convenio
                //database.addPromocao(result.get(i));

                Log.v("ANO ASSINATURA", result.get(i).getAnoAssinatura().toString());
                Log.v("ANO PUBLICAÇÃO", result.get(i).getAnoPublicacao().toString());
                Log.v("CODIGO AÇÃO", result.get(i).getCodigoAcao());
                Log.v("CODIGO PROGRAMA", result.get(i).getCodigoPrograma());
                Log.v("DATA ASSINATURA", result.get(i).getDataAssinatura());
                Log.v("DATA PUBLICAÇÃO", result.get(i).getDataPublicacao());
                Log.v("ESTA ASSINADO", String.valueOf(result.get(i).isEstaAssinado()));
                Log.v("ESTA EMPENHADO", String.valueOf(result.get(i).isEstaEmpenhado()));
                Log.v("ESTA PUBLICADO", String.valueOf(result.get(i).isEstaPublicado()));
                Log.v("FIM VIGENCIA", result.get(i).getFimVigencia());
                Log.v("IDENTIFICACAO CONVENIO", result.get(i).getIdentificacaoConvenio().toString());
                Log.v("IDENT PROP PROGRAMA", result.get(i).getIdentificacaoPropostaPrograma().toString());
                Log.v("INICIO VIGENCIA", result.get(i).getInicioVigencia());
                Log.v("JUSTIFICATIVA", result.get(i).getJustificativa());
                Log.v("MES ASSINATURA", result.get(i).getMesAssinatura().toString());
                Log.v("MES PUBLICACAO", result.get(i).getMesPublicacao().toString());
                Log.v("MODALIDADE", result.get(i).getModalidade());
                Log.v("NOME DO PROGRAMA", result.get(i).getNomePrograma());
                Log.v("NUMERO DO CONVENIO", result.get(i).getNumeroConvenio().toString());
                Log.v("NUMERO INTERNO", result.get(i).getNumeroInterno());
                Log.v("NUMERO DO PROCESSO", result.get(i).getNumeroProcesso());
                Log.v("OBJETO", result.get(i).getObjeto());

                // ORGÃO CONCEDENTE

                Log.v("ORGÃO CONCEDENTE", "1");
                Log.v("CARGO RESP. CONCEDENTE", result.get(i).getOrgaoConcedente().getCargoResponsavelConcedente());
                Log.v("CODIGO ORGAO CONCEDENTE", result.get(i).getOrgaoConcedente().getCodigoOrgaoConcedente().toString());
                Log.v("CODIGO RESP. CONCEDENTE", result.get(i).getOrgaoConcedente().getCodigoResponsavelConcedente());
                Log.v("NOME ORGAO CONCEDENTE", result.get(i).getOrgaoConcedente().getNomeOrgaoConcedente());
                Log.v("NOME RESP. CONCEDENTE", result.get(i).getOrgaoConcedente().getNomeResponsavelConcedente());

                // ORGÃO SUPERIOR

                Log.v("ORGÃO SUPERIOR", "1");
                Log.v("COD ORGAO SUPERIOR", result.get(i).getOrgaoSuperior().getCodigoOrgaoSuperior().toString());
                Log.v("NOME ORGAO SUPERIOR", result.get(i).getOrgaoSuperior().getNomeOrgaoSuperior());
                Log.v("PER AJUSTE CRON. FISICO", String.valueOf(result.get(i).isPermiteAjusteNoCronogramaFisico()));
                Log.v("POSSUI ADITIVO", String.valueOf(result.get(i).isPossuiAditivo()));
                Log.v("PRO. DE OFICIO", String.valueOf(result.get(i).isPossuiProrrogaDeOficio()));

                // PROPONENTE

                Log.v("PROPONENTE", "1");
                Log.v("BAIRRO ", result.get(i).getProponente().getBairroProponente());
                Log.v("CARGO RESP.", result.get(i).getProponente().getCargoResponsavelProponente());
                Log.v("CEP", result.get(i).getProponente().getCepProponente());
                Log.v("CODIGO RESPONSAVEL", result.get(i).getProponente().getCodigoResponsavelProponente());
                Log.v("ENDERECO", result.get(i).getProponente().getEnderecoProponente());
                Log.v("ESFERA ADMINISTRATIVA", result.get(i).getProponente().getEsferaAdministrativa());
                Log.v("IDENTIFICACAO", result.get(i).getProponente().getIdentificacaoProponente());
                Log.v("MUNICIPIO", result.get(i).getProponente().getMunicipio());
                Log.v("NOME PROPONENTE", result.get(i).getProponente().getNomeProponente());
                Log.v("NOME RESPONSAVEL", result.get(i).getProponente().getNomeResponsavelProponente());
                Log.v("QUALIFICAÇÃO", result.get(i).getProponente().getQualificacao());
                Log.v("REGIÃO", result.get(i).getProponente().getRegiao());
                Log.v("UF", result.get(i).getProponente().getUf());

                // PROPOSTA

                Log.v("PROPOSTA", "1");
                Log.v("ANO PROPOSTA", result.get(i).getProposta().getAnoProposta().toString());
                Log.v("DATA INCLUSAO", result.get(i).getProposta().getDataInclusaoProposta());
                Log.v("IDENTIFICACAO", result.get(i).getProposta().getIdentificacaoProposta().toString());
                Log.v("NUMERO PROPOSTA", result.get(i).getProposta().getNumeroProposta().toString());

                Log.v("QTD ADITIVOS", result.get(i).getQuantidadeAditivos().toString());
                Log.v("QTD EMPENHOS", result.get(i).getQuantidadeEmpenhos().toString());
                Log.v("QTD PRORROGAS", result.get(i).getQuantidadeProrrogas().toString());
                Log.v("SITUACAO CONVENIO", result.get(i).getSituacaoConvenio());
                Log.v("SUBSITUACAO CONVENIO", result.get(i).getSubsituacaoConvenio());
                Log.v("ULTIMO EMPENHO", result.get(i).getUltimoEmpenho());
                Log.v("ULTIMO PGTO", result.get(i).getUltimoPagamento());

                // VALOR
                Log.v("VALOR", "1");
                Log.v("VALOR CONTR. FIN. B&S", result.get(i).getValorConvenio().getValorContrapartidaFinanceiraBensEServicos().toString());
                Log.v("VALOR DESEMBOLSADO", result.get(i).getValorConvenio().getValorDesembolsado().toString());
                Log.v("EMPENHADO", result.get(i).getValorConvenio().getValorEmpenhado().toString());
                Log.v("GLOBAL", result.get(i).getValorConvenio().getValorGlobal().toString());
                Log.v("REPASSE UNIAO", result.get(i).getValorConvenio().getValorRepasseUniao().toString());
                Log.v("TOTAL CONTRAPARTIDA", result.get(i).getValorConvenio().getValorTotalContrapartida().toString());
            }

            //MainActivityFragment.loadPromocoesItens(); */
        }
    }

}
