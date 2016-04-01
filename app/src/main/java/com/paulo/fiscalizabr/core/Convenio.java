package com.paulo.fiscalizabr.core;

/**
 * Created by Paulo on 25/03/2016.
 */
public class Convenio {

    private String objetoConvenio;
    private boolean isFavorito;
    private String vigencia;
    private String valorConvenio;
    // Situação do Convênio, 1 - Encerrado com problema; 2 - Em andamento; 3 - Encerrado com sucesso
    private Integer situacaoConvenio;

    public Convenio() { }

    public Convenio(String objetoConvenio, boolean isFavorito, String vigencia, String valorConvenio, Integer situacaoConvenio) {
        this.objetoConvenio = objetoConvenio;
        this.isFavorito = isFavorito;
        this.vigencia = vigencia;
        this.valorConvenio = valorConvenio;
        situacaoConvenio = situacaoConvenio;
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

    public Integer getSituacaoConvenio() {
        return situacaoConvenio;
    }

    public void setSituacaoConvenio(Integer situacaoConvenio) {
        this.situacaoConvenio = situacaoConvenio;
    }
}
