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
        public static final String COLUMN_NAME_IS_FAVORITO = "isFavorito";
        public static final String COLUMN_NAME_MUNICIPIO = "municipio";
        public static final String COLUMN_NAME_UF = "uf";
        public static final String COLUMN_NAME_NOME_PROPONENTE = "nomeProponente";
        public static final String COLUMN_NAME_VIGENCIA = "vigencia";
        public static final String COLUMN_NAME_VALOR_CONVENIO = "valorConvenio";
    }

    public static abstract class ConvenioCompletoEntry implements BaseColumns {
        public static final String TABLE_NAME = "convenioCompleto";
        public static final String COLUMN_NAME_ANO_ASSINATURA = "anoAssinatura";
        public static final String COLUMN_NAME_ANO_CONVENIO = "anoConvenio";
        public static final String COLUMN_NAME_ANO_PUBLICACAO = "anoPublicacao";
        public static final String COLUMN_NAME_DATA_ASSINATURA = "dataAssinatura";
        public static final String COLUMN_NAME_DATA_PUBLICACAO = "dataPublicacao";
        public static final String COLUMN_NAME_ESTA_ASSINADO = "estaAssinado";
        public static final String COLUMN_NAME_ESTA_PUBLICADO = "estaPublicado";
        public static final String COLUMN_NAME_ESTA_EMPENHADO = "estaEmpenhado";
        public static final String COLUMN_NAME_FIM_VIGENCIA = "fimVigencia";
        public static final String COLUMN_NAME_INICIO_VIGENCIA = "inicioVigencia";
        public static final String COLUMN_NAME_JUSTIFICATIVA = "justificativa";
        public static final String COLUMN_NAME_MODALIDADE = "modalidade";
        public static final String COLUMN_NAME_NOME_PROGRAMA = "nomePrograma";
        public static final String COLUMN_NAME_OBJETO = "objeto";
        public static final String COLUMN_NAME_NUMERO_CONVENIO = "numeroConvenio";

        public static final String COLUMN_NAME_CARGO_RESPONSAVEL_CONCEDENTE = "cargoResponsavelConcedente";
        public static final String COLUMN_NAME_PERMITE_AJUSTE_CRONOGRAMA_FISICO = "permiteAjusteCronogramaFisico";
        public static final String COLUMN_NAME_POSSUI_ADITIVO = "possuiAditivo";
        public static final String COLUMN_NAME_POSSUI_PROGRAMA_DE_OFICIO = "possuiProgramaDoOficio";

        public static final String COLUMN_NAME_MUNICIPIO = "municipio";
        public static final String COLUMN_NAME_NOME_PROPONENTE = "nomeProponente";
        public static final String COLUMN_NAME_NOME_RESPONSAVEL_PROPONENTE = "nomeResponsavelProponente";
        public static final String COLUMN_NAME_QUALIFICACAO = "qualificacao";
        public static final String COLUMN_NAME_REGIAO = "regiao";
        public static final String COLUMN_NAME_UF = "uf";

        public static final String COLUMN_NAME_ANO_PROPOSTA = "anoProposta";
        public static final String COLUMN_NAME_DATA_INICIO_PROPOSTA = "dataInicioProposta";
        public static final String COLUMN_NAME_SITUACAO_CONVENIO = "situacaoConvenio";
        public static final String COLUMN_NAME_SITUACAO_PUBLICACAO_CONVENIO = "situacaoPublicacaoConvenio";
        public static final String COLUMN_NAME_ULTIMO_EMPENHO = "ultimoEmpenho";
        public static final String COLUMN_NAME_ULTIMO_PAGAMENTO = "ultimoPagamento";

        public static final String COLUMN_NAME_VALOR_CONTRAPARTIDA_FINANCEIRA = "valorContrapartidaFinanceira";
        public static final String COLUMN_NAME_VALOR_CONTRAPARTIDA_FINANCEIRA_BENS_E_SERVICOS = "valorContrapartidaFinanceiraBensEServicos";
        public static final String COLUMN_NAME_VALOR_DESEMBOLSADO = "valorDesembolsado";
        public static final String COLUMN_NAME_VALOR_GLOBAL = "valorGlobal";
        public static final String COLUMN_NAME_VALOR_REPASSE_UNIAO = "valorRepasseUniao";
        public static final String COLUMN_NAME_VALOR_TOTAL_CONTRAPARTIDA = "valorTotalContrapartida";

    }
}
