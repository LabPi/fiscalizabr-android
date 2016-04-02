package com.paulo.fiscalizabr.core;

/**
 * Created by Paulo on 01/04/2016.
 */
public class Proponente {

    private String bairroProponente;
    private String cargoResponsavelProponente;
    private String cepProponente;
    private String codigoResponsavelProponente;
    private String enderecoProponente;
    private String esferaAdministrativa;
    private String identificacaoProponente;
    private String municipio;
    private String nomeProponente;
    private String nomeResponsavelProponente;
    private String qualificacao;
    private String regiao;
    private String uf;

    public Proponente() {   }

    public Proponente(String bairroProponente, String cargoResponsavelProponente, String cepProponente, String codigoResponsavelProponente, String enderecoProponente, String esferaAdministrativa, String identificacaoProponente, String municipio, String nomeProponente, String nomeResponsavelProponente, String qualificacao, String regiao, String uf) {
        this.bairroProponente = bairroProponente;
        this.cargoResponsavelProponente = cargoResponsavelProponente;
        this.cepProponente = cepProponente;
        this.codigoResponsavelProponente = codigoResponsavelProponente;
        this.enderecoProponente = enderecoProponente;
        this.esferaAdministrativa = esferaAdministrativa;
        this.identificacaoProponente = identificacaoProponente;
        this.municipio = municipio;
        this.nomeProponente = nomeProponente;
        this.nomeResponsavelProponente = nomeResponsavelProponente;
        this.qualificacao = qualificacao;
        this.regiao = regiao;
        this.uf = uf;
    }

    public String getBairroProponente() {
        return bairroProponente;
    }

    public void setBairroProponente(String bairroProponente) {
        this.bairroProponente = bairroProponente;
    }

    public String getCargoResponsavelProponente() {
        return cargoResponsavelProponente;
    }

    public void setCargoResponsavelProponente(String cargoResponsavelProponente) {
        this.cargoResponsavelProponente = cargoResponsavelProponente;
    }

    public String getCepProponente() {
        return cepProponente;
    }

    public void setCepProponente(String cepProponente) {
        this.cepProponente = cepProponente;
    }

    public String getCodigoResponsavelProponente() {
        return codigoResponsavelProponente;
    }

    public void setCodigoResponsavelProponente(String codigoResponsavelProponente) {
        this.codigoResponsavelProponente = codigoResponsavelProponente;
    }

    public String getEnderecoProponente() {
        return enderecoProponente;
    }

    public void setEnderecoProponente(String enderecoProponente) {
        this.enderecoProponente = enderecoProponente;
    }

    public String getEsferaAdministrativa() {
        return esferaAdministrativa;
    }

    public void setEsferaAdministrativa(String esferaAdministrativa) {
        this.esferaAdministrativa = esferaAdministrativa;
    }

    public String getIdentificacaoProponente() {
        return identificacaoProponente;
    }

    public void setIdentificacaoProponente(String identificacaoProponente) {
        this.identificacaoProponente = identificacaoProponente;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getNomeProponente() {
        return nomeProponente;
    }

    public void setNomeProponente(String nomeProponente) {
        this.nomeProponente = nomeProponente;
    }

    public String getNomeResponsavelProponente() {
        return nomeResponsavelProponente;
    }

    public void setNomeResponsavelProponente(String nomeResponsavelProponente) {
        this.nomeResponsavelProponente = nomeResponsavelProponente;
    }

    public String getQualificacao() {
        return qualificacao;
    }

    public void setQualificacao(String qualificacao) {
        this.qualificacao = qualificacao;
    }

    public String getRegiao() {
        return regiao;
    }

    public void setRegiao(String regiao) {
        this.regiao = regiao;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

}
