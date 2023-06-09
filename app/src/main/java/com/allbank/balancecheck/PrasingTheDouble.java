package com.allbank.balancecheck;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class PrasingTheDouble {
    double aDouble;

    public PrasingTheDouble(double mDouble) {
        this.aDouble = mDouble;
    }

    public String getaDouble() {
        return ((DecimalFormat) NumberFormat.getNumberInstance(Locale.US)).format(this.aDouble);
    }
}
