package com.angelwitchell.currencyconverter;

public class Quotes {
    private double USDGBP;
    private double USDEUR;
    private double USDHKD;
    private double USDCHF;
    private double USDJPY;

    public Quotes(double USDGBP, double USDEUR, double USDHKD, double USDCHF, double USDJPY) {
        this.USDGBP = USDGBP;
        this.USDEUR = USDEUR;
        this.USDHKD = USDHKD;
        this.USDCHF = USDCHF;
        this.USDJPY = USDJPY;
    }

    public double getUSDGBP() {
        return USDGBP;
    }

    public double getUSDEUR() {
        return USDEUR;
    }

    public double getUSDHKD() {
        return USDHKD;
    }

    public double getUSDCHF() {
        return USDCHF;
    }

    public double getUSDJPY() {
        return USDJPY;
    }
}
