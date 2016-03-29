package com.paulo.fiscalizabr;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class PesquisarConvenio extends AppCompatActivity {

    private SeekBar minimoSeekBar;
    private SeekBar maximoSeekBar;

    private TextView minimoTextView;
    private TextView maximoTextView;

    private Spinner situacaoConvenioSpinner;

    private TextView inicioVigencia;
    private TextView fimVigencia;

    private int dia, mes, ano; // DatePicker
    private static final int DIALOG_ID = 0;

    private DatePickerDialog.OnDateSetListener datePickerListener;
    private Boolean isInicio;

    private Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pesquisar_convenio);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_pesquisar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        minimoSeekBar = (SeekBar) findViewById(R.id.valor_minimo_seekbar);
        maximoSeekBar = (SeekBar) findViewById(R.id.valor_maximo_seekbar);

        minimoTextView = (TextView) findViewById(R.id.valor_minimo_textview);
        maximoTextView = (TextView) findViewById(R.id.valor_maximo_textview);

        situacaoConvenioSpinner = (Spinner) findViewById(R.id.situacao_convenio_spinner);

        inicioVigencia = (TextView) findViewById(R.id.inicio_vigencia_pesquisar_textview);
        fimVigencia = (TextView) findViewById(R.id.fim_vigencia_pesquisar_textview);

        minimoSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String minimo = "R$ " + progress + ",00";
                minimoTextView.setText(minimo);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        maximoSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                String maximo = "R$ " + progress + ",00";
                maximoTextView.setText(maximo);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Populando Spinner Situação Convênio
        ArrayAdapter<CharSequence> adapterSituacao = ArrayAdapter.createFromResource(PesquisarConvenio.this, R.array.situacao_array, android.R.layout.simple_spinner_dropdown_item);
        adapterSituacao.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        situacaoConvenioSpinner.setAdapter(adapterSituacao);

        final Calendar cal = Calendar.getInstance();
        ano = cal.get(Calendar.YEAR);
        mes = cal.get(Calendar.MONTH);
        dia = cal.get(Calendar.DAY_OF_MONTH);

        datePickerListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                ano = year;
                mes = monthOfYear+1;
                dia = dayOfMonth;
                if(isInicio) {
                    inicioVigencia.setText(dia + "/" + mes + "/" + ano);
                } else{
                    fimVigencia.setText(dia + "/" + mes + "/" + ano);
                }
            }
        };
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if(id == DIALOG_ID) {
            return new DatePickerDialog(this, datePickerListener, ano, mes, dia);
        }
        return null;
    }

    // Métodos dos Listeners das Datas
    public void selecionarInicioVigenciaDatePicker(View view) {
        showDialog(DIALOG_ID);
        isInicio = true;
    }

    public void selecionarFinalVigenciaDatePicker(View view) {
        showDialog(DIALOG_ID);
        isInicio = false;
    }

    public void pesquisarConvenio(View view) {
        Toast.makeText(this.getApplicationContext(), "Realiza Pesquisa do Convênio", Toast.LENGTH_SHORT).show();
    }

}
