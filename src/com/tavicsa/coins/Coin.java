package com.tavicsa.coins;

public class Coin {
    private String country;
    private int denomination;
    private int mintingYear;
    private int currentValue;
    private String acquiredDate;

    public Coin() {
    }

    public Coin(String country, int denomination, int mintingYear, int currentValue, String acquiredDate) {
        this.country = country;
        this.denomination = denomination;
        this.mintingYear = mintingYear;
        this.currentValue = currentValue;
        this.acquiredDate = acquiredDate;
    }

    @Override
    public String toString() {
        return "Country='" + country + '\'' +
                ", Denomination=" + denomination +
                ", Year=" + mintingYear +
                ", Value=" + currentValue +
                ", Date=" + acquiredDate + "\n";
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getDenomination() {
        return denomination;
    }

    public void setDenomination(int denomination) {
        this.denomination = denomination;
    }

    public int getMintingYear() {
        return mintingYear;
    }

    public void setMintingYear(int mintingYear) {
        this.mintingYear = mintingYear;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public String getAcquiredDate() {
        return acquiredDate;
    }

    public void setAcquiredDate(String acquiredDate) {
        this.acquiredDate = acquiredDate;
    }
}
