package com.allbank.balancecheck;

public class CountryItem2 implements Comparable<CountryItem2>{
    private int countryID;
    String countryName2;
    int countryFlag2;
    int countryFlag3;

    public CountryItem2(int countryID, String countryName2, int countryFlag2, int countryFlag3) {
        this.countryID = countryID;
        this.countryName2 = countryName2;
        this.countryFlag2 = countryFlag2;
        this.countryFlag3 = countryFlag3;

    }

    public int getCountryID() {
        return countryID;
    }

    public String getCountryName() {
        return countryName2;
    }

    public String getCountryName2() {
        return countryName2;
    }

    public void setCountryName2(String countryName2) {
        this.countryName2 = countryName2;
    }

    public int getCountryFlag2() {
        return countryFlag2;
    }

    public void setCountryFlag2(int countryFlag2) {
        this.countryFlag2 = countryFlag2;
    }

    public int getCountryFlag3() {
        return countryFlag3;
    }

    public void setCountryFlag3(int countryFlag3) {
        this.countryFlag3 = countryFlag3;
    }

    @Override
    public int compareTo(CountryItem2 another) {
        return this.getCountryID() - another.getCountryID();//ascending order
//            return another.getCountryID()-this.getCountryID();//descending  order
    }
}
