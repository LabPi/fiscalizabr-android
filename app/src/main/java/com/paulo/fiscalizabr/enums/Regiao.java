package com.paulo.fiscalizabr.enums;

import java.io.Serializable;

/**
 * Created by Paulo on 08/04/2016.
 */
public enum Regiao implements Serializable {

    SUDESTE("Sudeste"),
    CENTRO_OESTE("Centro-Oeste"),
    NORDESTE("Nordeste"),
    NORTE("Norte"),
    SUL("Sul");

    private final String descricao;

    private Regiao(String desc) {
        this.descricao = desc;
    }

    public String getDescricao() {
        return this.descricao;
    }
}