package com.paulo.fiscalizabr.core;

import android.util.Log;

import com.paulo.fiscalizabr.tools.StringsTreatment;

import java.math.BigDecimal;
import java.util.Comparator;

/**
 * Created by Paulo on 13/04/2016.
 */
public class DataComparator implements Comparator<Convenio> {

    @Override
    public int compare(Convenio obj1, Convenio obj2) {
        // Valor
        StringsTreatment tratamento = new StringsTreatment();

        //20/10/2008 a 20/01/2009
        String data1 = obj1.getVigencia().substring(0, 10);
        String data2 = obj2.getVigencia().substring(0, 10);

        if(data1.compareTo(data2) < 0) return -1;
        else if(!(data1.compareTo(data2) < 0)) return 1;
        return 0;
    }

}
