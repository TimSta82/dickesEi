package com.example.tstaats.myvermeer.Common;

import android.util.Log;

public class CustomDate {

    private static final String TAG = "CustomDate";

    public static CustomDate instance;
    private int day;
    private int month;
    private int year;
    private boolean isMonthSwitch = false;
    private boolean isYearSwitch = false;

    private CustomDate(){

    }

    public static CustomDate getInstance(){
        if (instance == null){
            Log.d(TAG, "getInstance: instance created");
            instance = new CustomDate();
        }
        Log.d(TAG, "getInstance: instance allready exists");
        return instance;
    }

    public boolean isMonthSwitch() {
        return isMonthSwitch;
    }

    public void setMonthSwitch(boolean monthSwitch) {
        isMonthSwitch = monthSwitch;
    }

    public boolean isYearSwitch() {
        return isYearSwitch;
    }

    public void setYearSwitch(boolean yearSwitch) {
        isYearSwitch = yearSwitch;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        if (month > 12){
            this.month = 1;
            setYear(getYear() + 1);
        } else {
            this.month = month;
        }
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {

        if (getYear()%4 == 0){ // schaltjahr
            if (getMonth() == 2){
                if (day > 29){
                    Log.d(TAG, "setDay(): month: " + getMonth() + " day > 29 daher: -> day: " + day + " this.day: " + this.day);
                    this.day = 1;
                    setMonth(getMonth() + 1);
                    setMonthSwitch(true);
                } else {
                    Log.d(TAG, "setDay(): month: " + getMonth() + " day < 29 daher: -> day: " + day + " this.day: " + this.day);
                    this.day = day;
                    setMonthSwitch(false);
                }
            } else if (getMonth() == 1 || getMonth() == 12 || getMonth() == 3 || getMonth() == 5 || getMonth() == 7 || getMonth() == 8 || getMonth() == 10){
                if (day > 31){
                    this.day = 1;
                    setMonth(getMonth() + 1);
                    setMonthSwitch(true);
                    if (month == 12){
                        setYearSwitch(true);
                    }
                    setYearSwitch(false);
                    Log.d(TAG, "setDay: month: " + getMonth() + " day: " + getDay());
                } else {
                    this.day = day;
                    setMonthSwitch(false);
                    setYearSwitch(false);

                }
            } else if (getMonth() == 11 || getMonth() == 4 || getMonth() == 6 || getMonth() == 9){
                if (day > 30){
                    this.day = 1;
                    setMonth(getMonth() + 1);
                    setMonthSwitch(true);
                    Log.d(TAG, "setDay: month: " + getMonth() + " day: " + getDay());
                } else {
                    this.day = day;
                    setMonthSwitch(false);
                }
            }
        } else { // kein schaltjahr
            if (getMonth() == 2){
                if (day > 28){
                    Log.d(TAG, "setDay(): month: " + getMonth() + " day > 28 daher: -> day: " + day + " this.day: " + this.day);
                    this.day = 1;
                    setMonth(getMonth() + 1);
                    setMonthSwitch(true);
                } else {
                    Log.d(TAG, "setDay(): month: " + getMonth() + " day < 28 daher: -> day: " + day + " this.day: " + this.day);
                    this.day = day;
                    setMonthSwitch(false);
                }
            } else if (getMonth() == 1 || getMonth() == 12 || getMonth() == 3 || getMonth() == 5 || getMonth() == 7 || getMonth() == 8 || getMonth() == 10){
                if (day > 31){
                    this.day = 1;
                    setMonth(getMonth() + 1);
                    setMonthSwitch(true);
                    if (month == 12){
                        setYearSwitch(true);
                    }
                    setYearSwitch(false);
                    Log.d(TAG, "setDay: month: " + getMonth() + " day: " + getDay());
                } else {
                    this.day = day;
                    setMonthSwitch(false);
                    setYearSwitch(false);
                }
            } else if (getMonth() == 11 || getMonth() == 4 || getMonth() == 6 || getMonth() == 9){
                if (day > 30){
                    this.day = 1;
                    setMonth(getMonth() + 1);
                    setMonthSwitch(true);
                    Log.d(TAG, "setDay: month: " + getMonth() + " day: " + getDay());
                } else {
                    this.day = day;
                    setMonthSwitch(false);
                }
            }
        }

    }


    @Override
    public String toString() {
        int dayX = this.day;
        int monthX = this.month;
        String dayStr = "";
        String monthStr = "";

        if (dayX < 10){
            dayStr = "0" + dayX;
        } else {
            dayStr = "" + dayX;
        }
        if (monthX < 10){
            monthStr = "0" + monthX;
        } else {
            monthStr = "" + monthX;
        }
        return dayStr + "." + monthStr + "." + this.year;
    }
}
