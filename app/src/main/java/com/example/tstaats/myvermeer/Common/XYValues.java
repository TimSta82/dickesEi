package com.example.tstaats.myvermeer.Common;

public class XYValues {

    private static final String TAG = "XYValues";

    private double x;
    private double y;

    public XYValues(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }
}
