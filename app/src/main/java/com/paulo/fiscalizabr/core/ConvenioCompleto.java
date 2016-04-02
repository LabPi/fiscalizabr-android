package com.paulo.fiscalizabr.core;

/**
 * Created by Paulo on 25/03/2016.
 */
public class ConvenioCompleto {

    // Detalhes do Convênio
    private String anoAssinatura;
    private String anoConvenio;
    private String anoPublicacao;
    private String dataAssinatura;
    private String dataPublicacao;
    private boolean estaAssinado;
    private boolean estaPublicado;
    private boolean estaEmpenhado;
    private String fimVigencia;
    private String inicioVigencia;
    private String justificativa;
    private String modalidade;
    private String nomePrograma;
    private String objeto;
    private Integer numeroConvenio;

    // Orgão Concedente
    private String cargoResponsavelConcedente;
    private boolean permiteAjusteCronogramaFisico;
    private boolean possuiAditivo;
    private boolean possuiProgramaDoOficio;

    // Proponente
    private String municipio;
    private String nomeProponente;
    private String nomeResponsavelProponente;
    private String qualificacao;
    private String regiao;
    private String uf;

    // Proposta
    private String anoProposta;
    private String dataInicioProposta;
    private String situacaoConvenio;
    private String situacaoPublicacaoConvenio;
    private String ultimoEmpenho;
    private String ultimoPagamento;

    // Valores
    private String valorContrapartidaFinanceira;
    private String valorContrapartidaFinanceiraBensEServicos;
    private String valorDesembolsado;
    private String valorGlobal;
    private String valorRepasseUniao;
    private String valorTotalContrapartida;

    public ConvenioCompleto() { }

    public ConvenioCompleto(String anoAssinatura, String anoConvenio, String anoPublicacao, String dataAssinatura, String dataPublicacao, boolean estaAssinado, boolean estaPublicado, boolean estaEmpenhado, String fimVigencia, String inicioVigencia, String justificativa, String modalidade, String nomePrograma, String objeto, String cargoResponsavelConcedente, boolean permiteAjusteCronogramaFisico, boolean possuiAditivo, boolean possuiProgramaDoOficio, String municipio, String nomeProponente, String nomeResponsavelProponente, String qualificacao, String regiao, String uf, String anoProposta, String dataInicioProposta, String situacaoConvenio, String situacaoPublicacaoConvenio, String ultimoEmpenho, String ultimoPagamento, String valorContrapartidaFinanceira, String valorContrapartidaFinanceiraBensEServicos, String valorDesembolsado, String valorGlobal, String valorRepasseUniao, String valorTotalContrapartida) {
        this.anoAssinatura = anoAssinatura;
        this.anoConvenio = anoConvenio;
        this.anoPublicacao = anoPublicacao;
        this.dataAssinatura = dataAssinatura;
        this.dataPublicacao = dataPublicacao;
        this.estaAssinado = estaAssinado;
        this.estaPublicado = estaPublicado;
        this.estaEmpenhado = estaEmpenhado;
        this.fimVigencia = fimVigencia;
        this.inicioVigencia = inicioVigencia;
        this.justificativa = justificativa;
        this.modalidade = modalidade;
        this.nomePrograma = nomePrograma;
        this.objeto = objeto;
        this.cargoResponsavelConcedente = cargoResponsavelConcedente;
        this.permiteAjusteCronogramaFisico = permiteAjusteCronogramaFisico;
        this.possuiAditivo = possuiAditivo;
        this.possuiProgramaDoOficio = possuiProgramaDoOficio;
        this.municipio = municipio;
        this.nomeProponente = nomeProponente;
        this.nomeResponsavelProponente = nomeResponsavelProponente;
        this.qualificacao = qualificacao;
        this.regiao = regiao;
        this.uf = uf;
        this.anoProposta = anoProposta;
        this.dataInicioProposta = dataInicioProposta;
        this.situacaoConvenio = situacaoConvenio;
        this.situacaoPublicacaoConvenio = situacaoPublicacaoConvenio;
        this.ultimoEmpenho = ultimoEmpenho;
        this.ultimoPagamento = ultimoPagamento;
        this.valorContrapartidaFinanceira = valorContrapartidaFinanceira;
        this.valorContrapartidaFinanceiraBensEServicos = valorContrapartidaFinanceiraBensEServicos;
        this.valorDesembolsado = valorDesembolsado;
        this.valorGlobal = valorGlobal;
        this.valorRepasseUniao = valorRepasseUniao;
        this.valorTotalContrapartida = valorTotalContrapartida;
    }

