package com.angelwitchell.currencyconverter;

import com.google.gson.annotations.SerializedName;

public class Model {

    @SerializedName("quotes")
    private Quotes mQuotes;

    public Model(Quotes quotes) {
        mQuotes = quotes;
    }

    public Quotes getQuotes() {
        return mQuotes;
    }

    public void setQuotes(Quotes quotes) {
        mQuotes = quotes;
    }
}

