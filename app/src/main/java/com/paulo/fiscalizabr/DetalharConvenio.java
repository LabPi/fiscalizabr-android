package com.paulo.fiscalizabr;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class DetalharConvenio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhar_convenio);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detalhar_convenios);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detalhar_convenio, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.compartilhar_convenio:
                // Compartilha convenio
                Toast.makeText(getApplicationContext(), "Compartilhar Convênio", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.favoritar_convenio:
                // Adiciona aos favoritos
                Toast.makeText(getApplicationContext(), "Adicionar aos Favoritos", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
