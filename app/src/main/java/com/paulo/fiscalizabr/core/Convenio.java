package com.paulo.fiscalizabr.core;

/**
 * Created by Paulo on 25/03/2016.
 */
public class Convenio {

    private String objetoConvenio;
    private boolean isFavorito;
    private String vigencia;
    private String valorConvenio;

    public Convenio() { }

    public Convenio(String objetoConvenio, boolean isFavorito, String vigencia, String valorConvenio) {
        this.objetoConvenio = objetoConvenio;
        this.isFavorito = isFavorito;
        this.vigencia = vigencia;
        this.valorConvenio = valorConvenio;
    }

    public String getObjetoConvenio() {
        return objetoConvenio;
    }

    public void setObjetoConvenio(String objetoConvenio) {
        this.objetoConvenio = objetoConvenio;
    }

    public boolean isFavorito() {
        return isFavorito;
    }

    public void setIsFavorito(boolean isFavorito) {
        this.isFavorito = isFavorito;
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

}
