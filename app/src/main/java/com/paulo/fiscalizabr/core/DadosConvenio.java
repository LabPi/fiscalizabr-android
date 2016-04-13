package com.paulo.fiscalizabr.core;

/**
 * Created by Paulo on 01/04/2016.
 */
public class DadosConvenio {

    private Integer anoAssinatura;
    private Integer anoConvenio;
    private Integer anoPublicacao;
    private String dataAssinatura;
    private String dataPublicacao;
    private String fimVigencia;
    private String inicioVigencia;
    private String justificativa;
    private Integer mesAssinatura;
    private Integer mesPublicacao;
    private String modalidade;
    private String nomePrograma;
    private String numeroConvenio;
    private String numeroProcesso;
    private String objeto;
    private String situacaoPublicacaoConvenio;

    private OrgaoConcedente orgaoConcedente;
    private OrgaoSuperior orgaoSuperior;

    private Proponente proponente;
    private Proposta proposta;

    private Integer quantidadeAditivos;
    private Integer quantidadeEmpenhos;
    private Integer quantidadeProrrogas;
    private String situacaoConvenio;
    private String subsituacaoConvenio;
    private String ultimoPagamento;

    private ValorConvenio valorConvenio;

    public DadosConvenio() {   }

    public DadosConvenio(Integer anoAssinatura, Integer anoConvenio, Integer anoPublicacao, String dataAssinatura, String dataPublicacao, String fimVigencia, String inicioVigencia, String justificativa, Integer mesAssinatura, Integer mesPublicacao, String modalidade, String nomePrograma, String numeroConvenio, String numeroProcesso, String objeto, String situacaoPublicacaoConvenio, OrgaoConcedente orgaoConcedente, OrgaoSuperior orgaoSuperior, Proponente proponente, Proposta proposta, Integer quantidadeAditivos, Integer quantidadeEmpenhos, Integer quantidadeProrrogas, String situacaoConvenio, String subsituacaoConvenio, String ultimoPagamento, ValorConvenio valorConvenio) {
        this.anoAssinatura = anoAssinatura;
        this.anoConvenio = anoConvenio;
        this.anoPublicacao = anoPublicacao;
        this.dataAssinatura = dataAssinatura;
        this.dataPublicacao = dataPublicacao;
        this.fimVigencia = fimVigencia;
        this.inicioVigencia = inicioVigencia;
        this.justificativa = justificativa;
        this.mesAssinatura = mesAssinatura;
        this.mesPublicacao = mesPublicacao;
        this.modalidade = modalidade;
        this.nomePrograma = nomePrograma;
        this.numeroConvenio = numeroConvenio;
        this.numeroProcesso = numeroProcesso;
        this.objeto = objeto;
        this.situacaoPublicacaoConvenio = situacaoPublicacaoConvenio;
        this.orgaoConcedente = orgaoConcedente;
        this.orgaoSuperior = orgaoSuperior;
        this.proponente = proponente;
        this.proposta = proposta;
        this.quantidadeAditivos = quantidadeAditivos;
        this.quantidadeEmpenhos = quantidadeEmpenhos;
        this.quantidadeProrrogas = quantidadeProrrogas;
        this.situacaoConvenio = situacaoConvenio;
        this.subsituacaoConvenio = subsituacaoConvenio;
        this.ultimoPagamento = ultimoPagamento;
        this.valorConvenio = valorConvenio;
    }

    public Integer getAnoAssinatura() {
        return anoAssinatura;
    }

    public void setAnoAssinatura(Integer anoAssinatura) {
        this.anoAssinatura = anoAssinatura;
    }

    public Integer getAnoConvenio() {
        return anoConvenio;
    }

    public void setAnoConvenio(Integer anoConvenio) {
        this.anoConvenio = anoConvenio;
    }

    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(Integer anoPublicacao) {
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

    public Integer getMesAssinatura() {
        return mesAssinatura;
    }

    public void setMesAssinatura(Integer mesAssinatura) {
        this.mesAssinatura = mesAssinatura;
    }

    public Integer getMesPublicacao() {
        return mesPublicacao;
    }

    public void setMesPublicacao(Integer mesPublicacao) {
        this.mesPublicacao = mesPublicacao;
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

    public String getNumeroConvenio() {
        return numeroConvenio;
    }

    public void setNumeroConvenio(String numeroConvenio) {
        this.numeroConvenio = numeroConvenio;
    }

    public String getNumeroProcesso() {
        return numeroProcesso;
    }

    public void setNumeroProcesso(String numeroProcesso) {
        this.numeroProcesso = numeroProcesso;
    }

    public String getObjeto() {
        return objeto;
    }

    public void setObjeto(String objeto) {
        this.objeto = objeto;
    }

    public String getSituacaoPublicacaoConvenio() {
        return situacaoPublicacaoConvenio;
    }

    public void setSituacaoPublicacaoConvenio(String situacaoPublicacaoConvenio) {
        this.situacaoPublicacaoConvenio = situacaoPublicacaoConvenio;
    }

    public OrgaoConcedente getOrgaoConcedente() {
        return orgaoConcedente;
    }

    public void setOrgaoConcedente(OrgaoConcedente orgaoConcedente) {
        this.orgaoConcedente = orgaoConcedente;
    }

    public OrgaoSuperior getOrgaoSuperior() {
        return orgaoSuperior;
    }

    public void setOrgaoSuperior(OrgaoSuperior orgaoSuperior) {
        this.orgaoSuperior = orgaoSuperior;
    }

    public Proponente getProponente() {
        return proponente;
    }

    public void setProponente(Proponente proponente) {
        this.proponente = proponente;
    }

    public Proposta getProposta() {
        return proposta;
    }

    public void setProposta(Proposta proposta) {
        this.proposta = proposta;
    }

    public Integer getQuantidadeAditivos() {
        return quantidadeAditivos;
    }

    public void setQuantidadeAditivos(Integer quantidadeAditivos) {
        this.quantidadeAditivos = quantidadeAditivos;
    }

    public Integer getQuantidadeEmpenhos() {
        return quantidadeEmpenhos;
    }

    public void setQuantidadeEmpenhos(Integer quantidadeEmpenhos) {
        this.quantidadeEmpenhos = quantidadeEmpenhos;
    }

    public Integer getQuantidadeProrrogas() {
        return quantidadeProrrogas;
    }

    public void setQuantidadeProrrogas(Integer quantidadeProrrogas) {
        this.quantidadeProrrogas = quantidadeProrrogas;
    }

    public String getSituacaoConvenio() {
        return situacaoConvenio;
    }

    public void setSituacaoConvenio(String situacaoConvenio) {
        this.situacaoConvenio = situacaoConvenio;
    }

    public String getSubsituacaoConvenio() {
        return subsituacaoConvenio;
    }

    public void setSubsituacaoConvenio(String subsituacaoConvenio) {
        this.subsituacaoConvenio = subsituacaoConvenio;
    }

    public String getUltimoPagamento() {
        return ultimoPagamento;
    }

    public void setUltimoPagamento(String ultimoPagamento) {
        this.ultimoPagamento = ultimoPagamento;
    }

    public ValorConvenio getValorConvenio() {
        return valorConvenio;
    }

    public void setValorConvenio(ValorConvenio valorConvenio) {
        this.valorConvenio = valorConvenio;
    }
}