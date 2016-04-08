package com.paulo.fiscalizabr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.paulo.fiscalizabr.adapter.ConveniosAdapter;
import com.paulo.fiscalizabr.connection.DownloadConveniosParametros;
import com.paulo.fiscalizabr.core.Convenio;
import com.paulo.fiscalizabr.tools.CheckConnection;
import com.paulo.fiscalizabr.tools.StringsTreatment;

import java.util.ArrayList;

public class ConveniosPesquisaDetalhada extends AppCompatActivity {

    private ListView conveniosListView;
    public static ConveniosAdapter adapter;
    public static ArrayList<Convenio> listaConvenios = new ArrayList<Convenio>();
    public static StringsTreatment tratamentoString = new StringsTreatment();

    // valorMinimo, valorMaximo, inicioVigencia, fimVigencia, situacaoConvenio
    private String valorMinimo;
    private String valorMaximo;
    private String inicioVigencia;
    private String fimVigencia;
    private String situacaoConvenio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convenios_pesquisa_detalhada);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle bundle;
        bundle = getIntent().getExtras();

        valorMinimo = bundle.getString("valorMinimo");
        valorMaximo = bundle.getString("valorMaximo");
        inicioVigencia = bundle.getString("inicioVigencia");
        fimVigencia = bundle.getString("fimVigencia");
        situacaoConvenio = bundle.getString("situacaoConvenio");

        // Só carrega dados do servidor se a listaConvenios estiver vazia
        //if(listaConvenios.isEmpty()) {
          //  carregaDadosServidor();
        //}

        setUpWidgets();
    }

    public void setUpWidgets() {
        conveniosListView = (ListView) findViewById(R.id.listview_convenios_resultados_pesquisa);
        adapter = new ConveniosAdapter(getApplicationContext());

        adapter.clear();
        adapter.addEmptyList(new Convenio(new Convenio().IS_LOADING));

        conveniosListView.setAdapter(adapter);

        carregaDadosServidor();

        conveniosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listaConvenios.size() != 0) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("idConvenio", listaConvenios.get(position).getNumeroConvenio());

                    Intent i = new Intent(getApplicationContext(), DetalharConvenio.class);
                    i.putExtras(bundle);
                    startActivity(i);
                } else {
                    carregaDadosServidor();
                }
            }
        });
    }

    public void carregaDadosServidor() {
        CheckConnection conexaoInternet = new CheckConnection(getApplicationContext());
        if(conexaoInternet.isConnected() == false) {
            adapter.clear();
            adapter.addEmptyList(new Convenio(new Convenio().NO_INTERNET));
            //Toast.makeText(getContext(), "Ops, estamos sem conexão com a Internet!", Toast.LENGTH_SHORT).show();
        } else {
            // Carrega Municipio/Estado do arquivo de preferências
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            String cidadePreference = sharedPrefs.getString(getString(R.string.preference_cidade), getString(R.string.default_cidade));
            String ufPreference = sharedPrefs.getString(getString(R.string.preference_uf), getString(R.string.default_uf));

            DownloadConveniosParametros parametros = new DownloadConveniosParametros(this, tratamentoString.normalizaString(cidadePreference), ufPreference, valorMinimo, valorMaximo, inicioVigencia, fimVigencia, situacaoConvenio);
            parametros.execute();
        }
    }

    public static void setUpConvenios() {
        adapter.clear();
        if(listaConvenios.size() == 0) {
            adapter.addEmptyList(new Convenio(new Convenio().RESULT_IS_NULL));
        } else {
            for (int i = 0; i < listaConvenios.size(); i++) {
                Convenio convenio = new Convenio();

                convenio.setObjetoConvenio(listaConvenios.get(i).getObjetoConvenio());
                convenio.setValorConvenio(tratamentoString.converteValor(listaConvenios.get(i).getValorConvenio()));
                String vigenciaInicio = "", vigenciaFinal = "";
                vigenciaInicio = tratamentoString.converteAnoMes(listaConvenios.get(i).getMesVigencia(), listaConvenios.get(i).getAnoVigencia());
                vigenciaFinal = tratamentoString.converteAnoMes(listaConvenios.get(i).getMesFinalVigencia(), listaConvenios.get(i).getAnoFinalVigencia());

                convenio.setVigencia(vigenciaInicio + " à " + vigenciaFinal);
                convenio.setIsFavorito(false);
                if(listaConvenios.get(i).getSituacaoConvenio() == 7) {
                    // verde
                    convenio.setSituacaoConvenio(3);
                } else if(listaConvenios.get(i).getSituacaoConvenio() == 5) {
                    convenio.setSituacaoConvenio(1);
                } else {
                    convenio.setSituacaoConvenio(2);
                }

                adapter.addItem(convenio);
            }
        }
    }
}
