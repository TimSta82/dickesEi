package com.example.tstaats.myvermeer.Common;

import android.content.Context;

import com.example.tstaats.myvermeer.R;

import java.util.ArrayList;

public class City {

    private static final String TAG = "City";
    public static final int STABIL = 1;
    public static final int SHAKY = 2;
    public static final int TROUBLED = 3;
    public static final int DANGEROUS = 4;

    private Context mContext;

    private String cityName;
    private String countryName;
    private Plant plant;
    private int cityImg;
    private int countryFlag;
    private int politicalStatus;
    private double latitude;
    private double longitude;
    public enum TownInfo{VILLAGE, CITY, CAPITAL}
    public TownInfo currentTownInfo;

    private ArrayList<City> listOfCities = new ArrayList<>();

    public City(Context context){
        this.mContext = context;
    }

    public TownInfo getCurrentTownInfo() {
        return currentTownInfo;
    }

    public void setCurrentTownInfo(TownInfo currentTownInfo) {
        this.currentTownInfo = currentTownInfo;
    }


    public City(TownInfo currentTownInfo, String cityName, int cityImg, String countryName,
                int countryFlag, int politicalStatus, Plant plant, double latitude, double longitude) {
        this.plant = plant;
        this.currentTownInfo = currentTownInfo;
        this.cityName = cityName;
        this.cityImg = cityImg;
        this.countryName = countryName;
        this.countryFlag = countryFlag;
        this.politicalStatus = politicalStatus;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Plant getPlant() {
        return plant;
    }

    public void setPlant(Plant plant) {
        this.plant = plant;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getCityImg() {
        return cityImg;
    }

    public void setCityImg(int cityImg) {
        this.cityImg = cityImg;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountryFlag() {
        return countryFlag;
    }

    public void setCountryFlag(int countryFlag) {
        this.countryFlag = countryFlag;
    }

    public int getPoliticalStatus() {
        return politicalStatus;
    }

    public void setPoliticalStatus(int politicalStatus) {
        this.politicalStatus = politicalStatus;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public ArrayList<City> getListOfCities() {
        return listOfCities;
    }

    public void setListOfCities() {
        listOfCities.add(new City(TownInfo.VILLAGE,"Ankara", R.drawable._city_ankara, "Turkei",
                R.drawable._turkey, STABIL, new Plant(mContext, "Ankara"),   39.925533, 32.866287));

        listOfCities.add(new City(TownInfo.CITY,"Hamburg", R.drawable._city_hamburg,"Deutschland",
                R.drawable._deutschland, STABIL, null,  53.554467, 9.944100));

        listOfCities.add(new City(TownInfo.CAPITAL,"London", R.drawable._city_london, "England",
                R.drawable._england, STABIL, null, 51.507167, -0.127053));

        listOfCities.add(new City(TownInfo.CITY,"Lissabon", R.drawable._city_lissabon, "Portugal",
                R.drawable._portugal, STABIL, null,  38.722255, -9.138981));

        listOfCities.add(new City(TownInfo.CITY,"Rom", R.drawable._city_rom, "Italien",
                R.drawable._italien, STABIL,null,   41.903754, 12.499251));

        listOfCities.add(new City(TownInfo.CITY,"Paris", R.drawable._city_paris, "Frankreich",
                R.drawable._frankreich, STABIL, null,  48.862098, 2.334692));

        listOfCities.add(new City(TownInfo.CITY,"Amsterdam", R.drawable._city_amsterdam, "Holland",
                R.drawable._holland, STABIL, null,  52.377386, 4.897488));

        listOfCities.add(new City(TownInfo.CAPITAL,"New York", R.drawable._city_newyork, "USA",
                R.drawable._usa, STABIL, null,  40.703939, -74.011893));

        listOfCities.add(new City(TownInfo.VILLAGE,"Duala", R.drawable._city_duala, "Kamerun",
                R.drawable._kamerun, STABIL,  new Plant(mContext, "Duala"),  4.061536, 9.786072));

    }
}
