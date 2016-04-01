package com.paulo.fiscalizabr.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.paulo.fiscalizabr.R;
import com.paulo.fiscalizabr.core.ItemDialog;

import java.util.ArrayList;

/**
 * Created by Paulo on 29/03/2016.
 */
public class DialogListViewAdapter extends BaseAdapter {

    private ArrayList<ItemDialog> mData = new ArrayList<ItemDialog>();
    private LayoutInflater mInflater;

    public DialogListViewAdapter(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem(ItemDialog item) {
        mData.add(item);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public ItemDialog getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolderDialog holderDialog = null;

        if (convertView == null) {
            holderDialog = new ViewHolderDialog();

            convertView = mInflater.inflate(R.layout.item_ordenar_dialog_listview, null);

            holderDialog.icone = (ImageView) convertView.findViewById(R.id.icone_dialog_imageview);
            holderDialog.item = (TextView) convertView.findViewById(R.id.item_dialogo_textview);

            convertView.setTag(holderDialog);
        } else {
            holderDialog = (ViewHolderDialog) convertView.getTag();
        }

        holderDialog.icone.setImageResource(mData.get(position).getIcone());
        holderDialog.item.setText(mData.get(position).getNomeItem());

        return convertView;
    }

    public static class ViewHolderDialog {
        private ImageView icone;
        private TextView item;
    }

}
