package com.paulo.fiscalizabr.core;

/**
 * Created by Paulo on 25/03/2016.
 */
public class Convenio {

    private String nomeProponente;
    private String municipio;
    private String uf;
    private String objeto;
    private String valorGlobal;
    private String inicioVigencia;

    public Convenio() { }

    public Convenio(String nomeProponente, String municipio, String uf, String objeto, String valorGlobal, String inicioVigencia) {
        this.nomeProponente = nomeProponente;
        this.municipio = municipio;
        this.uf = uf;
        this.objeto = objeto;
        this.valorGlobal = valorGlobal;
        this.inicioVigencia = inicioVigencia;
    }

    public String getNomeProponente() {
        return nomeProponente;
    }

    public void setNomeProponente(String nomeProponente) {
        this.nomeProponente = nomeProponente;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getValorGlobal() {
        return valorGlobal;
    }

    public void setValorGlobal(String valorGlobal) {
        this.valorGlobal = valorGlobal;
    }

    public String getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(String inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

}
