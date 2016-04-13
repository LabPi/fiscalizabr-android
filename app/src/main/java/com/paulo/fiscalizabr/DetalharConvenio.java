package com.paulo.fiscalizabr;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

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

public class DetalharConvenio extends AppCompatActivity {

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

    public static String idConvenio;
    public DatabaseController database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String scheme = intent.getScheme();

        // Recebe intent de outra Activity
        if ("http".equals(scheme)) {
            Uri data = getIntent().getData();//set a variable for the Intent
            String fullPath = data.getEncodedSchemeSpecificPart();//get the full path -scheme - fragments
            String idConvenio = scheme + fullPath;
            this.idConvenio = idConvenio.substring(48);
        } else {
            // Recebe Intent da Main
            Bundle dados = intent.getExtras();

            String idConvenio = dados.getString("idConvenio");
            this.idConvenio = idConvenio;
        }

        database = new DatabaseController(getApplicationContext());

        setContentView(R.layout.activity_detalhar_convenio);

        dadosConvenio = new ArrayList<DadosConvenio>();

        setUpWidgets();
        carregaDadosConvenio();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detalhar_convenios);
        toolbar.setTitle(getResources().getString(R.string.title_activity_detalhar_convenio));
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        boolean isFavorito = database.isFavorito(idConvenio);
        if(isFavorito) {
            // Menu 2
            inflater.inflate(R.menu.menu_detalhar_convenio_2, menu);
        } else {
            // Menu 1
            inflater.inflate(R.menu.menu_detalhar_convenio, menu);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.compartilhar_convenio:
                String URL = "http://fiscalizabr-dccufla.rhcloud.com/convenios/" + idConvenio;
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, URL);
                startActivity(i);
                return true;
            case R.id.favoritar_convenio:
                boolean isFavorito = database.isFavorito(idConvenio);
                if(isFavorito) {
                    database.removeFavoritos(idConvenio);
                    Toast.makeText(getApplicationContext(), "Convênio removido da lista de Favoritos!", Toast.LENGTH_SHORT).show();
                } else {
                    database.addFavoritos(idConvenio);
                    Toast.makeText(getApplicationContext(), "Convênio adicionado aos Favoritos!", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void setUpWidgets() {
        // Dados do Convênio
        anoAssinaturaConvenioPublicacao = (TextView) findViewById(R.id.assinatura_convenio_publicacao_textview);
        dataAssinatura = (TextView) findViewById(R.id.data_assinatura_textview);
        dataPublicacao = (TextView) findViewById(R.id.data_publicacao_textview);
        inicioVigencia = (TextView) findViewById(R.id.inicio_vigencia_convenio_textview);
        fimVigencia = (TextView) findViewById(R.id.fim_vigencia_convenio_textview);
        justificativa = (TextView) findViewById(R.id.justificativa_textview);
        modalidade = (TextView) findViewById(R.id.modalidade_textview);
        nomePrograma = (TextView) findViewById(R.id.nome_programa_textview);
        numeroConvenio = (TextView) findViewById(R.id.numero_convenio_textview);
        numeroProcesso = (TextView) findViewById(R.id.numero_processo_textview);
        objeto = (TextView) findViewById(R.id.objeto_convenio_textview);
        situacaoPublicacaoConvenio = (TextView) findViewById(R.id.situacao_publicacao_convenio_textview);
        qtdAditivos = (TextView) findViewById(R.id.qtd_aditivos_textview);
        qtdEmpenhos = (TextView) findViewById(R.id.qtd_empenhos_textview);
        qtdProrrogas = (TextView) findViewById(R.id.qtd_prorrogas_textview);
        situacaoConvenio = (TextView) findViewById(R.id.situacao_convenio_textview);
        subSituacaoConvenio = (TextView) findViewById(R.id.subsituacao_convenio_textview);
        situacaoPublicacaoConvenio = (TextView) findViewById(R.id.situacao_publicacao_convenio_textview);
        ultimoPagamento = (TextView) findViewById(R.id.ultimo_pagamento_textview);

        // Orgão Concedente
        cargoConcedenteResponsavel = (TextView) findViewById(R.id.cargo_concedente_responsavel_textview);
        nomeOrgaoConcedente = (TextView) findViewById(R.id.nome_orgao_concedente_textview);
        nomeResponsavelConcedente = (TextView) findViewById(R.id.nome_responsavel_concedente_textview);

        // Orgão Superior
        nomeOrgaoSuperior = (TextView) findViewById(R.id.nome_orgao_superior_textview);

        // Proponente
        bairroProponente = (TextView) findViewById(R.id.bairro_proponente_textview);
        cargoResponsavelProponente = (TextView) findViewById(R.id.cargo_responsavel_proponente_textview);
        cepProponente = (TextView) findViewById(R.id.cep_proponente_textview);
        enderecoProponente = (TextView) findViewById(R.id.endereco_proponente_textview);
        esferaAdministrativa = (TextView) findViewById(R.id.esfera_administrativa_textview);
        municipio = (TextView) findViewById(R.id.municipio_proponente_textview);
        nomeProponente = (TextView) findViewById(R.id.nome_proponente_detalhado_textview);
        nomeResponsavelProponente = (TextView) findViewById(R.id.nome_responsavel_pelo_proponente_textview);
        qualificacao = (TextView) findViewById(R.id.qualificacao_textview);
        regiao = (TextView) findViewById(R.id.regiao_textview);
        uf = (TextView) findViewById(R.id.uf_textview);
        nomeResponsavelProponente = (TextView) findViewById(R.id.nome_responsavel_pelo_proponente_textview);

        // Proposta
        dataInclusaoProposta = (TextView) findViewById(R.id.data_inclusao_proposta_textview);
        numeroProposta = (TextView) findViewById(R.id.numero_proposta_textview);

        // Valores
        contrapartidaFinanceiraBensServicos = (TextView) findViewById(R.id.contrapartida_financeira_bens_servicos_textview);
        valorDesembolsado = (TextView) findViewById(R.id.valor_desembolsado_textview);
        valorEmpenhado = (TextView) findViewById(R.id.valor_empenhado_textview);
        valorGlobal = (TextView) findViewById(R.id.valor_global_detalhado_textview);
        repasseUniao = (TextView) findViewById(R.id.repasse_uniao_textview);
        contrapartidaTotal = (TextView) findViewById(R.id.contrapartida_total_textview);
    }

    // Carrega os dados do convenio do webservice e seta nos TextViews
    public void carregaDadosConvenio() {
        dadosConvenio.clear();
        dadosConvenio.add(database.selectConvenioCompleto(this.idConvenio));
        setDadosConvenio();
    }

    public static void setDadosConvenio() {
        // Pega os dados do WS e seta nas Views
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
