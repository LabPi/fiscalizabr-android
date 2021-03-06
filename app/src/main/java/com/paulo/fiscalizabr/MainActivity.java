package com.paulo.fiscalizabr;

import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
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

import com.paulo.fiscalizabr.adapter.DialogListViewAdapter;
import com.paulo.fiscalizabr.adapter.ViewPagerAdapter;
import com.paulo.fiscalizabr.core.ItemDialog;
import com.paulo.fiscalizabr.database.DatabaseController;
import com.paulo.fiscalizabr.tools.StringsTreatment;

public class MainActivity extends AppCompatActivity {

    public TabLayout tabLayout;
    public ViewPager viewPager;

    private static TextView municipioEstado;

    // Fragments
    private  ConveniosFragment convenios;
    private FavoritosFragment favoritos;

    private StringsTreatment tratamentoString = new StringsTreatment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setUpFragments();

        municipioEstado = (TextView) findViewById(R.id.municipio_estado_main_textview);

        carregaMunicipioUfPreference();

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

    @Override
    protected void onResume()
    {
        super.onResume();
        carregaMunicipioUfPreference();
    }

    public void carregaMunicipioUfPreference() {
        // Carrega Municipio/Estado do arquivo de preferências
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String cidadePreference = sharedPrefs.getString(getString(R.string.preference_cidade), getString(R.string.default_cidade));
        if(cidadePreference.equals("")) cidadePreference = "Brasília";
        String ufPreference = sharedPrefs.getString(getString(R.string.preference_uf), getString(R.string.default_uf));

        municipioEstado.setText(cidadePreference + ", " + ufPreference);
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
        switch (item.getItemId()) {
            case R.id.editar_localizacao:
                Intent intent = new Intent(this.getApplicationContext(), AlterarPreferencias.class);
                startActivity(intent);

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
                        DatabaseController database = new DatabaseController(getApplicationContext());
                        if(position == 0) {
                            // Valor
                            ConveniosFragment.listaConvenios.clear();
                            ConveniosFragment.listaConvenios.addAll(database.ordenaConveniosValor());
                            ConveniosFragment.setUpConvenios(0);
                            dialog.hide();
                        } else if(position == 1) {
                            ConveniosFragment.listaConvenios.clear();
                            ConveniosFragment.listaConvenios.addAll(database.ordenaConveniosVigencia());
                            ConveniosFragment.setUpConvenios(0);
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
