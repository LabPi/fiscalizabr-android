package com.paulo.fiscalizabr.enums;

import java.io.Serializable;

/**
 * Created by Paulo on 08/04/2016.
 */

public enum SituacaoPublicacaoConvenio implements Serializable {

    A_PUBLICAR("A publicar"),
    NAO_PUBLICADO("Não publicado"),
    PUBLICADO("Publicado"),
    TRANSFERIDO_PARA_IMPRENSA_NACIONAL("Transferido para a imprensa nacional");

    private final String descricao;

    private SituacaoPublicacaoConvenio(String desc) {
        this.descricao = desc;
    }

    public String getDescricao() {
        return this.descricao;
    }
}