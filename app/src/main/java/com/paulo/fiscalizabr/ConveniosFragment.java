package com.paulo.fiscalizabr;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.paulo.fiscalizabr.adapter.ConveniosAdapter;
import com.paulo.fiscalizabr.connection.DownloadConvenios;
import com.paulo.fiscalizabr.core.Convenio;
import com.paulo.fiscalizabr.database.DatabaseController;
import com.paulo.fiscalizabr.tools.CheckConnection;
import com.paulo.fiscalizabr.tools.StringsTreatment;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConveniosFragment extends Fragment {

    private View view;

    private ListView conveniosListView;
    public static ConveniosAdapter adapter;
    public static ArrayList<Convenio> listaConvenios = new ArrayList<Convenio>();
    public static StringsTreatment tratamentoString = new StringsTreatment();

    public ConveniosFragment() {   }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_convenios, container, false);

        setUpWidgets();
        carregaDadosBanco();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        carregaDadosBanco();
    }

    public void setUpWidgets() {
        conveniosListView = (ListView) view.findViewById(R.id.listview_convenios);
        adapter = new ConveniosAdapter(getContext());

        adapter.clear();
        adapter.addEmptyList(new Convenio(new Convenio().IS_LOADING));

        conveniosListView.setAdapter(adapter);

        carregaDadosBanco();

        conveniosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (listaConvenios.size() != 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("idConvenio", listaConvenios.get(position).getNumeroConvenio());

                    Intent i = new Intent(getContext(), DetalharConvenio.class);
                    i.putExtras(bundle);
                    startActivity(i);
                } else {
                    carregaDadosBanco();
                }
            }
        });
    }

    public void carregaDadosBanco() {
        CheckConnection conexaoInternet = new CheckConnection(getContext());

        if(conexaoInternet.isConnected() == false) {
            adapter.clear();
            adapter.addEmptyList(new Convenio(new Convenio().NO_INTERNET));
            //Toast.makeText(getContext(), "Ops, estamos sem conexão com a Internet!", Toast.LENGTH_SHORT).show();
        } else {
            // Carrega Municipio/Estado do arquivo de preferências
            listaConvenios.clear();

            DatabaseController database = new DatabaseController(getContext());
            listaConvenios.addAll(database.selectConvenios());
            setUpConvenios(0);
        }
    }

    public static void setUpConvenios(int widget) {
        adapter.clear();

        if(widget == 1) {
            adapter.addEmptyList(new Convenio(new Convenio().IS_LOADING));
        } else {

            if (listaConvenios.size() == 0) {
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

                    if (listaConvenios.get(i).getSituacaoConvenio() == 7) {
                        // verde
                        convenio.setSituacaoConvenio(3);
                    } else if (listaConvenios.get(i).getSituacaoConvenio() == 5) {
                        convenio.setSituacaoConvenio(1);
                    } else {
                        convenio.setSituacaoConvenio(2);
                    }

                    adapter.addItem(convenio);
                }
            }
        }
    }

}
