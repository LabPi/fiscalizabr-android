package com.paulo.fiscalizabr;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import com.paulo.fiscalizabr.adapter.ConveniosAdapter;
import com.paulo.fiscalizabr.adapter.ViewPagerAdapter;
import com.paulo.fiscalizabr.core.Convenio;

public class MainActivity extends AppCompatActivity {

    public TabLayout tabLayout;
    public ViewPager viewPager;

    // Fragments
    private  ConveniosFragment convenios;

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

}
