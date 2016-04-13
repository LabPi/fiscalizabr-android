package com.paulo.fiscalizabr.core;

import android.util.Log;

import com.paulo.fiscalizabr.tools.StringsTreatment;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Created by Paulo on 25/03/2016.
 */
public class Convenio implements Comparator<Convenio> {

    public final String RESULT_IS_NULL = "RESULT_IS_NULL"; // Resultado da busca do Convênio não retornou resultados
    public final String NO_INTERNET = "NO_INTERNET"; // Contém dados resultantes da busca no servidor
    public final String IS_LOADING = "IS_LOADING"; // Está carregando os convênios do servidor

    private String numeroConvenio;
    private String objetoConvenio;
    private boolean isFavorito = false;
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
    private Integer mesVigencia;
    private Integer anoVigencia;
    private Integer mesFinalVigencia;
    private Integer anoFinalVigencia;

    public Convenio() { }

    public Convenio(String numeroConvenio, String objetoConvenio, boolean isFavorito, String municipio, String uf, String nomeProponente, String vigencia, String valorConvenio) {
        this.numeroConvenio = numeroConvenio;
        this.objetoConvenio = objetoConvenio;
        this.isFavorito = isFavorito;
        this.municipio = municipio;
        this.uf = uf;
        this.nomeProponente = nomeProponente;
        this.vigencia = vigencia;
        this.valorConvenio = valorConvenio;
    }

    // Utilizado para setar a imagem e a mensagem a ser exibida no ListView
    public Convenio(String objetoConvenio) {
        this.objetoConvenio = objetoConvenio;
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

    public String getNumeroConvenio() {
        return numeroConvenio;
    }

    public void setNumeroConvenio(String numeroConvenio) {
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

    public Integer getMesVigencia() {
        return mesVigencia;
    }

    public void setMesVigencia(Integer mesVigencia) {
        this.mesVigencia = mesVigencia;
    }

    public Integer getAnoVigencia() {
        return anoVigencia;
    }

    public void setAnoVigencia(Integer anoVigencia) {
        this.anoVigencia = anoVigencia;
    }

    public Integer getMesFinalVigencia() {
        return mesFinalVigencia;
    }

    public void setMesFinalVigencia(Integer mesFinalVigencia) {
        this.mesFinalVigencia = mesFinalVigencia;
    }

    public Integer getAnoFinalVigencia() {
        return anoFinalVigencia;
    }

    public void setAnoFinalVigencia(Integer anoFinalVigencia) {
        this.anoFinalVigencia = anoFinalVigencia;
    }

    @Override
    public int compare(Convenio obj1, Convenio obj2) {
            // Valor
        StringsTreatment tratamento = new StringsTreatment();
        BigDecimal valor1 = new BigDecimal(obj1.getValorConvenio());
        BigDecimal valor2 = new BigDecimal(obj2.getValorConvenio());
        if(valor1.compareTo(valor2) < 0) return -1;
        else if(!(valor1.compareTo(valor2) < 0)) return 1;
        return 0;
    }
}
