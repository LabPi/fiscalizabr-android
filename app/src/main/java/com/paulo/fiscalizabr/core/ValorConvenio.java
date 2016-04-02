package com.paulo.fiscalizabr.core;

/**
 * Created by Paulo on 01/04/2016.
 */
public class ValorConvenio {

    private Integer valorContrapartidaFinanceiraBensEServicos;
    private Integer valorDesembolsado;
    private Integer valorEmpenhado;
    private Integer valorGlobal;
    private Integer valorRepasseUniao;
    private Integer valorTotalContrapartida;

    public ValorConvenio() {   }

    public ValorConvenio(Integer valorContrapartidaFinanceiraBensEServicos, Integer valorDesembolsado, Integer valorEmpenhado, Integer valorGlobal, Integer valorRepasseUniao, Integer valorTotalContrapartida) {
        this.valorContrapartidaFinanceiraBensEServicos = valorContrapartidaFinanceiraBensEServicos;
        this.valorDesembolsado = valorDesembolsado;
        this.valorEmpenhado = valorEmpenhado;
        this.valorGlobal = valorGlobal;
        this.valorRepasseUniao = valorRepasseUniao;
        this.valorTotalContrapartida = valorTotalContrapartida;
    }

    public Integer getValorContrapartidaFinanceiraBensEServicos() {
        return valorContrapartidaFinanceiraBensEServicos;
    }

    public void setValorContrapartidaFinanceiraBensEServicos(Integer valorContrapartidaFinanceiraBensEServicos) {
        this.valorContrapartidaFinanceiraBensEServicos = valorContrapartidaFinanceiraBensEServicos;
    }

    public Integer getValorDesembolsado() {
        return valorDesembolsado;
    }

    public void setValorDesembolsado(Integer valorDesembolsado) {
        this.valorDesembolsado = valorDesembolsado;
    }

    public Integer getValorEmpenhado() {
        return valorEmpenhado;
    }

    public void setValorEmpenhado(Integer valorEmpenhado) {
        this.valorEmpenhado = valorEmpenhado;
    }

    public Integer getValorGlobal() {
        return valorGlobal;
    }

    public void setValorGlobal(Integer valorGlobal) {
        this.valorGlobal = valorGlobal;
    }

    public Integer getValorRepasseUniao() {
        return valorRepasseUniao;
    }

    public void setValorRepasseUniao(Integer valorRepasseUniao) {
        this.valorRepasseUniao = valorRepasseUniao;
    }

    public Integer getValorTotalContrapartida() {
        return valorTotalContrapartida;
    }

    public void setValorTotalContrapartida(Integer valorTotalContrapartida) {
        this.valorTotalContrapartida = valorTotalContrapartida;
    }

}
