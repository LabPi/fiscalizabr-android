package com.paulo.fiscalizabr;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.paulo.fiscalizabr.connection.DownloadConvenios;
import com.paulo.fiscalizabr.tools.StringsTreatment;

/**
 * Created by Paulo on 03/04/2016.
 */
public class AlterarPreferencias extends PreferenceActivity implements Preference.OnPreferenceChangeListener {

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Toolbar bar;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            LinearLayout root = (LinearLayout) findViewById(android.R.id.list).getParent().getParent().getParent();
            bar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.pref_toolbar, root, false);
            bar.setTitleTextColor(getResources().getColor(R.color.background_text_preference));
            root.addView(bar, 0); // insert at top
        } else {
            ViewGroup root = (ViewGroup) findViewById(android.R.id.content);
            ListView content = (ListView) root.getChildAt(0);

            root.removeAllViews();

            bar = (Toolbar) LayoutInflater.from(this).inflate(R.layout.pref_toolbar, root, false);
            bar.setTitleTextColor(getResources().getColor(R.color.background_text_preference));

            int height;
            TypedValue tv = new TypedValue();
            if (getTheme().resolveAttribute(R.attr.actionBarSize, tv, true)) {
                height = TypedValue.complexToDimensionPixelSize(tv.data, getResources().getDisplayMetrics());
            }else{
                height = bar.getHeight();
            }

            content.setPadding(0, height, 0, 0);

            root.addView(content);
            root.addView(bar);
        }

        bar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String cidadePreference = sharedPrefs.getString(getString(R.string.preference_cidade), getString(R.string.default_cidade));
                if(cidadePreference.equals("")) cidadePreference = "Brasília";
                String ufPreference = sharedPrefs.getString(getString(R.string.preference_uf), getString(R.string.default_uf));

                DownloadConvenios downloadConvenios = new DownloadConvenios(getApplicationContext(), new StringsTreatment().normalizaString(cidadePreference), ufPreference, true);
                downloadConvenios.execute();

                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String cidadePreference = sharedPrefs.getString(getString(R.string.preference_cidade), getString(R.string.default_cidade));
        if(cidadePreference.equals("")) cidadePreference = "Brasília";
        String ufPreference = sharedPrefs.getString(getString(R.string.preference_uf), getString(R.string.default_uf));

        DownloadConvenios downloadConvenios = new DownloadConvenios(getApplicationContext(), new StringsTreatment().normalizaString(cidadePreference), ufPreference, true);
        downloadConvenios.execute();

        finish();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        PreferenceManager.setDefaultValues(this, R.xml.pref_fiscalizabr, false);

        addPreferencesFromResource(R.xml.pref_fiscalizabr);
        bindPreferenceSummaryToValue(findPreference(getString(R.string.preference_cidade)));
        bindPreferenceSummaryToValue(findPreference(getString(R.string.preference_uf)));
    }

    private void bindPreferenceSummaryToValue(Preference preference) {
        preference.setOnPreferenceChangeListener(this);

        onPreferenceChange(preference, PreferenceManager.getDefaultSharedPreferences(preference.getContext()).getString(preference.getKey(), ""));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object value) {
        String stringValue = value.toString();

        if(!stringValue.equals("")) {
            if (preference instanceof ListPreference) {
                ListPreference listPreference = (ListPreference) preference;
                int prefIndex = listPreference.findIndexOfValue(stringValue);
                if (prefIndex >= 0) {
                    preference.setSummary(listPreference.getEntries()[prefIndex]);
                }
            } else {
                preference.setSummary(stringValue);
            }
        } else {
            preference.setSummary("Brasília");
            //Toast.makeText(getApplicationContext(), "Por favor, preencha este campo com o nome do município", Toast.LENGTH_SHORT).show();
        }

        return true;
    }

}
