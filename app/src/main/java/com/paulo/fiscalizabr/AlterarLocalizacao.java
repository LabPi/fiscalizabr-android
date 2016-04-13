package com.paulo.fiscalizabr;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

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

        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        String cidadePreference = sharedPref.getString(getString(R.string.preference_cidade), "Brasilia");
        String ufPreference = sharedPref.getString(getString(R.string.preference_uf), "DF");

        municipio.setText(cidadePreference);

        // Populando Spinner UF
        ArrayAdapter<CharSequence> adapterUf = ArrayAdapter.createFromResource(AlterarLocalizacao.this, R.array.uf_array, android.R.layout.simple_spinner_dropdown_item);
        adapterUf.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        ufSpinner.setAdapter(adapterUf);

        setarUfSpinner(ufPreference);
    }

    public void setarUfSpinner(String uf) {
        if(uf.equals("AC")) {
            ufSpinner.setSelection(0);
        } else if(uf.equals("AL")) {
            ufSpinner.setSelection(1);
        } else if(uf.equals("AP")) {
            ufSpinner.setSelection(2);
        } else if(uf.equals("AM")) {
            ufSpinner.setSelection(3);
        } else if(uf.equals("BA")) {
            ufSpinner.setSelection(4);
        } else if(uf.equals("CE")) {
            ufSpinner.setSelection(5);
        } else if(uf.equals("DF")) {
            ufSpinner.setSelection(6);
        } else if(uf.equals("ES")) {
            ufSpinner.setSelection(7);
        } else if(uf.equals("GO")) {
            ufSpinner.setSelection(8);
        } else if(uf.equals("MA")) {
            ufSpinner.setSelection(9);
        } else if(uf.equals("MT")) {
            ufSpinner.setSelection(10);
        } else if(uf.equals("MS")) {
            ufSpinner.setSelection(11);
        } else if(uf.equals("MG")) {
            ufSpinner.setSelection(12);
        } else if(uf.equals("PA")) {
            ufSpinner.setSelection(13);
        } else if(uf.equals("PB")) {
            ufSpinner.setSelection(14);
        } else if(uf.equals("PR")) {
            ufSpinner.setSelection(15);
        } else if(uf.equals("PE")) {
            ufSpinner.setSelection(16);
        } else if(uf.equals("PI")) {
            ufSpinner.setSelection(17);
        } else if(uf.equals("RJ")) {
            ufSpinner.setSelection(18);
        } else if(uf.equals("RN")) {
            ufSpinner.setSelection(19);
        } else if(uf.equals("RS")) {
            ufSpinner.setSelection(20);
        } else if(uf.equals("RO")) {
            ufSpinner.setSelection(21);
        } else if(uf.equals("RR")) {
            ufSpinner.setSelection(22);
        } else if(uf.equals("SC")) {
            ufSpinner.setSelection(23);
        } else if(uf.equals("SP")) {
            ufSpinner.setSelection(24);
        } else if(uf.equals("SE")) {
            ufSpinner.setSelection(25);
        } else if(uf.equals("TO")) {
            ufSpinner.setSelection(26);
        }
    }

    public void alterarLocalizacao(View view) {
        // Preferências compartilhadas
        SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(getString(R.string.preference_cidade), municipio.getText().toString());
        editor.putString(getString(R.string.preference_uf), ufSpinner.getSelectedItem().toString());
        editor.commit();
    }

}
