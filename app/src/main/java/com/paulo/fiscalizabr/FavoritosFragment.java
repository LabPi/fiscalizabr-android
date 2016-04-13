package com.paulo.fiscalizabr;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.paulo.fiscalizabr.adapter.FavoritosAdapter;
import com.paulo.fiscalizabr.core.Convenio;
import com.paulo.fiscalizabr.core.Favoritos;
import com.paulo.fiscalizabr.database.DatabaseController;
import com.paulo.fiscalizabr.tools.StringsTreatment;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends Fragment {

    private View view;

    public ArrayList<Convenio> listaFavoritos = new ArrayList<Convenio>();
    public ListView favoritosListView;
    public FavoritosAdapter adapter;
    public StringsTreatment tratamentoString;

    public FavoritosFragment() {   }


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
        favoritosListView = (ListView) view.findViewById(R.id.listview_convenios);
        adapter = new FavoritosAdapter(getContext());

        adapter.addEmptyList(new Favoritos());
        favoritosListView.setAdapter(adapter);

        favoritosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listaFavoritos.size() != 0) {
                    Bundle bundle = new Bundle();
                    bundle.putString("idConvenio", listaFavoritos.get(position).getNumeroConvenio());

                    Intent i = new Intent(getContext(), DetalharConvenio.class);
                    i.putExtras(bundle);
                    startActivity(i);
                }
            }
        });
    }

    public void carregaDadosBanco() {
        listaFavoritos.clear();
        DatabaseController database = new DatabaseController(getContext());
        listaFavoritos.addAll(database.selectFavoritos());
        setUpFavoritos();
    }

    public void setUpFavoritos() {
        adapter.clear();

            if (listaFavoritos.size() == 0) {
                adapter.addEmptyList(new Favoritos(new Favoritos().RESULT_IS_NULL));
            } else {
                for (int i = 0; i < listaFavoritos.size(); i++) {
                    Favoritos convenio = new Favoritos();

                    convenio.setObjetoConvenio(listaFavoritos.get(i).getObjetoConvenio());
                    convenio.setValorConvenio(tratamentoString.converteValor(listaFavoritos.get(i).getValorConvenio()));
                    String vigenciaInicio = "", vigenciaFinal = "";
                    vigenciaInicio = tratamentoString.converteAnoMes(listaFavoritos.get(i).getMesVigencia(), listaFavoritos.get(i).getAnoVigencia());
                    vigenciaFinal = tratamentoString.converteAnoMes(listaFavoritos.get(i).getMesFinalVigencia(), listaFavoritos.get(i).getAnoFinalVigencia());

                    convenio.setVigencia(vigenciaInicio + " à " + vigenciaFinal);
                    convenio.setMunicipioUf(listaFavoritos.get(i).getMunicipio() + ", " + listaFavoritos.get(i).getUf());

                    if (listaFavoritos.get(i).getSituacaoConvenio() == 7) {
                        // verde
                        convenio.setSituacaoConvenio(3);
                    } else if (listaFavoritos.get(i).getSituacaoConvenio() == 5) {
                        convenio.setSituacaoConvenio(1);
                    } else {
                        convenio.setSituacaoConvenio(2);
                    }

                    adapter.addItem(convenio);
                }
            }

    }

}
