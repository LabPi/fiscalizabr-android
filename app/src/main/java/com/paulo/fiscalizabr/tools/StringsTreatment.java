package com.paulo.fiscalizabr.tools;

import java.math.BigDecimal;

/**
 * Created by Paulo on 07/04/2016.
 */
public class StringsTreatment {

    // De acordo com a data este método retorna a String com o Ano/Mes correspondente
    public static String converteAnoMes(Integer mes, Integer ano) {
        String result = "";
        if(mes == 1) result = "Jan/"+ano;
        if(mes == 2) result = "Fev/"+ano;
        if(mes == 3) result = "Mar/"+ano;
        if(mes == 4) result = "Abr/"+ano;
        if(mes == 5) result = "Mai/"+ano;
        if(mes == 6) result = "Jun/"+ano;
        if(mes == 7) result = "Jul/"+ano;
        if(mes == 8) result = "Ago/"+ano;
        if(mes == 9) result = "Set/"+ano;
        if(mes == 10) result = "Out/"+ano;
        if(mes == 11) result = "Nov/"+ano;
        if(mes == 12) result = "Dez/"+ano;

        return result;
    }

    // Método teste
    public static String converteValor(String valor) {
        BigDecimal valorGlobal = new BigDecimal(valor);
        if (valorGlobal.compareTo(new BigDecimal(1000)) < 0) {
            return "R$ " + valorGlobal + ",00";
        } else if (valorGlobal.compareTo(new BigDecimal(1000000)) < 0) {
            BigDecimal valorMilhares = valorGlobal.divide(new BigDecimal(1000));
            return "R$ " + valorMilhares + " mil";
        } else {
            BigDecimal valorMilhares = valorGlobal.divide(new BigDecimal(1000000));
            return "R$ " + valorMilhares + " mi";
        }
    }

    // Retira Á É Í Ó Ú À È Ì Ò Ù Â Ê Î Ô Û Ã Õ
    // Tokenização para consulta web atraves da URL
    public String normalizaString(String str) {
        str = str.toUpperCase();

        str = str.replace("Á", "A");
        str = str.replace("É", "E");
        str = str.replace("Í", "I");
        str = str.replace("Ú", "U");
        str = str.replace("Ó", "O");
        str = str.replace("Ù", "U");
        str = str.replace("Ò", "O");
        str = str.replace("Ì", "I");
        str = str.replace("È", "E");
        str = str.replace("À", "A");
        str = str.replace("Ç", "C");
        str = str.replace("Ã", "A");
        str = str.replace("Õ", "O");
        str = str.replace("Â", "A");
        str = str.replace("Ê", "E");
        str = str.replace("Î", "I");
        str = str.replace("Ô", "O");
        str = str.replace("Û", "U");
        str = str.replace(" ", "+");

        return str;
    }

}
