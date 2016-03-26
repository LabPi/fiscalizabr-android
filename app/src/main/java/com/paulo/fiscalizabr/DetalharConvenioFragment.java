package com.paulo.fiscalizabr;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paulo.fiscalizabr.core.ConvenioCompleto;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetalharConvenioFragment extends Fragment {

    private View view;

    // Dados do Convênio
    private TextView anoAssinaturaConvenioPublicacao;
    private TextView dataAssinatura;
    private TextView dataPublicacao;
    private TextView assinadoEmpenhadoPublicado;
    private TextView inicioVigencia;
    private TextView fimVigencia;
    private TextView justificativa;
    private TextView modalidade;
    private TextView nomePrograma;
    private TextView objeto;

    // Orgão Concedente
    private TextView cargoConcedenteResponsavel;
    private TextView permiteAjusteCronograma;
    private TextView possuiAditivo;
    private TextView possuiProgramaOficio;

    // Proponente
    private TextView municipio;
    private TextView nomeProponente;
    private TextView nomeResponsavel;
    private TextView qualificacao;
    private TextView regiao;
    private TextView uf;

    // Proposta
    private TextView anoProposta;
    private TextView dataInicioProposta;
    private TextView situacaoConvenio;
    private TextView situacaoPublicacaoConvenio;
    private TextView ultimoEmpenho;
    private TextView ultimoPagamento;

    // Valores
    private TextView contrapartidaFinanceira;
    private TextView contrapartidaFinanceiraBensServicos;
    private TextView valorDesembolsado;
    private TextView valorGlobal;
    private TextView repasseUniao;
    private TextView contrapartidaTotal;

    private ConvenioCompleto conv;

    public DetalharConvenioFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detalhar_convenio, container, false);

        setUpWidgets();
        carregaDadosConvenio();
        setDadosConvenio();

        return view;
    }

    public void setUpWidgets() {
        // Dados do Convênio
        anoAssinaturaConvenioPublicacao = (TextView) view.findViewById(R.id.assinatura_convenio_publicacao_textview);
        dataAssinatura = (TextView) view.findViewById(R.id.data_assinatura_textview);
        dataPublicacao = (TextView) view.findViewById(R.id.data_publicacao_textview);
        assinadoEmpenhadoPublicado = (TextView) view.findViewById(R.id.assinado_empenho_publicado_textview);
        inicioVigencia = (TextView) view.findViewById(R.id.inicio_vigencia_convenio_textview);
        fimVigencia = (TextView) view.findViewById(R.id.fim_vigencia_convenio_textview);
        justificativa = (TextView) view.findViewById(R.id.justificativa_textview);
        modalidade = (TextView) view.findViewById(R.id.modalidade_textview);
        nomePrograma = (TextView) view.findViewById(R.id.nome_programa_textview);
        objeto = (TextView) view.findViewById(R.id.objeto_convenio_textview);

        // Orgão Concedente
        cargoConcedenteResponsavel = (TextView) view.findViewById(R.id.cargo_concedente_responsavel_textview);
        permiteAjusteCronograma = (TextView) view.findViewById(R.id.ajuste_cronograma_textview);
        possuiAditivo = (TextView) view.findViewById(R.id.aditivo_textview);
        possuiProgramaOficio = (TextView) view.findViewById(R.id.programa_oficio_textview);

        // Proponente
        municipio = (TextView) view.findViewById(R.id.municipio_proponente_textview);
        nomeProponente = (TextView) view.findViewById(R.id.nome_proponente_detalhado_textview);
        nomeResponsavel = (TextView) view.findViewById(R.id.nome_responsavel_textview);
        qualificacao = (TextView) view.findViewById(R.id.qualificacao_textview);
        regiao = (TextView) view.findViewById(R.id.regiao_textview);
        uf = (TextView) view.findViewById(R.id.uf_textview);

        // Proposta
        anoProposta = (TextView) view.findViewById(R.id.ano_proposta_textview);
        dataInicioProposta = (TextView) view.findViewById(R.id.data_inicio_proposta_textview);
        situacaoConvenio = (TextView) view.findViewById(R.id.situacao_convenio_textview);
        situacaoPublicacaoConvenio = (TextView) view.findViewById(R.id.situacao_publicacao_convenio_textview);
        ultimoEmpenho = (TextView) view.findViewById(R.id.ultimo_empenho_textview);
        ultimoPagamento = (TextView) view.findViewById(R.id.ultimo_pagamento_textview);

        // Valores
        contrapartidaFinanceira = (TextView) view.findViewById(R.id.contrapartida_financeira_textview);
        contrapartidaFinanceiraBensServicos = (TextView) view.findViewById(R.id.contrapartida_financeira_bens_servicos_textview);
        valorDesembolsado = (TextView) view.findViewById(R.id.valor_desembolsado_textview);
        valorGlobal = (TextView) view.findViewById(R.id.valor_global_detalhado_textview);
        repasseUniao = (TextView) view.findViewById(R.id.repasse_uniao_textview);
        contrapartidaTotal = (TextView) view.findViewById(R.id.contrapartida_total_textview);
    }

    // Carrega os dados do convenio do webservice e seta nos TextViews
    public void carregaDadosConvenio() {
        conv = new ConvenioCompleto();
        conv.setAnoAssinatura("2016");
        conv.setAnoConvenio("2016");
        conv.setAnoPublicacao("2016");
        conv.setDataAssinatura("26/03/2016");
        conv.setDataPublicacao("26/02/2016");
        conv.setEstaAssinado(true);
        conv.setEstaEmpenhado(true);
        conv.setEstaPublicado(true);
        conv.setInicioVigencia("26/03/2016");
        conv.setFimVigencia("26/03/2016");
        conv.setJustificativa("Teste de Justificativa");
        conv.setModalidade("Teste de Modalidade");
        conv.setNomePrograma("Minha casa, minha vida");
        conv.setObjeto("Construção de casas populares");

        conv.setCargoResponsavelConcedente("Presidente da República");
        conv.setPermiteAjusteCronogramaFisico(false);
        conv.setPossuiAditivo(true);
        conv.setPossuiProgramaDoOficio(true);

        conv.setMunicipio("Jataí");
        conv.setNomeProponente("Prefeitura Municipal de Jataí");
        conv.setNomeResponsavelProponente("Paulo Henrique Lima Oliveira");
        conv.setQualificacao("Prefeito");
        conv.setRegiao("Centro - Oeste");
        conv.setUf("GO");

        conv.setAnoProposta("2016");
        conv.setDataInicioProposta("26/03/2016");
        conv.setSituacaoConvenio("OK");
        conv.setSituacaoPublicacaoConvenio("PUBLICADO");
        conv.setUltimoEmpenho("26/03/2016");
        conv.setUltimoPagamento("26/03/2016");

        conv.setValorContrapartidaFinanceira("R$ 100.000,00");
        conv.setValorContrapartidaFinanceiraBensEServicos("R$ 50.000,00");
        conv.setValorDesembolsado("R$ 150.000,00");
        conv.setValorGlobal("R$ 150.000,00");
        conv.setValorRepasseUniao("R$ 100.000,00");
        conv.setValorTotalContrapartida("R$ 200.000,00");
    }

    private void setDadosConvenio() {
        // Dados do Convênio
        anoAssinaturaConvenioPublicacao.setText(conv.getAnoAssinatura() + " / " + conv.getAnoConvenio() + " / " + conv.getAnoPublicacao());
        dataAssinatura.setText(conv.getDataAssinatura());
        dataPublicacao.setText(conv.getDataPublicacao());

        String assinado;
        String empenhado;
        String publicado;

        if(conv.isEstaAssinado()) assinado = "SIM";
        else assinado = "NÃO";

        if(conv.isEstaEmpenhado()) empenhado = "SIM";
        else empenhado = "NÃO";

        if(conv.isEstaPublicado()) publicado = "SIM";
        else publicado = "NÃO";

        assinadoEmpenhadoPublicado.setText(assinado + " / " + empenhado + " / " + publicado);
        inicioVigencia.setText(conv.getInicioVigencia());
        fimVigencia.setText(conv.getFimVigencia());
        justificativa.setText(conv.getJustificativa());
        modalidade.setText(conv.getModalidade());
        nomePrograma.setText(conv.getNomePrograma());
        objeto.setText(conv.getObjeto());

        // Orgão Concedente
        cargoConcedenteResponsavel.setText(conv.getCargoResponsavelConcedente());

        String permiteAjuste;
        String aditivo;
        String programaOficio;

        if(conv.isPermiteAjusteCronogramaFisico()) permiteAjuste = "SIM";
        else permiteAjuste = "NÃO";

        if(conv.isPossuiAditivo()) aditivo = "SIM";
        else aditivo = "NÃO";

        if(conv.isPossuiProgramaDoOficio()) programaOficio = "SIM";
        else programaOficio = "NÃO";

        permiteAjusteCronograma.setText(permiteAjuste);
        possuiAditivo.setText(aditivo);
        possuiProgramaOficio.setText(programaOficio);

        // Proponente

        municipio.setText(conv.getMunicipio());
        nomeProponente.setText(conv.getNomeProponente());
        nomeResponsavel.setText(conv.getNomeResponsavelProponente());
        qualificacao.setText(conv.getQualificacao());
        regiao.setText(conv.getRegiao());
        uf.setText(conv.getUf());

        // Proposta
        anoProposta.setText(conv.getAnoProposta());
        dataInicioProposta.setText(conv.getDataInicioProposta());
        situacaoConvenio.setText(conv.getSituacaoConvenio());
        situacaoPublicacaoConvenio.setText(conv.getSituacaoPublicacaoConvenio());
        ultimoEmpenho.setText(conv.getUltimoEmpenho());
        ultimoPagamento.setText(conv.getUltimoPagamento());

        // Valores
        contrapartidaFinanceira.setText(conv.getValorContrapartidaFinanceira());
        contrapartidaFinanceiraBensServicos.setText(conv.getValorContrapartidaFinanceiraBensEServicos());
        valorDesembolsado.setText(conv.getValorDesembolsado());
        valorGlobal.setText(conv.getValorGlobal());
        repasseUniao.setText(conv.getValorRepasseUniao());
        contrapartidaTotal.setText(conv.getValorTotalContrapartida());
    }
}
