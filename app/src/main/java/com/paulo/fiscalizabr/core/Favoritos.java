package com.paulo.fiscalizabr.core;

/**
 * Created by Paulo on 29/03/2016.
 */
public class Favoritos {

    private String objetoConvenio;
    private String vigencia;
    private String valorConvenio;
    private String municipioUf;
    // Situação do Convênio, 1 - Encerrado com problema; 2 - Em andamento; 3 - Encerrado com sucesso
    private Integer situacaoConvenio;

    public Favoritos() {    }

    public Favoritos(String objetoConvenio, String vigencia, String valorConvenio, String municipioUf, Integer situacaoConvenio) {
        this.objetoConvenio = objetoConvenio;
        this.vigencia = vigencia;
        this.valorConvenio = valorConvenio;
        this.municipioUf = municipioUf;
        this.situacaoConvenio = situacaoConvenio;
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

    public Integer getSituacaoConvenio() {
        return situacaoConvenio;
    }

    public void setSituacaoConvenio(Integer situacaoConvenio) {
        this.situacaoConvenio = situacaoConvenio;
    }
}
