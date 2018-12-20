package com.example.tstaats.myvermeer.Common;

import android.util.Log;

public class RawMaterials {

    private static final String TAG = "RawMaterials";
    public static final String TABAK = "Tabak";
    public static final String TEE = "Tee";
    public static final String KAFFEE = "Kaffee";
    public static final String KAKAO = "Kakao";

    public static RawMaterials instance;

    private double tabak;
    private double tee;
    private double kaffee;
    private double kakao;

    private RawMaterials(){
    }

    public static RawMaterials getInstance(){
        if (instance == null){
            Log.d(TAG, "getInstance: instance created");
            instance = new RawMaterials();
        }
        Log.d(TAG, "getInstance: instance allready exists");
        return instance;
    }

    public double getTabak() {
        return tabak;
    }

    public void setTabak(double tabak) {
        this.tabak = tabak;
    }

    public double getTee() {
        return tee;
    }

    public void setTee(double tee) {
        this.tee = tee;
    }

    public double getKaffee() {
        return kaffee;
    }

    public void setKaffee(double kaffee) {
        this.kaffee = kaffee;
    }

    public double getKakao() {
        return kakao;
    }

    public void setKakao(double kakao) {
        this.kakao = kakao;
    }

    @Override
    public String toString() {
        return "Tabak: " + instance.getTabak() +
                "\nTee: " + instance.getTee() +
                "\nKaffee: " + instance.getKaffee() +
                "\nKakao: " + instance.getKakao();
    }
}
