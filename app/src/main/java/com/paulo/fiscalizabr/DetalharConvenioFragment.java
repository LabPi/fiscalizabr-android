package com.paulo.fiscalizabr;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
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
    private TextView numeroConvenio;
    private TextView numeroInterno;
    private TextView numeroProcesso;
    private TextView objeto;

    private TextView qtdAditivos;
    private TextView qtdEmpenhos;
    private TextView qtdProrrogas;
    private TextView situacaoConvenio;
    private TextView subSituacaoConvenio;
    private TextView ultimoEmpenho;
    private TextView ultimoPagamento;

    // Orgão Concedente
    private TextView cargoConcedenteResponsavel;
    private TextView nomeOrgaoConcedente;
    private TextView nomeResponsavelConcedente;

    // Orgão Superior
    private TextView nomeOrgaoSuperior;
    private TextView permiteAjusteCronogramaFisico;
    private TextView possuiAditivo;
    private TextView prorrogasDeOficio;

    // Proponente
    private TextView bairroProponente;
    private TextView cargoResponsavelProponente;
    private TextView cepProponente;
    private TextView enderecoProponente;
    private TextView esferaAdministrativa;
    private TextView municipio;
    private TextView nomeProponente;
    private TextView qualificacao;
    private TextView regiao;
    private TextView uf;

    // Proposta
    private TextView anoProposta;
    private TextView dataInclusaoProposta;
    private TextView situacaoProposta;
    private TextView numeroProposta;

    // Valores
    private TextView contrapartidaFinanceiraBensServicos;
    private TextView valorDesembolsado;
    private TextView valorEmpenhado;
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
    }

    private void setDadosConvenio() {
        // Pega os dados do WS e seta aqui
    }
}
