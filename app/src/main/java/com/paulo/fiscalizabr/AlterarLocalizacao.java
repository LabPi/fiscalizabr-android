package com.paulo.fiscalizabr;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class AlterarLocalizacao extends AppCompatActivity {

    private EditText municipio;
    private Spinner ufSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterar_localizacao);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        municipio = (EditText) findViewById(R.id.municipio_edittext);
        ufSpinner = (Spinner) findViewById(R.id.estado_spinner);

        // Populando Spinner UF
        ArrayAdapter<CharSequence> adapterUf = ArrayAdapter.createFromResource(AlterarLocalizacao.this, R.array.uf_array, android.R.layout.simple_spinner_dropdown_item);
        adapterUf.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ufSpinner.setAdapter(adapterUf);
    }

}
