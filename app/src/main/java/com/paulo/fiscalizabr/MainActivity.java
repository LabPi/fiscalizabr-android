package com.paulo.fiscalizabr;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.paulo.fiscalizabr.adapter.DialogListViewAdapter;
import com.paulo.fiscalizabr.adapter.ViewPagerAdapter;
import com.paulo.fiscalizabr.connection.DownloadConvenioId;
import com.paulo.fiscalizabr.connection.DownloadConvenios;
import com.paulo.fiscalizabr.core.Convenio;
import com.paulo.fiscalizabr.core.ItemDialog;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public TabLayout tabLayout;
    public ViewPager viewPager;

    private TextView municipioEstado;

    // Fragments
    private  ConveniosFragment convenios;
    private FavoritosFragment favoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpFragments();

        municipioEstado = (TextView) findViewById(R.id.municipio_estado_main_textview);
        // Carrega Municipio/Estado do arquivo de preferências
        municipioEstado.setText("Lavras, MG");

        // Floating Button para pesquisa de convênios
        FloatingActionButton pesquisarButton = (FloatingActionButton) findViewById(R.id.procurar_convenio_button);
        pesquisarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pesquisar = new Intent(getApplicationContext(), PesquisarConvenio.class);
                startActivity(pesquisar);
            }
        });

        setUpViewPager();

    }

    public void setUpFragments() {
        convenios = new ConveniosFragment();
        favoritos = new FavoritosFragment();
    }

    public void setUpViewPager() {
        viewPager = (ViewPager) findViewById(R.id.app_view_pager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(new ConveniosFragment(), "Convênios");
        adapter.addFrag(new FavoritosFragment(), "Favoritos");

        viewPager.setAdapter(adapter);

        tabLayout = (TabLayout) findViewById(R.id.app_tab_layout);
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.editar_localizacao:
                // Compartilha convenio
                Intent editarLocalizacao = new Intent(getApplicationContext(), AlterarLocalizacao.class);
                startActivity(editarLocalizacao);
                return true;
            case R.id.ordenar_lista:
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.listview_dialog);

                ListView dialogListView = (ListView) dialog.findViewById(R.id.dialog_listview);
                DialogListViewAdapter adapter = new DialogListViewAdapter(dialog.getContext());

                ItemDialog itemLista = new ItemDialog(R.drawable.ic_local_atm_black_18dp, "Valor");
                adapter.addItem(itemLista);
                itemLista = new ItemDialog(R.drawable.ic_today_black_36dp, "Vigência");
                adapter.addItem(itemLista);

                dialogListView.setAdapter(adapter);

                dialog.setCancelable(true);
                dialog.setTitle(getResources().getString(R.string.ordenar_lista));
                dialog.show();

                dialogListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if(position == 0) {
                            // Valor
                            Toast.makeText(getApplicationContext(), "Ordena pelo Valor", Toast.LENGTH_SHORT).show();
                            dialog.hide();
                        } else if(position == 1) {
                            Toast.makeText(getApplicationContext(), "Ordena pela Vigência", Toast.LENGTH_SHORT).show();
                            dialog.hide();
                        }
                    }
                });

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
