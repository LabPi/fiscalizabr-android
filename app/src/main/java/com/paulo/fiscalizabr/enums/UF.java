package com.paulo.fiscalizabr.enums;

import java.io.Serializable;

/**
 * Created by Paulo on 08/04/2016.
 */
public enum UF implements Serializable {

    AC("AC"),
    AL("AL"),
    AM("AM"),
    AP("AP"),
    BA("BA"),
    CE("CE"),
    DF("DF"),
    ES("ES"),
    GO("GO"),
    MA("MA"),
    MG("MG"),
    MS("MS"),
    MT("MT"),
    PA("PA"),
    PB("PB"),
    PE("PE"),
    PI("PI"),
    PR("PR"),
    RJ("RJ"),
    RN("RN"),
    RO("RO"),
    RR("RR"),
    RS("RS"),
    SC("SC"),
    SE("SE"),
    SP("SP"),
    TO("TO");

    private final String descricao;

    private UF(String desc) {
        this.descricao = desc;
    }

    public String getDescricao() {
        return this.descricao;
    }

}
