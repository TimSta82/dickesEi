package com.example.tstaats.myvermeer.Common;

import android.util.Log;

import java.util.Random;

public class Aktien {

    private static final String TAG = "Aktien";
    public static final String ROYAL = "Royal";
    public static final String LLOYD = "Lloyd";
    public static final String HANSE = "Hanse";
    public static final String STAR = "Star";

    public static Aktien instance;

    private double lloyd;
    private double hanse;
    private double star;
    private double royal;

    private Aktien(){

    }

    public static Aktien getInstance(){
        if (instance == null){
            Log.d(TAG, "getInstance: instance created");
            instance = new Aktien();
        }
        Log.d(TAG, "getInstance: instance allready exists");
        return instance;
    }

    public double getLloyd() {
        return lloyd;
    }

    public void setLloyd(double lloyd) {
        this.lloyd = lloyd;
    }

    public double getHanse() {
        return hanse;
    }

    public void setHanse(double hanse) {
        this.hanse = hanse;
    }

    public double getStar() {
        return star;
    }

    public void setStar(double star) {
        this.star = star;
    }

    public double getRoyal() {
        return royal;
    }

    public void setRoyal(double royal) {
        this.royal = royal;
    }

    @Override
    public String toString() {
        return "Lloyd: " + instance.getLloyd() +
                "\nStar: " + instance.getStar() +
                "\nHanse: " + instance.getHanse() +
                "\nRoyal: " + instance.getRoyal();
    }

    public Aktien neuerKurs(){

        Random rand = new Random();
        int x = rand.nextInt(10);

        double nRoyal = instance.getRoyal();
        nRoyal = nRoyal + nRoyal * x;
        instance.setRoyal(nRoyal);

        double nLloyd = instance.getLloyd();
        nLloyd = nLloyd + nLloyd * x;
        instance.setLloyd(nLloyd);

        double nHanse = instance.getHanse();
        nHanse = nHanse + nHanse * x;
        instance.setHanse(nHanse);

        double nStar = instance.getStar();
        nStar = nStar + nStar * x;
        instance.setStar(nStar);


        return instance;
    }
}
