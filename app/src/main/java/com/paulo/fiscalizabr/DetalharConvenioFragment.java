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
import com.paulo.fiscalizabr.database.DatabaseController;
import com.paulo.fiscalizabr.enums.EsferaAdministrativa;
import com.paulo.fiscalizabr.enums.Modalidade;
import com.paulo.fiscalizabr.enums.Qualificacao;
import com.paulo.fiscalizabr.enums.Regiao;
import com.paulo.fiscalizabr.enums.SituacaoConvenio;
import com.paulo.fiscalizabr.enums.SubsituacaoConvenio;
import com.paulo.fiscalizabr.enums.UF;
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
    private static TextView inicioVigencia;
    private static TextView fimVigencia;
    private static TextView justificativa;
    private static TextView modalidade;
    private static TextView nomePrograma;
    private static TextView numeroConvenio;
    private static TextView numeroProcesso;
    private static TextView objeto;
    private static TextView situacaoPublicacaoConvenio;

    private static TextView qtdAditivos;
    private static TextView qtdEmpenhos;
    private static TextView qtdProrrogas;
    private static TextView situacaoConvenio;
    private static TextView subSituacaoConvenio;
    private static TextView ultimoPagamento;

    // Orgão Concedente
    private static TextView cargoConcedenteResponsavel;
    private static TextView nomeOrgaoConcedente;
    private static TextView nomeResponsavelConcedente;

    // Orgão Superior
    private static TextView nomeOrgaoSuperior;

    // Proponente
    private static TextView bairroProponente;
    private static TextView cargoResponsavelProponente;
    private static TextView cepProponente;
    private static TextView enderecoProponente;
    private static TextView esferaAdministrativa;
    private static TextView municipio;
    private static TextView nomeProponente;
    private static TextView nomeResponsavelProponente;
    private static TextView qualificacao;
    private static TextView regiao;
    private static TextView uf;

    // Proposta
    private static TextView dataInclusaoProposta;
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
        inicioVigencia = (TextView) view.findViewById(R.id.inicio_vigencia_convenio_textview);
        fimVigencia = (TextView) view.findViewById(R.id.fim_vigencia_convenio_textview);
        justificativa = (TextView) view.findViewById(R.id.justificativa_textview);
        modalidade = (TextView) view.findViewById(R.id.modalidade_textview);
        nomePrograma = (TextView) view.findViewById(R.id.nome_programa_textview);
        numeroConvenio = (TextView) view.findViewById(R.id.numero_convenio_textview);
        numeroProcesso = (TextView) view.findViewById(R.id.numero_processo_textview);
        objeto = (TextView) view.findViewById(R.id.objeto_convenio_textview);
        situacaoPublicacaoConvenio = (TextView) view.findViewById(R.id.situacao_publicacao_convenio_textview);
        qtdAditivos = (TextView) view.findViewById(R.id.qtd_aditivos_textview);
        qtdEmpenhos = (TextView) view.findViewById(R.id.qtd_empenhos_textview);
        qtdProrrogas = (TextView) view.findViewById(R.id.qtd_prorrogas_textview);
        situacaoConvenio = (TextView) view.findViewById(R.id.situacao_convenio_textview);
        subSituacaoConvenio = (TextView) view.findViewById(R.id.subsituacao_convenio_textview);
        situacaoPublicacaoConvenio = (TextView) view.findViewById(R.id.situacao_publicacao_convenio_textview);
        ultimoPagamento = (TextView) view.findViewById(R.id.ultimo_pagamento_textview);

        // Orgão Concedente
        cargoConcedenteResponsavel = (TextView) view.findViewById(R.id.cargo_concedente_responsavel_textview);
        nomeOrgaoConcedente = (TextView) view.findViewById(R.id.nome_orgao_concedente_textview);
        nomeResponsavelConcedente = (TextView) view.findViewById(R.id.nome_responsavel_concedente_textview);

        // Orgão Superior
        nomeOrgaoSuperior = (TextView) view.findViewById(R.id.nome_orgao_superior_textview);

        // Proponente
        bairroProponente = (TextView) view.findViewById(R.id.bairro_proponente_textview);
        cargoResponsavelProponente = (TextView) view.findViewById(R.id.cargo_responsavel_proponente_textview);
        cepProponente = (TextView) view.findViewById(R.id.cep_proponente_textview);
        enderecoProponente = (TextView) view.findViewById(R.id.endereco_proponente_textview);
        esferaAdministrativa = (TextView) view.findViewById(R.id.esfera_administrativa_textview);
        municipio = (TextView) view.findViewById(R.id.municipio_proponente_textview);
        nomeProponente = (TextView) view.findViewById(R.id.nome_proponente_detalhado_textview);
        nomeResponsavelProponente = (TextView) view.findViewById(R.id.nome_responsavel_pelo_proponente_textview);
        qualificacao = (TextView) view.findViewById(R.id.qualificacao_textview);
        regiao = (TextView) view.findViewById(R.id.regiao_textview);
        uf = (TextView) view.findViewById(R.id.uf_textview);
        nomeResponsavelProponente = (TextView) view.findViewById(R.id.nome_responsavel_pelo_proponente_textview);

        // Proposta
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
        DatabaseController database = new DatabaseController(getContext());
        dadosConvenio.clear();
        dadosConvenio.add(database.selectConvenioCompleto(DetalharConvenio.idConvenio));
        setDadosConvenio();
    }

    public static void setDadosConvenio() {
        // Pega os dados do WS e seta aqui
        anoAssinaturaConvenioPublicacao.setText(dadosConvenio.get(0).getAnoAssinatura() + " / " + dadosConvenio.get(0).getAnoConvenio() + " / " + dadosConvenio.get(0).getAnoPublicacao());
        dataAssinatura.setText(dadosConvenio.get(0).getDataAssinatura());
        dataPublicacao.setText(dadosConvenio.get(0).getDataPublicacao());

        inicioVigencia.setText(dadosConvenio.get(0).getInicioVigencia());
        fimVigencia.setText(dadosConvenio.get(0).getFimVigencia());
        justificativa.setText(dadosConvenio.get(0).getJustificativa());
        modalidade.setText(Modalidade.valueOf(dadosConvenio.get(0).getModalidade()).getDescricao());
        nomePrograma.setText(dadosConvenio.get(0).getNomePrograma());
        numeroConvenio.setText(String.valueOf(dadosConvenio.get(0).getNumeroConvenio()));
        numeroProcesso.setText(dadosConvenio.get(0).getNumeroProcesso());
        objeto.setText(dadosConvenio.get(0).getObjeto());
        situacaoPublicacaoConvenio.setText(dadosConvenio.get(0).getSituacaoPublicacaoConvenio());

        qtdAditivos.setText(String.valueOf(dadosConvenio.get(0).getQuantidadeAditivos()));
        qtdEmpenhos.setText(String.valueOf(dadosConvenio.get(0).getQuantidadeEmpenhos()));
        qtdProrrogas.setText(String.valueOf(dadosConvenio.get(0).getQuantidadeProrrogas()));
        situacaoConvenio.setText(SituacaoConvenio.valueOf(dadosConvenio.get(0).getSituacaoConvenio()).getDescricao());
        subSituacaoConvenio.setText(SubsituacaoConvenio.valueOf(dadosConvenio.get(0).getSubsituacaoConvenio()).getDescricao());
        ultimoPagamento.setText(dadosConvenio.get(0).getUltimoPagamento());

        cargoConcedenteResponsavel.setText(dadosConvenio.get(0).getOrgaoConcedente().getCargoResponsavelConcedente());
        nomeOrgaoConcedente.setText(dadosConvenio.get(0).getOrgaoConcedente().getNomeOrgaoConcedente());
        nomeResponsavelConcedente.setText(dadosConvenio.get(0).getOrgaoConcedente().getNomeResponsavelConcedente());

        nomeOrgaoSuperior.setText(dadosConvenio.get(0).getOrgaoSuperior().getNomeOrgaoSuperior());

        bairroProponente.setText(dadosConvenio.get(0).getProponente().getBairroProponente());
        cargoResponsavelProponente.setText(dadosConvenio.get(0).getProponente().getCargoResponsavelProponente());
        cepProponente.setText(dadosConvenio.get(0).getProponente().getCepProponente());
        enderecoProponente.setText(dadosConvenio.get(0).getProponente().getEnderecoProponente());
        esferaAdministrativa.setText(EsferaAdministrativa.valueOf(dadosConvenio.get(0).getProponente().getEsferaAdministrativa()).getDescricao());
        municipio.setText(dadosConvenio.get(0).getProponente().getMunicipio());
        nomeProponente.setText(dadosConvenio.get(0).getProponente().getNomeProponente());
        qualificacao.setText(Qualificacao.valueOf(dadosConvenio.get(0).getProponente().getQualificacao()).getDescricao());
        regiao.setText(Regiao.valueOf(dadosConvenio.get(0).getProponente().getRegiao()).getDescricao());
        uf.setText(UF.valueOf(dadosConvenio.get(0).getProponente().getUf()).getDescricao());
        nomeResponsavelProponente.setText(dadosConvenio.get(0).getProponente().getNomeResponsavelProponente());

        dataInclusaoProposta.setText(dadosConvenio.get(0).getProposta().getDataInclusao());
        numeroProposta.setText(String.valueOf(dadosConvenio.get(0).getProposta().getNumeroProposta()));

        contrapartidaFinanceiraBensServicos.setText(tratamentoString.converteValor(String.valueOf(dadosConvenio.get(0).getValorConvenio().getValorContrapartidaFinanceiraBensEServicos())));
        valorDesembolsado.setText(tratamentoString.converteValor(String.valueOf(dadosConvenio.get(0).getValorConvenio().getValorDesembolsado())));
            valorEmpenhado.setText(tratamentoString.converteValor(String.valueOf(dadosConvenio.get(0).getValorConvenio().getValorEmpenhado())));
        valorGlobal.setText(tratamentoString.converteValor(String.valueOf(dadosConvenio.get(0).getValorConvenio().getValorGlobal())));
        repasseUniao.setText(tratamentoString.converteValor(String.valueOf(dadosConvenio.get(0).getValorConvenio().getValorRepasseUniao())));
        contrapartidaTotal.setText(tratamentoString.converteValor(String.valueOf(dadosConvenio.get(0).getValorConvenio().getValorTotalContrapartida())));
    }

}