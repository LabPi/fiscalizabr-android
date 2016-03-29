package com.paulo.fiscalizabr.core;

/**
 * Created by Paulo on 29/03/2016.
 */
public class Favoritos {

    private String objetoConvenio;
    private String vigencia;
    private String valorConvenio;
    private String municipioUf;

    public Favoritos() {    }

    public Favoritos(String objetoConvenio, String vigencia, String valorConvenio, String municipioUf) {
        this.objetoConvenio = objetoConvenio;
        this.vigencia = vigencia;
        this.valorConvenio = valorConvenio;
        this.municipioUf = municipioUf;
    }

    public String getObjetoConvenio() {
        return objetoConvenio;
    }

    public void setObjetoConvenio(String objetoConvenio) {
        this.objetoConvenio = objetoConvenio;
    }

    public String getVigencia() {
        return vigencia;
    }

    public void setVigencia(String vigencia) {
        this.vigencia = vigencia;
    }

    public String getValorConvenio() {
        return valorConvenio;
    }

    public void setValorConvenio(String valorConvenio) {
        this.valorConvenio = valorConvenio;
    }

    public String getMunicipioUf() {
        return municipioUf;
    }

    public void setMunicipioUf(String municipioUf) {
        this.municipioUf = municipioUf;
    }

}
