package com.paulo.fiscalizabr.enums;

import java.io.Serializable;

/**
 * Created by Paulo on 08/04/2016.
 */
public enum SituacaoConvenio implements Serializable {

    AGUARDANDO_PRESTACAO_CONTAS("Aguardando Prestação de Contas"),
    EM_EXECUCAO("Em execução"),
    ASSINADO("Assinado"),
    PRESTACAO_CONTAS_EM_ANALISE("Prestação de Contas enviada para Análise"),
    PRESTACAO_CONTAS_REJEITADA("Prestação de Contas Rejeitada"),
    PRESTACAO_CONTAS_EM_COMPLEMENTACAO("Prestação de Contas em Complementação"),
    PRESTACAO_CONTAS_APROVADA("Prestação de Contas Aprovada"),
    PLANO_TRABALHO_COMPLEMENTADO_EM_ANALISE("Proposta Aprovada e Plano de Trabalho Complementado enviado para Análise"),
    PLANO_TRABALHO_EM_COMPLEMENTACAO("Proposta Aprovada e Plano de Trabalho em Complementação"),
    PROPOSTA_EM_ANALISE("Proposta em Análise"),
    PLANO_TRABALHO_EM_ANALISE("Plano de Trabalho em Análise");

    private final String descricao;

    private SituacaoConvenio(String desc) {
        this.descricao = desc;
    }

    public String getDescricao() {
        return this.descricao;
    }

}
