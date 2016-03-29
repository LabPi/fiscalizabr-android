package com.paulo.fiscalizabr.core;

/**
 * Created by Paulo on 29/03/2016.
 */
public class ItemDialog {

    private Integer icone;
    private String nomeItem;

    public ItemDialog() { }

    public ItemDialog(Integer icone, String nomeItem) {
        this.icone = icone;
        this.nomeItem = nomeItem;
    }

    public Integer getIcone() {
        return icone;
    }

    public void setIcone(Integer icone) {
        this.icone = icone;
    }

    public String getNomeItem() {
        return nomeItem;
    }

    public void setNomeItem(String nomeItem) {
        this.nomeItem = nomeItem;
    }

}
