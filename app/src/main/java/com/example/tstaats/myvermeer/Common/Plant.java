package com.example.tstaats.myvermeer.Common;

import android.content.Context;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Plant {

    private static final String TAG = "Plant";

    public static final int FIELD_SIZE = 10;
    public static final int FIELD_POSITION_CLICK_COUNTER = FIELD_SIZE;

    private Context mContext;

    private String villageName;

    private int tabakCounter;
    private int teeCounter;
    private int kaffeeCounter;
    private int kakaoCounter;
    private int totalCounter;

    private boolean isBought;

    private ArrayList<ImageButton> mFieldList;
    private ArrayList<Boolean> mClickedIndicatorList;

    public Plant(){

    }

    public Plant(Context context, String name) {
        this.villageName = name;
        this.isBought = false;
        this.mContext = context;
        this.mFieldList = new ArrayList<>();
        this.mClickedIndicatorList = new ArrayList<>();
    }

/*
    public Plant(ArrayList<ImageButton> mFieldList, ArrayList<Boolean> mClickedIndicatorList) {
        this.mFieldList = mFieldList;
        this.mClickedIndicatorList = mClickedIndicatorList;
    }
*/

    public ArrayList<ImageButton> generateField(){
        ImageButton field = new ImageButton(mContext);
        for (int i = 0; i < FIELD_SIZE; i++) {
            mFieldList.add(field);
        }

        return mFieldList;
    }

    public ArrayList<Boolean> generateFieldCounter(){
        Boolean isClicked = false;
        for (int i = 0; i < FIELD_SIZE; i++) {
            mClickedIndicatorList.add(isClicked);
        }

        return mClickedIndicatorList;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public boolean isBought() {
        return isBought;
    }

    public void setBought(boolean bought) {
        isBought = bought;
    }

    public int getTabakCounter() {
        return tabakCounter;
    }

    public void setTabakCounter(int tabakCounter) {
        this.tabakCounter = tabakCounter;
    }

    public int getTeeCounter() {
        return teeCounter;
    }

    public void setTeeCounter(int teeCounter) {
        this.teeCounter = teeCounter;
    }

    public int getKaffeeCounter() {
        return kaffeeCounter;
    }

    public void setKaffeeCounter(int kaffeeCounter) {
        this.kaffeeCounter = kaffeeCounter;
    }

    public int getKakaoCounter() {
        return kakaoCounter;
    }

    public void setKakaoCounter(int kakaoCounter) {
        this.kakaoCounter = kakaoCounter;
    }

    public int getTotalCount(){
        return this.kaffeeCounter + this.kakaoCounter + this.tabakCounter + this.teeCounter;
    }
}
