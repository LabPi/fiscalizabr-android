package com.paulo.fiscalizabr.core;

/**
 * Created by Paulo on 01/04/2016.
 */
public class OrgaoConcedente {

    private String cargoResponsavelConcedente; //
    private String nomeOrgaoConcedente; //
    private String nomeResponsavelConcedente; //

    public OrgaoConcedente() {   }

    public OrgaoConcedente(String cargoResponsavelConcedente, String nomeOrgaoConcedente, String nomeResponsavelConcedente) {
        this.cargoResponsavelConcedente = cargoResponsavelConcedente;
        this.nomeOrgaoConcedente = nomeOrgaoConcedente;
        this.nomeResponsavelConcedente = nomeResponsavelConcedente;
    }

    public String getCargoResponsavelConcedente() {
        return cargoResponsavelConcedente;
    }

    public void setCargoResponsavelConcedente(String cargoResponsavelConcedente) {
        this.cargoResponsavelConcedente = cargoResponsavelConcedente;
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
