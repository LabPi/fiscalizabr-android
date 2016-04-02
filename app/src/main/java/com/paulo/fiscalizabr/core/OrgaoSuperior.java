package com.paulo.fiscalizabr.core;

/**
 * Created by Paulo on 01/04/2016.
 */
public class OrgaoSuperior {

    private Integer codigoOrgaoSuperior;
    private String nomeOrgaoSuperior;

    public OrgaoSuperior() {   }

    public OrgaoSuperior(Integer codigoOrgaoSuperior, String nomeOrgaoSuperior) {
        this.codigoOrgaoSuperior = codigoOrgaoSuperior;
        this.nomeOrgaoSuperior = nomeOrgaoSuperior;
    }

    public Integer getCodigoOrgaoSuperior() {
        return codigoOrgaoSuperior;
    }

    public void setCodigoOrgaoSuperior(Integer codigoOrgaoSuperior) {
        this.codigoOrgaoSuperior = codigoOrgaoSuperior;
    }

    public String getNomeOrgaoSuperior() {
        return nomeOrgaoSuperior;
    }

    public void setNomeOrgaoSuperior(String nomeOrgaoSuperior) {
        this.nomeOrgaoSuperior = nomeOrgaoSuperior;
    }

}
