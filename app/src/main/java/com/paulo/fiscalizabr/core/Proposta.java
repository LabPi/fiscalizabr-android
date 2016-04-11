package com.paulo.fiscalizabr.core;

/**
 * Created by Paulo on 01/04/2016.
 */
public class Proposta {

    private Integer anoProposta;
    private String dataInclusao;
    private String identificacaoProposta;
    private String numeroProposta;

    public Proposta() {   }

    public Proposta(Integer anoProposta, String dataInclusao, String identificacaoProposta, String numeroProposta) {
        this.anoProposta = anoProposta;
        this.dataInclusao = dataInclusao;
        this.identificacaoProposta = identificacaoProposta;
        this.numeroProposta = numeroProposta;
    }

    public Integer getAnoProposta() {
        return anoProposta;
    }

    public void setAnoProposta(Integer anoProposta) {
        this.anoProposta = anoProposta;
    }

    public String getDataInclusao() {
        return dataInclusao;
    }

    public void setDataInclusao(String dataInclusao) {
        this.dataInclusao = dataInclusao;
    }

    public String getIdentificacaoProposta() {
        return identificacaoProposta;
    }

    public void setIdentificacaoProposta(String identificacaoProposta) {
        this.identificacaoProposta = identificacaoProposta;
    }

    public String getNumeroProposta() {
        return numeroProposta;
    }

    public void setNumeroProposta(String numeroProposta) {
        this.numeroProposta = numeroProposta;
    }
}
