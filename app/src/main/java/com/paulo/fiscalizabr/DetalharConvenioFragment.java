package com.paulo.fiscalizabr;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.paulo.fiscalizabr.connection.DownloadConvenioId;
import com.paulo.fiscalizabr.core.DadosConvenio;
import com.paulo.fiscalizabr.enums.Modalidade;
import com.paulo.fiscalizabr.tools.StringsTreatment;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class DetalharConvenioFragment extends Fragment {

    private View view;
    public static ArrayList<DadosConvenio> dadosConvenio;
    public static StringsTreatment tratamentoString = new StringsTreatment();

    // Dados do Convênio
    private static TextView anoAssinaturaConvenioPublicacao;
    private static TextView dataAssinatura;
    private static TextView dataPublicacao;
    private static TextView assinadoEmpenhadoPublicado;
    private static TextView inicioVigencia;
    private static TextView fimVigencia;
    private static TextView justificativa;
    private static TextView modalidade;
    private static TextView nomePrograma;
    private static TextView numeroConvenio;
    private static TextView numeroInterno;
    private static TextView numeroProcesso;
    private static TextView objeto;

    private static TextView qtdAditivos;
    private static TextView qtdEmpenhos;
    private static TextView qtdProrrogas;
    private static TextView situacaoConvenio;
    private static TextView subSituacaoConvenio;
    private static TextView ultimoEmpenho;
    private static TextView ultimoPagamento;

    // Orgão Concedente
    private static TextView cargoConcedenteResponsavel;
    private static TextView nomeOrgaoConcedente;
    private static TextView nomeResponsavelConcedente;

    // Orgão Superior
    private static TextView nomeOrgaoSuperior;
    private static TextView permiteAjusteCronogramaFisico;
    private static TextView possuiAditivo;
    private static TextView prorrogasDeOficio;

    // Proponente
    private static TextView bairroProponente;
    private static TextView cargoResponsavelProponente;
    private static TextView cepProponente;
    private static TextView enderecoProponente;
    private static TextView esferaAdministrativa;
    private static TextView municipio;
    private static TextView nomeProponente;
    private static TextView qualificacao;
    private static TextView regiao;
    private static TextView uf;

    // Proposta
    private static TextView anoProposta;
    private static TextView dataInclusaoProposta;
    private static TextView situacaoProposta;
    private static TextView numeroProposta;

    // Valores
    private static TextView contrapartidaFinanceiraBensServicos;
    private static TextView valorDesembolsado;
    private static TextView valorEmpenhado;
    private static TextView valorGlobal;
    private static TextView repasseUniao;
    private static TextView contrapartidaTotal;

    public DetalharConvenioFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_detalhar_convenio, container, false);

        dadosConvenio = new ArrayList<DadosConvenio>();

        setUpWidgets();
        carregaDadosConvenio();

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
        numeroConvenio = (TextView) view.findViewById(R.id.numero_convenio_textview);
        numeroInterno = (TextView) view.findViewById(R.id.numero_interno_textview);
        numeroProcesso = (TextView) view.findViewById(R.id.numero_processo_textview);
        objeto = (TextView) view.findViewById(R.id.objeto_convenio_textview);
        qtdAditivos = (TextView) view.findViewById(R.id.qtd_aditivos_textview);
        qtdEmpenhos = (TextView) view.findViewById(R.id.qtd_empenhos_textview);
        qtdProrrogas = (TextView) view.findViewById(R.id.qtd_prorrogas_textview);
        situacaoConvenio = (TextView) view.findViewById(R.id.situacao_convenio_textview);
        subSituacaoConvenio = (TextView) view.findViewById(R.id.subsituacao_convenio_textview);
        ultimoEmpenho = (TextView) view.findViewById(R.id.ultimo_empenho_textview);
        ultimoPagamento = (TextView) view.findViewById(R.id.ultimo_pagamento_textview);

        // Orgão Concedente
        cargoConcedenteResponsavel = (TextView) view.findViewById(R.id.cargo_concedente_responsavel_textview);
        nomeOrgaoConcedente = (TextView) view.findViewById(R.id.nome_orgao_concedente_textview);
        nomeResponsavelConcedente = (TextView) view.findViewById(R.id.nome_responsavel_concedente_textview);

        // Orgão Superior
        nomeOrgaoSuperior = (TextView) view.findViewById(R.id.nome_orgao_superior_textview);
        permiteAjusteCronogramaFisico = (TextView) view.findViewById(R.id.permite_ajuste_cronograma_fisico_textview);
        possuiAditivo = (TextView) view.findViewById(R.id.possui_aditivo_textview);
        prorrogasDeOficio = (TextView) view.findViewById(R.id.prorrogas_de_oficio_textview);

        // Proponente
        bairroProponente = (TextView) view.findViewById(R.id.bairro_proponente_textview);
        cargoResponsavelProponente = (TextView) view.findViewById(R.id.cargo_responsavel_proponente_textview);
        cepProponente = (TextView) view.findViewById(R.id.cep_proponente_textview);
        enderecoProponente = (TextView) view.findViewById(R.id.endereco_proponente_textview);
        esferaAdministrativa = (TextView) view.findViewById(R.id.esfera_administrativa_textview);
        municipio = (TextView) view.findViewById(R.id.municipio_proponente_textview);
        nomeProponente = (TextView) view.findViewById(R.id.nome_proponente_detalhado_textview);
        qualificacao = (TextView) view.findViewById(R.id.qualificacao_textview);
        regiao = (TextView) view.findViewById(R.id.regiao_textview);
        uf = (TextView) view.findViewById(R.id.uf_textview);

        // Proposta
        anoProposta = (TextView) view.findViewById(R.id.ano_proposta_textview);
        dataInclusaoProposta = (TextView) view.findViewById(R.id.data_inclusao_proposta_textview);
        numeroProposta = (TextView) view.findViewById(R.id.numero_proposta_textview);

        // Valores
        contrapartidaFinanceiraBensServicos = (TextView) view.findViewById(R.id.contrapartida_financeira_bens_servicos_textview);
        valorDesembolsado = (TextView) view.findViewById(R.id.valor_desembolsado_textview);
        valorEmpenhado = (TextView) view.findViewById(R.id.valor_empenhado_textview);
        valorGlobal = (TextView) view.findViewById(R.id.valor_global_detalhado_textview);
        repasseUniao = (TextView) view.findViewById(R.id.repasse_uniao_textview);
        contrapartidaTotal = (TextView) view.findViewById(R.id.contrapartida_total_textview);
    }

    // Carrega os dados do convenio do webservice e seta nos TextViews
    public void carregaDadosConvenio() {
        // Carrega do WS
        DownloadConvenioId downloadConvenioId = new DownloadConvenioId(getContext(), DetalharConvenio.idConvenio);
        downloadConvenioId.execute();
    }

    public static void setDadosConvenio() {
        // Pega os dados do WS e seta aqui
        anoAssinaturaConvenioPublicacao.setText(dadosConvenio.get(0).getAnoAssinatura() + " / " + dadosConvenio.get(0).getAnoConvenio() + " / " + dadosConvenio.get(0).getAnoPublicacao());
        dataAssinatura.setText(dadosConvenio.get(0).getDataAssinatura());
        dataPublicacao.setText(dadosConvenio.get(0).getDataPublicacao());

        String assinado, empenhado, publicado;
        if (dadosConvenio.get(0).isEstaAssinado()) assinado = "SIM";
        else assinado = "NÃO";

        if (dadosConvenio.get(0).isEstaEmpenhado()) empenhado = "SIM";
        else empenhado = "NÃO";

        if (dadosConvenio.get(0).isEstaPublicado()) publicado = "SIM";
        else publicado = "NÃO";

        assinadoEmpenhadoPublicado.setText(assinado + " / " + empenhado + " / " + publicado);
        inicioVigencia.setText(dadosConvenio.get(0).getInicioVigencia());
        fimVigencia.setText(dadosConvenio.get(0).getFimVigencia());
        justificativa.setText(dadosConvenio.get(0).getJustificativa());
        modalidade.setText(dadosConvenio.get(0).getModalidade());
        nomePrograma.setText(dadosConvenio.get(0).getNomePrograma());
        numeroConvenio.setText(String.valueOf(dadosConvenio.get(0).getNumeroConvenio()));
        numeroInterno.setText(dadosConvenio.get(0).getNumeroInterno());
        numeroProcesso.setText(dadosConvenio.get(0).getNumeroProcesso());
        objeto.setText(dadosConvenio.get(0).getObjeto());

        qtdAditivos.setText(String.valueOf(dadosConvenio.get(0).getQuantidadeAditivos()));
        qtdEmpenhos.setText(String.valueOf(dadosConvenio.get(0).getQuantidadeEmpenhos()));
        qtdProrrogas.setText(String.valueOf(dadosConvenio.get(0).getQuantidadeProrrogas()));
        situacaoConvenio.setText(dadosConvenio.get(0).getSituacaoConvenio());
        subSituacaoConvenio.setText(dadosConvenio.get(0).getSubsituacaoConvenio());
        ultimoEmpenho.setText(dadosConvenio.get(0).getUltimoEmpenho());
        ultimoPagamento.setText(dadosConvenio.get(0).getUltimoPagamento());

        cargoConcedenteResponsavel.setText(dadosConvenio.get(0).getOrgaoConcedente().getCargoResponsavelConcedente());
        nomeOrgaoConcedente.setText(dadosConvenio.get(0).getOrgaoConcedente().getNomeOrgaoConcedente());
        nomeResponsavelConcedente.setText(dadosConvenio.get(0).getOrgaoConcedente().getNomeResponsavelConcedente());

        nomeOrgaoSuperior.setText(dadosConvenio.get(0).getOrgaoSuperior().getNomeOrgaoSuperior());

        String ajuste, aditivo, oficio;

        if (dadosConvenio.get(0).isPermiteAjusteNoCronogramaFisico()) ajuste = "SIM";
        else ajuste = "NÃO";

        if (dadosConvenio.get(0).isPossuiAditivo()) aditivo = "SIM";
        else aditivo = "NÃO";

        if (dadosConvenio.get(0).isPossuiProrrogaDeOficio()) oficio = "SIM";
        else oficio = "NÃO";

        permiteAjusteCronogramaFisico.setText(ajuste);
        possuiAditivo.setText(aditivo);
        prorrogasDeOficio.setText(oficio);

        bairroProponente.setText(dadosConvenio.get(0).getProponente().getBairroProponente());
        cargoResponsavelProponente.setText(dadosConvenio.get(0).getProponente().getCargoResponsavelProponente());
        cepProponente.setText(dadosConvenio.get(0).getProponente().getCepProponente());
        enderecoProponente.setText(dadosConvenio.get(0).getProponente().getEnderecoProponente());
        esferaAdministrativa.setText(dadosConvenio.get(0).getProponente().getEsferaAdministrativa());
        municipio.setText(dadosConvenio.get(0).getProponente().getMunicipio());
        nomeProponente.setText(dadosConvenio.get(0).getProponente().getNomeProponente());
        qualificacao.setText(dadosConvenio.get(0).getProponente().getQualificacao());
        regiao.setText(dadosConvenio.get(0).getProponente().getRegiao());
        uf.setText(dadosConvenio.get(0).getProponente().getUf());

        anoProposta.setText(String.valueOf(dadosConvenio.get(0).getProposta().getAnoProposta()));
        dataInclusaoProposta.setText(dadosConvenio.get(0).getProposta().getDataInclusaoProposta());
        numeroProposta.setText(String.valueOf(dadosConvenio.get(0).getProposta().getNumeroProposta()));

        contrapartidaFinanceiraBensServicos.setText(tratamentoString.converteValor(String.valueOf(dadosConvenio.get(0).getValorConvenio().getValorContrapartidaFinanceiraBensEServicos())));
        valorDesembolsado.setText(tratamentoString.converteValor(String.valueOf(dadosConvenio.get(0).getValorConvenio().getValorDesembolsado())));
        valorEmpenhado.setText(tratamentoString.converteValor(String.valueOf(dadosConvenio.get(0).getValorConvenio().getValorEmpenhado())));
        valorGlobal.setText(tratamentoString.converteValor(String.valueOf(dadosConvenio.get(0).getValorConvenio().getValorGlobal())));
        repasseUniao.setText(tratamentoString.converteValor(String.valueOf(dadosConvenio.get(0).getValorConvenio().getValorRepasseUniao())));
        contrapartidaTotal.setText(tratamentoString.converteValor(String.valueOf(dadosConvenio.get(0).getValorConvenio().getValorTotalContrapartida())));
    }

}