package com.paulo.fiscalizabr;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.paulo.fiscalizabr.tools.StringsTreatment;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

public class PesquisarConvenio extends AppCompatActivity {

    private SeekBar minimoSeekBar;
    private SeekBar maximoSeekBar;

    private TextView minimoTextView;
    private TextView maximoTextView;

    private Spinner situacaoConvenioSpinner;

    private TextView inicioVigencia;
    private TextView fimVigencia;

    private CheckBox valorCheckBox;
    private CheckBox vigenciaCheckBox;

    public boolean filtrarValor = true;
    public boolean filtrarVigencia = true;
    public boolean filtrarSituacao = true;

    private int dia, mes, ano; // DatePicker
    private static final int DIALOG_ID = 0;

    private DatePickerDialog.OnDateSetListener datePickerListener;
    private Boolean isInicio;

    private Calendar myCalendar = Calendar.getInstance();

    public StringsTreatment tratamentoString = new StringsTreatment();

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
        minimoTextView.setText("R$ 0.00");
        maximoTextView.setText("R$ 0.00");

        valorCheckBox = (CheckBox) findViewById(R.id.valor_checkbox);
        vigenciaCheckBox = (CheckBox) findViewById(R.id.vigencia_checkbox);

        situacaoConvenioSpinner = (Spinner) findViewById(R.id.situacao_convenio_spinner);

        inicioVigencia = (TextView) findViewById(R.id.inicio_vigencia_pesquisar_textview);
        fimVigencia = (TextView) findViewById(R.id.fim_vigencia_pesquisar_textview);

        minimoSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progress = progress / 100000;
                progress = progress * 100000;

                minimoTextView.setText(tratamentoString.converteValorSpinner(String.valueOf(progress)));
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
                progress = progress / 100000;
                progress = progress * 100000;

                maximoTextView.setText(tratamentoString.converteValorSpinner(String.valueOf(progress)));
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
        String municipio, uf, inicioVigencia, fimVigencia, situacaoConvenio;
        BigDecimal valorMinimo, valorMaximo;

        // Carrega Municipio/Estado do arquivo de preferências
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        municipio = sharedPrefs.getString(getString(R.string.preference_cidade), getString(R.string.default_cidade));
        uf = sharedPrefs.getString(getString(R.string.preference_uf), getString(R.string.default_uf));

        valorMaximo = valorConvertido(maximoTextView.getText().toString());
        valorMinimo = valorConvertido(minimoTextView.getText().toString());
        inicioVigencia = this.inicioVigencia.getText().toString();
        fimVigencia = this.fimVigencia.getText().toString();

        boolean verificaData = true;
        if(!(inicioVigencia.contains("Clique") && fimVigencia.contains("Clique"))) {
            verificaData = verificaDatas(new Date(inicioVigencia), new Date(fimVigencia));
        }

        situacaoConvenio = situacaoConvenioSpinner.getSelectedItem().toString();

        if(situacaoConvenio.equals("TODOS")) filtrarSituacao = false;
        else {
            if (situacaoConvenio.equals("AGUARDANDO PRESTAÇÃO DE CONTAS")) {
                situacaoConvenio = "AGUARDANDO_PRESTACAO_CONTAS";
            } else if (situacaoConvenio.equals("EM EXECUÇÃO")) {
                situacaoConvenio = "EM_EXECUCAO";
            } else if (situacaoConvenio.equals("ASSINADO")) {
                situacaoConvenio = "ASSINADO";
            } else if (situacaoConvenio.equals("PRESTAÇÃO DE CONTAS EM ANÁLISE")) {
                situacaoConvenio = "PRESTACAO_CONTAS_EM_ANALISE";
            } else if (situacaoConvenio.equals("PRESTAÇÃO DE CONTAS REJEITADA")) {
                situacaoConvenio = "PRESTACAO_CONTAS_REJEITADA";
            } else if (situacaoConvenio.equals("PRESTAÇÃO DE CONTAS EM COMPLEMENTAÇÃO")) {
                situacaoConvenio = "PRESTACAO_CONTAS_EM_COMPLEMENTACAO";
            } else if (situacaoConvenio.equals("PRESTAÇÃO DE CONTAS APROVADA")) {
                situacaoConvenio = "PRESTACAO_CONTAS_APROVADA";
            } else if (situacaoConvenio.equals("PLANO DE TRABALHO COMPLEMENTADO EM ANÁLISE")) {
                situacaoConvenio = "PLANO_TRABALHO_COMPLEMENTADO_EM_ANALISE";
            } else if (situacaoConvenio.equals("PLANO DE TRABALHO EM COMPLEMENTAÇÃO")) {
                situacaoConvenio = "PLANO_TRABALHO_EM_COMPLEMENTACAO";
            } else if (situacaoConvenio.equals("PROPOSTA EM ANÁLISE")) {
                situacaoConvenio = "PROPOSTA_EM_ANALISE";
            } else if (situacaoConvenio.equals("PLANO DE TRABALHO EM ANÁLISE")) {
                situacaoConvenio = "PLANO_TRABALHO_EM_ANALISE";
            }
        }

        if((((valorMinimo.compareTo(new BigDecimal(0)) < 0) ||
                (valorMaximo.compareTo(new BigDecimal(0)) < 0)) && filtrarValor == true) ||
                ((inicioVigencia.equals("Clique aqui para selecionar uma data") ||
                fimVigencia.equals("Clique aqui para selecionar uma data")) && filtrarVigencia == true)) {
            Toast.makeText(this, "Selecione valores para todos os campos habilitados antes de continuar", Toast.LENGTH_SHORT).show();
        } else if(valorMaximo.compareTo(valorMinimo) < 0 && filtrarValor == true || verificaData == false) {
            Toast.makeText(this, "Por favor, verifique os valores informados!", Toast.LENGTH_SHORT).show();
        } else {
            Bundle bundle = new Bundle();
            bundle.putString("valorMinimo", String.valueOf(valorMinimo));
            bundle.putString("valorMaximo", String.valueOf(valorMaximo));
            bundle.putString("inicioVigencia", inicioVigencia);
            bundle.putString("fimVigencia", fimVigencia);
            bundle.putString("situacaoConvenio", situacaoConvenio);
            bundle.putBoolean("filtrarValor", filtrarValor);
            bundle.putBoolean("filtrarVigencia", filtrarVigencia);
            bundle.putBoolean("filtrarSituacao", filtrarSituacao);

            Intent i = new Intent(getApplicationContext(), ConveniosPesquisaDetalhada.class);
            i.putExtras(bundle);
            startActivity(i);
        }

    }

    public void desabilitaValor(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        filtrarValor = !checked;
    }

    public void desabilitaVigencia(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        filtrarVigencia = !checked;
    }

    public BigDecimal valorConvertido(String valorMinimo) {
        if(valorMinimo.contains("R$ 0.00")) return new BigDecimal(0);
        else if(valorMinimo.contains("mil")) {
            int valor = Integer.valueOf(valorMinimo.substring(3, 6));
            return new BigDecimal(valor * 1000);
        } else {
            String valor = valorMinimo.substring(3, 6);
            valor = valor.replace(",", ".");
            Double valorDouble = Double.parseDouble(valor);
            BigDecimal valorFinal = new BigDecimal(valorDouble * 1000000);
            return valorFinal;
        }
    }

    // verifica se o inicio da vigencia é menor do que o final da vigencia
    public boolean verificaDatas(Date data1, Date data2) {
        if(data2.compareTo(data1) < 0) return false;
        else return true;
    }

}
