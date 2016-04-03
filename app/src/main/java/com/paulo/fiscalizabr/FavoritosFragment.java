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
import com.paulo.fiscalizabr.core.Favoritos;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoritosFragment extends Fragment {

    private View view;

    private ListView favoritosListView;
    private FavoritosAdapter adapter;

    public FavoritosFragment() {   }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_convenios, container, false);

        setUpWidgets();

        return view;
    }


    public void setUpWidgets() {
        favoritosListView = (ListView) view.findViewById(R.id.listview_convenios);
        adapter = new FavoritosAdapter(getContext());

        // Se a lista for vazia seta um Convenio qualquer no EmptyList
        adapter.addEmptyList(new Favoritos());

        // Fazer o mesmo com os Favoritos

        favoritosListView.setAdapter(adapter);

        favoritosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getContext(), DetalharConvenio.class);
                startActivity(i);
            }
        });
    }

}
