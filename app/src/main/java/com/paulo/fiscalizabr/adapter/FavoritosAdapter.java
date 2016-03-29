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
import com.paulo.fiscalizabr.core.Favoritos;

import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by Paulo on 29/03/2016.
 */
public class FavoritosAdapter extends BaseAdapter {

    public static final int TYPE_ITEM = 0;
    public static final int TYPE_SEPARATOR = 1;
    public static final int TYPE_MAX_COUNT = TYPE_SEPARATOR +  1;

    private ArrayList<Favoritos> mData = new ArrayList<Favoritos>();
    private LayoutInflater mInflater;
    private TreeSet mSeparatorsSet = new TreeSet();

    public FavoritosAdapter(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(Favoritos item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    public void addEmptyList(Favoritos item) {
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
    public Favoritos getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderFavoritos holderFavoritos = null;
        ViewHolderVazio holderVazio = null;

        View convertViewFavoritos = null;
        View convertViewVazio = null;

        int type = getItemViewType(position);

        if(convertViewFavoritos == null || convertViewVazio == null) {
            switch(type) {
                case TYPE_ITEM:
                    holderFavoritos = new ViewHolderFavoritos();

                    convertViewFavoritos = mInflater.inflate(R.layout.item_favoritos_listview, null);

                    holderFavoritos.objetoConvenio = (TextView) convertViewFavoritos.findViewById(R.id.objeto_convenio_textview);
                    holderFavoritos.vigenciaConvenio = (TextView) convertViewFavoritos.findViewById(R.id.vigencia_convenio_textview);
                    holderFavoritos.valorConvenio = (TextView) convertViewFavoritos.findViewById(R.id.valor_convenio_textview);
                    holderFavoritos.municipioUf = (TextView) convertViewFavoritos.findViewById(R.id.municipio_uf_textview);

                    convertViewFavoritos.setTag(holderFavoritos);
                    break;
                case TYPE_SEPARATOR:
                    holderVazio = new ViewHolderVazio();

                    convertViewVazio = mInflater.inflate(R.layout.item_favoritos_clear, null);

                    holderVazio.iconeVazio = (ImageView) convertViewVazio.findViewById(R.id.favoritos_imageview);
                    holderVazio.textoVazio = (TextView) convertViewVazio.findViewById(R.id.nao_possui_favoritos_textview);

                    convertViewVazio.setTag(holderVazio);
                    break;
            }
        } else {
            switch(type) {
                case TYPE_ITEM:
                    holderFavoritos = (ViewHolderFavoritos) convertViewFavoritos.getTag();
                case TYPE_SEPARATOR:
                    holderVazio = (ViewHolderVazio) convertViewVazio.getTag();
            }
        }

        if(type == TYPE_ITEM) {
            holderFavoritos.objetoConvenio.setText(mData.get(position).getObjetoConvenio());
            holderFavoritos.vigenciaConvenio.setText(mData.get(position).getVigencia());
            holderFavoritos.valorConvenio.setText(mData.get(position).getValorConvenio());
            holderFavoritos.municipioUf.setText(mData.get(position).getMunicipioUf());

            convertView = convertViewFavoritos;
        } else if(type == TYPE_SEPARATOR) {
            // Separator utilizado para quando a lista de noticias está vazia
            holderVazio.iconeVazio.setImageResource(R.drawable.ic_star_black_48dp);
            holderVazio.textoVazio.setText(convertViewVazio.getResources().getString(R.string.nao_possui_favoritos));

            convertView = convertViewVazio;
        }

        return convertView;
    }

    public static class ViewHolderFavoritos {
        private TextView objetoConvenio;
        private TextView vigenciaConvenio;
        private TextView valorConvenio;
        private TextView municipioUf;
    }

    public static class ViewHolderVazio {
        private ImageView iconeVazio;
        private TextView textoVazio;
    }

}