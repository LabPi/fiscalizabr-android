package com.paulo.fiscalizabr.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.paulo.fiscalizabr.R;
import com.paulo.fiscalizabr.core.Convenio;

import java.util.ArrayList;

/**
 * Created by Paulo on 25/03/2016.
 */
public class ConveniosAdapter extends BaseAdapter {

    private ArrayList<Convenio> mData = new ArrayList<Convenio>();
    private LayoutInflater mInflater;

    public ConveniosAdapter(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(Convenio item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Convenio getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderConvenios holderConvenios = null;

        if (convertView == null) {
            holderConvenios = new ViewHolderConvenios();

            convertView = mInflater.inflate(R.layout.item_convenio_listview, null);

            holderConvenios.nomeProponente = (TextView) convertView.findViewById(R.id.nome_proponente_textview);
            holderConvenios.municipioUf = (TextView) convertView.findViewById(R.id.municipio_uf_textview);
            holderConvenios.objeto = (TextView) convertView.findViewById(R.id.objeto_textview);
            holderConvenios.valorGlobal = (TextView) convertView.findViewById(R.id.valor_global_textview);
            holderConvenios.inicioVigencia = (TextView) convertView.findViewById(R.id.inicio_vigencia_textview);

            convertView.setTag(holderConvenios);
        } else {
            holderConvenios = (ViewHolderConvenios) convertView.getTag();
        }

        holderConvenios.nomeProponente.setText(mData.get(position).getNomeProponente());
        holderConvenios.municipioUf.setText(mData.get(position).getMunicipio() + " / " + mData.get(position).getUf());
        holderConvenios.objeto.setText(mData.get(position).getObjeto());
        holderConvenios.valorGlobal.setText(mData.get(position).getValorGlobal());
        holderConvenios.inicioVigencia.setText(mData.get(position).getInicioVigencia());

        return convertView;
    }

    public static class ViewHolderConvenios {
        private TextView nomeProponente;
        private TextView municipioUf;
        private TextView objeto;
        private TextView valorGlobal;
        private TextView inicioVigencia;
    }

}
