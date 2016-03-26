package com.paulo.fiscalizabr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.paulo.fiscalizabr.adapter.ConveniosAdapter;
import com.paulo.fiscalizabr.core.Convenio;

public class MainActivity extends AppCompatActivity {

    private ListView conveniosListView;
    private ConveniosAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Floating Button para pesquisa de convênios
        FloatingActionButton pesquisarButton = (FloatingActionButton) findViewById(R.id.procurar_convenio_button);
        pesquisarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Pesquisa de convênios", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        setUpWidgets();

    }

    public void setUpWidgets() {
        conveniosListView = (ListView) findViewById(R.id.listview_convenios);
        adapter = new ConveniosAdapter(getApplicationContext());

        for(int i=0; i<10; i++) {
            Convenio convenio = new Convenio();

            if(i%2 == 0) {
                convenio.setNomeProponente("Prefeitura Municipal de Jataí");
                convenio.setMunicipio("Jataí");
                convenio.setUf("GO");
            } else {
                convenio.setNomeProponente("Prefeitura Municipal de Lavras");
                convenio.setMunicipio("Lavras");
                convenio.setUf("MG");
            }
            convenio.setObjeto("Compra de ambulâncias");
            convenio.setValorGlobal("R$ 50.000,00");
            convenio.setInicioVigencia("25/03/2016");

            adapter.addItem(convenio);
        }

        conveniosListView.setAdapter(adapter);

        conveniosListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(getApplicationContext(), DetalharConvenio.class);
                startActivity(i);
            }
        });
    }

}
