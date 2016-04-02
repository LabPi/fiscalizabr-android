package com.paulo.fiscalizabr.core;

/**
 * Created by Paulo on 01/04/2016.
 */
public class Proposta {

    private Integer anoProposta;
    private String dataInclusaoProposta;
    private Integer identificacaoProposta;
    private Integer numeroProposta;

    public Proposta() {   }

    public Proposta(Integer anoProposta, String dataInclusaoProposta, Integer identificacaoProposta, Integer numeroProposta) {
        this.anoProposta = anoProposta;
        this.dataInclusaoProposta = dataInclusaoProposta;
        this.identificacaoProposta = identificacaoProposta;
        this.numeroProposta = numeroProposta;
    }

    public Integer getAnoProposta() {
        return anoProposta;
    }

    public void setAnoProposta(Integer anoProposta) {
        this.anoProposta = anoProposta;
    }

    public String getDataInclusaoProposta() {
        return dataInclusaoProposta;
    }

    public void setDataInclusaoProposta(String dataInclusaoProposta) {
        this.dataInclusaoProposta = dataInclusaoProposta;
    }

    public Integer getIdentificacaoProposta() {
        return identificacaoProposta;
    }

    public void setIdentificacaoProposta(Integer identificacaoProposta) {
        this.identificacaoProposta = identificacaoProposta;
    }

    public Integer getNumeroProposta() {
        return numeroProposta;
    }

    public void setNumeroProposta(Integer numeroProposta) {
        this.numeroProposta = numeroProposta;
    }

}
