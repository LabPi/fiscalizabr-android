package com.paulo.fiscalizabr;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.paulo.fiscalizabr.adapter.ConveniosAdapter;
import com.paulo.fiscalizabr.core.Convenio;


/**
 * A simple {@link Fragment} subclass.
 */
public class ConveniosFragment extends Fragment {

    private View view;

    private ListView conveniosListView;
    private ConveniosAdapter adapter;

    public ConveniosFragment() {   }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_convenios, container, false);

        setUpWidgets();

        return view;
    }


    public void setUpWidgets() {
        conveniosListView = (ListView) view.findViewById(R.id.listview_convenios);
        adapter = new ConveniosAdapter(getContext());

        for(int i=0; i<10; i++) {
            Convenio convenio = new Convenio();

            if(i%2 == 0) {
                convenio.setObjetoConvenio("Compra de ambulâncias");
                convenio.setValorConvenio("$ 500 mi");
                convenio.setVigencia("Jan/15 á Dez/17");
                convenio.setIsFavorito(true);
            } else {
                convenio.setObjetoConvenio("Pavimentação UFG");
                convenio.setValorConvenio("$ 750 mil");
                convenio.setVigencia("Abr/15 á Mai/17");
                convenio.setIsFavorito(false);
            }

            adapter.addItem(convenio);
        }

        conveniosListView.setAdapter(adapter);

        conveniosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getContext(), DetalharConvenio.class);
                startActivity(i);
            }
        });
    }

}
