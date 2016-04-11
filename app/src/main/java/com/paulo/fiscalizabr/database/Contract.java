package com.paulo.fiscalizabr.database;

import android.provider.BaseColumns;

/**
 * Created by Paulo on 08/04/2016.
 */
public class Contract {

    public Contract() { }

    public static final String COLUMN_NAME_NULLABLE = " NULL";

    public static abstract class ConvenioEntry implements BaseColumns {
        public static final String TABLE_NAME = "convenio";
        public static final String COLUMN_NAME_NUMERO_CONVENIO = "numeroConvenio";
        public static final String COLUMN_NAME_OBJETO_CONVENIO = "objetoConvenio";
        public static final String COLUMN_NAME_MUNICIPIO = "municipio";
        public static final String COLUMN_NAME_UF = "uf";
        public static final String COLUMN_NAME_NOME_PROPONENTE = "nomeProponente";
        public static final String COLUMN_NAME_VIGENCIA = "vigencia";
        public static final String COLUMN_NAME_VALOR_CONVENIO = "valorConvenio";
        public static final String COLUMN_NAME_SITUACAO_CONVENIO = "situacaoConvenio";

        public static final String COLUMN_NAME_IS_FAVORITO = "isFavorito";
        public static final String COLUMN_NAME_MES_VIGENCIA = "mesVigencia";
        public static final String COLUMN_NAME_ANO_VIGENCIA = "anoVigencia";
        public static final String COLUMN_NAME_MES_FINAL_VIGENCIA = "mesFinalVigencia";
        public static final String COLUMN_NAME_ANO_FINAL_VIGENCIA = "anoFinalVigencia";
    }

    public static abstract class ConvenioCompletoEntry implements BaseColumns {
        public static final String TABLE_NAME = "convenioCompleto";

        public static final String COLUMN_NAME_IS_FAVORITO = "isFavorito";

        public static final String COLUMN_NAME_ANO_ASSINATURA = "anoAssinatura";
        public static final String COLUMN_NAME_ANO_CONVENIO = "anoConvenio";
        public static final String COLUMN_NAME_ANO_PUBLICACAO = "anoPublicacao";
        public static final String COLUMN_NAME_DATA_ASSINATURA = "dataAssinatura";
        public static final String COLUMN_NAME_DATA_PUBLICACAO = "dataPublicacao";
        public static final String COLUMN_NAME_FIM_VIGENCIA = "fimVigencia";
        public static final String COLUMN_NAME_INICIO_VIGENCIA = "inicioVigencia";
        public static final String COLUMN_NAME_JUSTIFICATIVA = "justificativa";
        public static final String COLUMN_NAME_MES_ASSINATURA = "mesAssinatura";
        public static final String COLUMN_NAME_MES_PUBLICACAO = "mesPublicacao";
        public static final String COLUMN_NAME_MODALIDADE = "modalidade";
        public static final String COLUMN_NAME_NOME_PROGRAMA = "nomePrograma";
        public static final String COLUMN_NAME_NUMERO_CONVENIO = "numeroConvenio";
        public static final String COLUMN_NAME_NUMERO_PROCESSO = "numeroProcesso";
        public static final String COLUMN_NAME_OBJETO = "objeto";
        public static final String COLUMN_NAME_SITUACAO_PUBLICACAO_CONVENIO = "situacaoPublicacaoConvenio";

        public static final String COLUMN_NAME_CARGO_RESPONSAVEL_CONCEDENTE = "cargoResponsavelConcedente";
        public static final String COLUMN_NAME_NOME_ORGAO_CONCEDENTE = "nomeOrgaoConcedente";
        public static final String COLUMN_NAME_NOME_RESPONSAVEL_CONCEDENTE = "nomeResponsavelConcedente";

        public static final String COLUMN_NAME_CODIGO_ORGAO_SUPERIOR = "codigoOrgaoSuperior";
        public static final String COLUMN_NAME_NOME_ORGAO_SUPERIOR = "nomeOrgaoSuperior";

        public static final String COLUMN_NAME_BAIRRO_PROPONENTE = "bairroProponente";
        public static final String COLUMN_NAME_CARGO_RESPONSAVEL_PROPONENTE = "cargoResponsavelProponente";
        public static final String COLUMN_NAME_CEP_PROPONENTE = "cepProponente";
        public static final String COLUMN_NAME_CODIGO_RESPONSAVEL_PROPONENTE = "codigoResponsavelProponente";
        public static final String COLUMN_NAME_ENDERECO_PROPONENTE = "enderecoProponente";
        public static final String COLUMN_NAME_ESFERA_ADMINISTRATIVA = "esferaAdministrativa";
        public static final String COLUMN_NAME_IDENTIFICACAO_PROPONENTE = "identificacaoProponente";
        public static final String COLUMN_NAME_MUNICIPIO = "municipio";
        public static final String COLUMN_NAME_NOME_PROPONENTE = "nomeProponente";
        public static final String COLUMN_NAME_NOME_RESPONSAVEL_PROPONENTE = "nomeResponsavelProponente";
        public static final String COLUMN_NAME_QUALIFICACAO = "qualificacao";
        public static final String COLUMN_NAME_REGIAO = "regiao";
        public static final String COLUMN_NAME_UF = "uf";

        public static final String COLUMN_NAME_ANO_PROPOSTA = "anoProposta";
        public static final String COLUMN_NAME_DATA_INCLUSAO_PROPOSTA = "dataInclusaoProposta";
        public static final String COLUMN_NAME_IDENTIFICACAO_PROPOSTA = "identificacaoProposta";
        public static final String COLUMN_NAME_NUMERO_PROPOSTA = "numeroProposta";

        public static final String COLUMN_NAME_QTD_ADITIVOS = "quantidadeAditivos";
        public static final String COLUMN_NAME_QTD_EMPENHOS = "quantidadeEmpenhos";
        public static final String COLUMN_NAME_QTD_PRORROGAS = "quantidadeProrrogas";
        public static final String COLUMN_NAME_SITUACAO_CONVENIO = "situacaoConvenio";
        public static final String COLUMN_NAME_SUBSITUACAO_CONVENIO = "subsituacaoConvenio";
        public static final String COLUMN_NAME_ULTIMO_PAGAMENTO = "ultimoPagamento";

        public static final String COLUMN_NAME_VALOR_CONTRAPARTIDA_FINANCEIRA_BENS_E_SERVICOS = "valorContrapartidaFinanceiraBensEServicos";
        public static final String COLUMN_NAME_VALOR_DESEMBOLSADO = "valorDesembolsado";
        public static final String COLUMN_NAME_VALOR_EMPENHADO = "valorEmpenhado";
        public static final String COLUMN_NAME_VALOR_GLOBAL = "valorGlobal";
        public static final String COLUMN_NAME_VALOR_REPASSE_UNIAO = "valorRepasseUniao";
        public static final String COLUMN_NAME_VALOR_TOTAL_CONTRAPARTIDA = "valorTotalContrapartida";
        public static final String COLUMN_NAME_VALOR_CONTRAPARTIDA_FINANCEIRA = "valorContrapartidaFinanceira";
    }
}
