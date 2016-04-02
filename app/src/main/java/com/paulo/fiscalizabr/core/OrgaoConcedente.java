package com.paulo.fiscalizabr.core;

/**
 * Created by Paulo on 01/04/2016.
 */
public class OrgaoConcedente {

    private String cargoResponsavelConcedente;
    private Integer codigoOrgaoConcedente;
    private String codigoResponsavelConcedente;
    private String nomeOrgaoConcedente;
    private String nomeResponsavelConcedente;

    public OrgaoConcedente() {   }

    public OrgaoConcedente(String cargoResponsavelConcedente, Integer codigoOrgaoConcedente, String codigoResponsavelConcedente, String nomeOrgaoConcedente, String nomeResponsavelConcedente) {
        this.cargoResponsavelConcedente = cargoResponsavelConcedente;
        this.codigoOrgaoConcedente = codigoOrgaoConcedente;
        this.codigoResponsavelConcedente = codigoResponsavelConcedente;
        this.nomeOrgaoConcedente = nomeOrgaoConcedente;
        this.nomeResponsavelConcedente = nomeResponsavelConcedente;
    }

    public String getCargoResponsavelConcedente() {
        return cargoResponsavelConcedente;
    }

    public void setCargoResponsavelConcedente(String cargoResponsavelConcedente) {
        this.cargoResponsavelConcedente = cargoResponsavelConcedente;
    }

    public Integer getCodigoOrgaoConcedente() {
        return codigoOrgaoConcedente;
    }

    public void setCodigoOrgaoConcedente(Integer codigoOrgaoConcedente) {
        this.codigoOrgaoConcedente = codigoOrgaoConcedente;
    }

    public String getCodigoResponsavelConcedente() {
        return codigoResponsavelConcedente;
    }

    public void setCodigoResponsavelConcedente(String codigoResponsavelConcedente) {
        this.codigoResponsavelConcedente = codigoResponsavelConcedente;
    }

    public String getNomeOrgaoConcedente() {
        return nomeOrgaoConcedente;
    }

    public void setNomeOrgaoConcedente(String nomeOrgaoConcedente) {
        this.nomeOrgaoConcedente = nomeOrgaoConcedente;
    }

    public String getNomeResponsavelConcedente() {
        return nomeResponsavelConcedente;
    }

    public void setNomeResponsavelConcedente(String nomeResponsavelConcedente) {
        this.nomeResponsavelConcedente = nomeResponsavelConcedente;
    }

}