    public String getAnoAssinatura() {
        return anoAssinatura;
    }

    public void setAnoAssinatura(String anoAssinatura) {
        this.anoAssinatura = anoAssinatura;
    }

    public String getAnoConvenio() {
        return anoConvenio;
    }

    public void setAnoConvenio(String anoConvenio) {
        this.anoConvenio = anoConvenio;
    }

    public String getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(String anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getDataAssinatura() {
        return dataAssinatura;
    }

    public void setDataAssinatura(String dataAssinatura) {
        this.dataAssinatura = dataAssinatura;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public boolean isEstaAssinado() {
        return estaAssinado;
    }

    public void setEstaAssinado(boolean estaAssinado) {
        this.estaAssinado = estaAssinado;
    }

    public boolean isEstaPublicado() {
        return estaPublicado;
    }

    public void setEstaPublicado(boolean estaPublicado) {
        this.estaPublicado = estaPublicado;
    }

    public boolean isEstaEmpenhado() {
        return estaEmpenhado;
    }

    public void setEstaEmpenhado(boolean estaEmpenhado) {
        this.estaEmpenhado = estaEmpenhado;
    }

    public String getFimVigencia() {
        return fimVigencia;
    }

    public void setFimVigencia(String fimVigencia) {
        this.fimVigencia = fimVigencia;
    }

    public String getInicioVigencia() {
        return inicioVigencia;
    }

    public void setInicioVigencia(String inicioVigencia) {
        this.inicioVigencia = inicioVigencia;
    }

    public String getJustificativa() {
        return justificativa;
    }

    public void setJustificativa(String justificativa) {
        this.justificativa = justificativa;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getNomePrograma() {
        return nomePrograma;
    }

    public void setNomePrograma(String nomePrograma) {
        this.nomePrograma = nomePrograma;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getCargoResponsavelConcedente() {
        return cargoResponsavelConcedente;
    }

    public void setCargoResponsavelConcedente(String cargoResponsavelConcedente) {
        this.cargoResponsavelConcedente = cargoResponsavelConcedente;
    }

    public boolean isPermiteAjusteCronogramaFisico() {
        return permiteAjusteCronogramaFisico;
    }

    public void setPermiteAjusteCronogramaFisico(boolean permiteAjusteCronogramaFisico) {
        this.permiteAjusteCronogramaFisico = permiteAjusteCronogramaFisico;
    }

    public boolean isPossuiAditivo() {
        return possuiAditivo;
    }

    public void setPossuiAditivo(boolean possuiAditivo) {
        this.possuiAditivo = possuiAditivo;
    }

    public boolean isPossuiProgramaDoOficio() {
        return possuiProgramaDoOficio;
    }

    public void setPossuiProgramaDoOficio(boolean possuiProgramaDoOficio) {
        this.possuiProgramaDoOficio = possuiProgramaDoOficio;
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

    public String getAnoProposta() {
        return anoProposta;
    }

    public void setAnoProposta(String anoProposta) {
        this.anoProposta = anoProposta;
    }

    public String getDataInicioProposta() {
        return dataInicioProposta;
    }

    public void setDataInicioProposta(String dataInicioProposta) {
        this.dataInicioProposta = dataInicioProposta;
    }

    public String getSituacaoConvenio() {
        return situacaoConvenio;
    }

    public void setSituacaoConvenio(String situacaoConvenio) {
        this.situacaoConvenio = situacaoConvenio;
    }

    public String getSituacaoPublicacaoConvenio() {
        return situacaoPublicacaoConvenio;
    }

    public void setSituacaoPublicacaoConvenio(String situacaoPublicacaoConvenio) {
        this.situacaoPublicacaoConvenio = situacaoPublicacaoConvenio;
    }

    public String getUltimoEmpenho() {
        return ultimoEmpenho;
    }

    public void setUltimoEmpenho(String ultimoEmpenho) {
        this.ultimoEmpenho = ultimoEmpenho;
    }

    public String getUltimoPagamento() {
        return ultimoPagamento;
    }

    public void setUltimoPagamento(String ultimoPagamento) {
        this.ultimoPagamento = ultimoPagamento;
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

    public Integer getNumeroConvenio() {
        return numeroConvenio;
    }

    public void setNumeroConvenio(Integer numeroConvenio) {
        this.numeroConvenio = numeroConvenio;
    }
}
