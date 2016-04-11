package com.paulo.fiscalizabr.core;

/**
 * Created by Paulo on 01/04/2016.
 */
public class ValorConvenio {

    private String valorContrapartidaFinanceira;
    private String valorContrapartidaFinanceiraBensEServicos;
    private String valorDesembolsado;
    private String valorEmpenhado;
    private String valorGlobal;
    private String valorRepasseUniao;
    private String valorTotalContrapartida;

    public ValorConvenio() {   }

    public ValorConvenio(String valorContrapartidaFinanceira, String valorContrapartidaFinanceiraBensEServicos, String valorDesembolsado, String valorEmpenhado, String valorGlobal, String valorRepasseUniao, String valorTotalContrapartida) {
        this.valorContrapartidaFinanceira = valorContrapartidaFinanceira;
        this.valorContrapartidaFinanceiraBensEServicos = valorContrapartidaFinanceiraBensEServicos;
        this.valorDesembolsado = valorDesembolsado;
        this.valorEmpenhado = valorEmpenhado;
        this.valorGlobal = valorGlobal;
        this.valorRepasseUniao = valorRepasseUniao;
        this.valorTotalContrapartida = valorTotalContrapartida;
    }

    public String getValorContrapartidaFinanceira() {
        return valorContrapartidaFinanceira;
    }

    public void setValorContrapartidaFinanceira(String valorContrapartidaFinanceira) {
        this.valorContrapartidaFinanceira = valorContrapartidaFinanceira;
    }

    public String getValorContrapartidaFinanceiraBensEServicos() {
        return valorContrapartidaFinanceiraBensEServicos;
    }

    public void setValorContrapartidaFinanceiraBensEServicos(String valorContrapartidaFinanceiraBensEServicos) {
        this.valorContrapartidaFinanceiraBensEServicos = valorContrapartidaFinanceiraBensEServicos;
    }

    public String getValorDesembolsado() {
        return valorDesembolsado;
    }

    public void setValorDesembolsado(String valorDesembolsado) {
        this.valorDesembolsado = valorDesembolsado;
    }

    public String getValorEmpenhado() {
        return valorEmpenhado;
    }

    public void setValorEmpenhado(String valorEmpenhado) {
        this.valorEmpenhado = valorEmpenhado;
    }

    public String getValorGlobal() {
        return valorGlobal;
    }

    public void setValorGlobal(String valorGlobal) {
        this.valorGlobal = valorGlobal;
    }

    public String getValorRepasseUniao() {
        return valorRepasseUniao;
    }

    public void setValorRepasseUniao(String valorRepasseUniao) {
        this.valorRepasseUniao = valorRepasseUniao;
    }

    public String getValorTotalContrapartida() {
        return valorTotalContrapartida;
    }

    public void setValorTotalContrapartida(String valorTotalContrapartida) {
        this.valorTotalContrapartida = valorTotalContrapartida;
    }
}
