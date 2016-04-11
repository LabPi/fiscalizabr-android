package com.paulo.fiscalizabr;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.paulo.fiscalizabr.database.DatabaseController;

public class DetalharConvenio extends AppCompatActivity {

    public static String idConvenio;
    public DatabaseController database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = getIntent();
        Bundle dados = i.getExtras();

        String idConvenio = dados.getString("idConvenio");
        this.idConvenio = idConvenio;

        database = new DatabaseController(getApplicationContext());

        setContentView(R.layout.activity_detalhar_convenio);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detalhar_convenios);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        boolean isFavorito = database.isFavorito(idConvenio);
        if(isFavorito) {
            // Menu 2
            inflater.inflate(R.menu.menu_detalhar_convenio_2, menu);
        } else {
            // Menu 1
            inflater.inflate(R.menu.menu_detalhar_convenio, menu);
        }
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
                boolean isFavorito = database.isFavorito(idConvenio);
                if(isFavorito) {
                    // Remove
                    database.removeFavoritos(idConvenio);
                    Toast.makeText(getApplicationContext(), "Convênio removido da lista de Favoritos!", Toast.LENGTH_SHORT).show();
                } else {
                    // Adiciona
                    database.addFavoritos(idConvenio);
                    Toast.makeText(getApplicationContext(), "Convênio adicionado aos Favoritos!", Toast.LENGTH_SHORT).show();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
