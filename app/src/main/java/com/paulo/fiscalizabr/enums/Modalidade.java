package com.paulo.fiscalizabr.enums;

import java.io.Serializable;

/**
 * Created by Paulo on 08/04/2016.
 */
public enum Modalidade implements Serializable {

    CONTRATO_REPASSE("Contrato de Repasse"),
    CONVENIO("Convênio"),
    TERMO_COOPERACAO("Termo de Cooperação"),
    TERMO_PARCERIA("Termo de Parceria");

    private final String descricao;

    private Modalidade(String desc) {
        this.descricao = desc;
    }

    public String getDescricao() {
        return this.descricao;
    }
}
