package com.paulo.fiscalizabr;

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

import com.paulo.fiscalizabr.adapter.ConveniosAdapter;
import com.paulo.fiscalizabr.adapter.ViewPagerAdapter;
import com.paulo.fiscalizabr.core.Convenio;

public class MainActivity extends AppCompatActivity {

    public TabLayout tabLayout;
    public ViewPager viewPager;

    private TextView municipioEstado;

    // Fragments
    private  ConveniosFragment convenios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

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

        setUpFragments();
        setUpViewPager();

    }

    public void setUpFragments() {
        convenios = new ConveniosFragment();
    }

    public void setUpViewPager() {
        viewPager = (ViewPager) findViewById(R.id.app_view_pager);

        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFrag(new ConveniosFragment(), "Convênios");
        adapter.addFrag(new ConveniosFragment(), "Favoritos");

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
                // Adiciona aos favoritos
                Toast.makeText(getApplicationContext(), "Ordenar Lista", Toast.LENGTH_SHORT).show();
                // Abre Dialog
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
