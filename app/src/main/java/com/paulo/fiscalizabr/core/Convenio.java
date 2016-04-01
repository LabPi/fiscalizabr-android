package com.paulo.fiscalizabr.core;

/**
 * Created by Paulo on 25/03/2016.
 */
public class Convenio {

    private Integer numeroConvenio;
    private String objetoConvenio;
    private boolean isFavorito;
    private String municipio;
    private String uf;
    private String nomeProponente;
    private String vigencia;
    private String valorConvenio;
    /*
        Situação do Convênio
        1 - AGUARDANDO PRESTAÇÃO DE CONTAS
        2 - EM EXECUÇÃO
        3 - ASSINADO
        4 - PRESTAÇÃO DE CONTAS EM ANÁLISE
        5 - PRESTAÇÃO DE CONTAS REJEITADA
        6 - PRESTAÇÃO DE CONTAS EM COMPLEMENTAÇÃO
        7 - PRESTAÇÃO DE CONTAS APROVADA
        8 - PLANO DE TRABALHO COMPLEMENTADO EM ANÁLISE
        9 - PLANO DE TRABALHO EM COMPLEMENTAÇÃO
        10 -  PROPOSTA EM ANÁLISE
        11 - PLANO DE TRABALHO EM ANÁLISE
    */
    private Integer situacaoConvenio;

    public Convenio() { }

    public Convenio(Integer numeroConvenio, String objetoConvenio, boolean isFavorito, String municipio, String uf, String nomeProponente, String vigencia, String valorConvenio) {
        this.numeroConvenio = numeroConvenio;
        this.objetoConvenio = objetoConvenio;
        this.isFavorito = isFavorito;
        this.municipio = municipio;
        this.uf = uf;
        this.nomeProponente = nomeProponente;
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

    public Integer getSituacaoConvenio() {
        return situacaoConvenio;
    }

    public void setSituacaoConvenio(Integer situacaoConvenio) {
        this.situacaoConvenio = situacaoConvenio;
    }

    public Integer getNumeroConvenio() {
        return numeroConvenio;
    }

    public void setNumeroConvenio(Integer numeroConvenio) {
        this.numeroConvenio = numeroConvenio;
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

    public String getNomeProponente() {
        return nomeProponente;
    }

    public void setNomeProponente(String nomeProponente) {
        this.nomeProponente = nomeProponente;
    }
}
