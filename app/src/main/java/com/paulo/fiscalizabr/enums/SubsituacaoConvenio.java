package com.paulo.fiscalizabr.enums;

import java.io.Serializable;

/**
 * Created by Paulo on 08/04/2016.
 */
public enum SubsituacaoConvenio implements Serializable {

    CONVENIO("Convênio"),
    EM_ADITIVACAO("Em Aditivação"),
    EM_AJUSTE_PLANO_TRABALHO("Em Ajuste do Plano de Trabalho"),
    EM_AJUSTE_PELO_CONCEDENTE("Em Ajustes pelo Concedente"),
    EM_COMPLEMENTACAO("Em Complementação"),
    EM_ANALISE("Em Análise"),
    EM_COMPLEMENTACAO_PRORROGA("Em Complementação Prorroga"),
    EM_AJUSTE_PELO_CONVENENTE("Em Ajustes pelo Convenente"),
    EM_PRORROGACAO("Em Prorrogação"),
    CONVENIO_CANCELADO("Convênio Cancelado");

    private final String descricao;

    private SubsituacaoConvenio(String desc) {
        this.descricao = desc;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
