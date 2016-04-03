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
import java.util.TreeSet;

/**
 * Created by Paulo on 25/03/2016.
 */
public class ConveniosAdapter extends BaseAdapter {

    public static final int TYPE_ITEM = 0;
    public static final int TYPE_SEPARATOR = 1;
    public static final int TYPE_MAX_COUNT = TYPE_SEPARATOR +  1;

    private ArrayList<Convenio> mData = new ArrayList<Convenio>();
    private LayoutInflater mInflater;
    private TreeSet mSeparatorsSet = new TreeSet();

    public ConveniosAdapter(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(Convenio item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void addEmptyList(Convenio item) {
        mData.add(item);
        // save separator position
        mSeparatorsSet.add(mData.size() - 1);
        notifyDataSetChanged();
    }

    public void clear() {
        mData.clear();
        mSeparatorsSet.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return mSeparatorsSet.contains(position) ? TYPE_SEPARATOR : TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_MAX_COUNT;
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
        ViewHolderVazio holderVazio = null;

        View convertViewConvenios = null;
        View convertViewVazio = null;

        int type = getItemViewType(position);

        if(convertViewConvenios == null || convertViewVazio == null) {
            switch(type) {
                case TYPE_ITEM:
                    holderConvenios = new ViewHolderConvenios();

                    convertViewConvenios = mInflater.inflate(R.layout.item_convenio_listview, null);

                    holderConvenios.objetoConvenio = (TextView) convertViewConvenios.findViewById(R.id.objeto_convenio_textview);
                    holderConvenios.vigenciaConvenio = (TextView) convertViewConvenios.findViewById(R.id.vigencia_convenio_textview);
                    holderConvenios.valorConvenio = (TextView) convertViewConvenios.findViewById(R.id.valor_convenio_textview);
                    holderConvenios.isFavorito = (ImageView) convertViewConvenios.findViewById(R.id.convenio_favorito_listview_imageview);
                    holderConvenios.situacaoConvenio = (ImageView) convertViewConvenios.findViewById(R.id.situacao_convenio_imageview);

                    convertViewConvenios.setTag(holderConvenios);
                    break;
                case TYPE_SEPARATOR:
                    holderVazio = new ViewHolderVazio();

                    convertViewVazio = mInflater.inflate(R.layout.item_procurando_convenios, null);

                    holderVazio.iconeVazio = (ImageView) convertViewVazio.findViewById(R.id.localizacao_carregando_imageview);
                    holderVazio.textoVazio = (TextView) convertViewVazio.findViewById(R.id.carrregando_convenios_textview);

                    convertViewVazio.setTag(holderVazio);
                    break;
            }
        } else {
            switch(type) {
                case TYPE_ITEM:
                    holderConvenios = (ViewHolderConvenios) convertViewConvenios.getTag();
                case TYPE_SEPARATOR:
                    holderVazio = (ViewHolderVazio) convertViewVazio.getTag();
            }
        }

        if(type == TYPE_ITEM) {
            holderConvenios.objetoConvenio.setText(mData.get(position).getObjetoConvenio());
            holderConvenios.vigenciaConvenio.setText(mData.get(position).getVigencia());
            holderConvenios.valorConvenio.setText(mData.get(position).getValorConvenio());
            if(mData.get(position).isFavorito() == true) {
                holderConvenios.isFavorito.setImageResource(R.drawable.ic_star_white_24dp);
            } else {
                holderConvenios.isFavorito.setImageResource(R.drawable.ic_star_border_white_24dp);
            }

            if(mData.get(position).getSituacaoConvenio() == 1) {
                // vermleho
                holderConvenios.situacaoConvenio.setImageResource(R.drawable.problema);
            } else if(mData.get(position).getSituacaoConvenio() == 2) {
                // amarelo
                holderConvenios.situacaoConvenio.setImageResource(R.drawable.em_andamento);
            } else if(mData.get(position).getSituacaoConvenio() == 3) {
                // verde
                holderConvenios.situacaoConvenio.setImageResource(R.drawable.ok);
            }

            convertView = convertViewConvenios;
        } else if(type == TYPE_SEPARATOR) {
            // Separator utilizado para quando a lista de noticias está vazia
            holderVazio.iconeVazio.setImageResource(R.drawable.ic_room_black_48dp);
            holderVazio.textoVazio.setText(convertViewVazio.getResources().getString(R.string.carregando_convenios));

            convertView = convertViewVazio;
        }

        return convertView;
    }

    public static class ViewHolderConvenios {
        private TextView objetoConvenio;
        private TextView vigenciaConvenio;
        private TextView valorConvenio;
        private ImageView isFavorito;
        private ImageView situacaoConvenio;
    }

    public static class ViewHolderVazio {
        private ImageView iconeVazio;
        private TextView textoVazio;
    }

}
