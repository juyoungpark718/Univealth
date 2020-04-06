package com.example.univealth.Fragment;

import com.github.mikephil.charting.formatter.ValueFormatter;

class MyValueFormatter extends ValueFormatter {
    private String[] mValues = new String[] {};
    private int mValueCount = 0;
    @Override
    public String getFormattedValue(float value) {
        int index = Math.round(value);
        if (index < 0 || index >= mValueCount || index != (int)value)
            return "";
        return mValues[index];
    }

    public MyValueFormatter(String[] values) {
        if (values == null)
            values = new String[] {};

        this.mValues = values;
        this.mValueCount = values.length;
    }
}
