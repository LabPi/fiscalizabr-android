package com.paulo.fiscalizabr.tools;

import java.math.BigDecimal;

/**
 * Created by Paulo on 07/04/2016.
 */
public class StringsTreatment {

    // De acordo com a data este método retorna a String com o Ano/Mes correspondente
    public static String converteAnoMes(Integer mes, Integer ano) {
        String result = "";
        if(mes == 1) result = "Janeiro de "+ano;
        if(mes == 2) result = "Fevereiro de "+ano;
        if(mes == 3) result = "Março de "+ano;
        if(mes == 4) result = "Abril de "+ano;
        if(mes == 5) result = "Maio de "+ano;
        if(mes == 6) result = "Junho de "+ano;
        if(mes == 7) result = "Julho de "+ano;
        if(mes == 8) result = "Agosto de "+ano;
        if(mes == 9) result = "Setembro de "+ano;
        if(mes == 10) result = "Outubro de "+ano;
        if(mes == 11) result = "Novembro de "+ano;
        if(mes == 12) result = "Dezembro de "+ano;

        return result;
    }

    // Converte o valor para apresentar no ListView
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

    // Converte o valor para apresentar no TextView do Spinner
    public static String converteValorSpinner(String valor) {
        BigDecimal valorGlobal = new BigDecimal(valor);
        if(valorGlobal.intValue() < 1) {
            return "R$ 0.00";
        } else if (valorGlobal.compareTo(new BigDecimal(50000)) < 0) {
            return "R$ " + String.format("%.0f",valorGlobal) + " mil";
        } else if (valorGlobal.compareTo(new BigDecimal(1000000)) < 0) {
            valorGlobal = valorGlobal.divide(new BigDecimal(1000));
            return "R$ " + String.format("%.0f",valorGlobal) + " mil";
        } else {
            BigDecimal valorMilhares = valorGlobal.divide(new BigDecimal(1000000));
            valorGlobal = valorGlobal.divide(new BigDecimal(1000000));
            return "R$ " + String.format("%.1f",valorGlobal) + " mi";
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

    public String normalizaStringBanco(String str) {
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

        return str;
    }

    public BigDecimal valorConvertido(String valorMinimo) {
        if(valorMinimo.contains("R$ 0.00")) return new BigDecimal(0);
        else if(valorMinimo.contains("mil")) {
            int valor = Integer.valueOf(valorMinimo.substring(3, 6));
            return new BigDecimal(valor * 1000);
        } else {
            String valor = valorMinimo.substring(3, 6);
            valor = valor.replace(",", ".");
            Double valorDouble = Double.parseDouble(valor);
            BigDecimal valorFinal = new BigDecimal(valorDouble * 1000000);
            return valorFinal;
        }
    }

}
