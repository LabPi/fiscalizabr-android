package com.paulo.fiscalizabr;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.paulo.fiscalizabr.connection.DownloadConvenios;
import com.paulo.fiscalizabr.tools.StringsTreatment;

public class SplashScreenFiscaliza extends Activity implements Runnable {

    public StringsTreatment tratamentoString = new StringsTreatment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen_fiscaliza);

        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
        String cidadePreference = sharedPrefs.getString(getString(R.string.preference_cidade), getString(R.string.default_cidade));
        String ufPreference = sharedPrefs.getString(getString(R.string.preference_uf), getString(R.string.default_uf));

        DownloadConvenios downloadConvenios = new DownloadConvenios(this, tratamentoString.normalizaString(cidadePreference), ufPreference, false);
        downloadConvenios.execute();

        Handler handler = new Handler();
        handler.postDelayed(this, 5000);
    }

    public void run(){
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}
