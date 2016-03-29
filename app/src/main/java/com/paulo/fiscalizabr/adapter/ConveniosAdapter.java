package com.paulo.fiscalizabr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
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

            holderConvenios.objetoConvenio = (TextView) convertView.findViewById(R.id.objeto_convenio_textview);
            holderConvenios.vigenciaConvenio = (TextView) convertView.findViewById(R.id.vigencia_convenio_textview);
            holderConvenios.valorConvenio = (TextView) convertView.findViewById(R.id.valor_convenio_textview);
            holderConvenios.isFavorito = (ImageView) convertView.findViewById(R.id.convenio_favorito_listview_imageview);

            convertView.setTag(holderConvenios);
        } else {
            holderConvenios = (ViewHolderConvenios) convertView.getTag();
        }

        holderConvenios.objetoConvenio.setText(mData.get(position).getObjetoConvenio());
        holderConvenios.vigenciaConvenio.setText(mData.get(position).getVigencia());
        holderConvenios.valorConvenio.setText(mData.get(position).getValorConvenio());
        if(mData.get(position).isFavorito() == true) {
            holderConvenios.isFavorito.setImageResource(R.drawable.ic_star_white_24dp);
        } else {
            holderConvenios.isFavorito.setImageResource(R.drawable.ic_star_border_white_24dp);
        }

        return convertView;
    }

    public static class ViewHolderConvenios {
        private TextView objetoConvenio;
        private TextView vigenciaConvenio;
        private TextView valorConvenio;
        private ImageView isFavorito;
    }

}
