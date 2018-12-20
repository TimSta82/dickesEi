package com.example.tstaats.myvermeer.Common;

import android.widget.ImageButton;

public class FieldItem {

    private static final String TAG = "FieldItem";

    private ImageButton fieldButton;
    private int fieldItemResource;

    public FieldItem(ImageButton fieldButton, int fieldItemResource) {
        this.fieldButton = fieldButton;
        this.fieldItemResource = fieldItemResource;
        this.fieldButton.setImageResource(this.fieldItemResource);
    }

    public ImageButton getFieldButton() {
        return fieldButton;
    }

    public void setFieldButton(ImageButton fieldButton) {
        this.fieldButton = fieldButton;
    }

    public int getFieldItemResource() {
        return fieldItemResource;
    }

    public void setFieldItemResource(int fieldItemResource) {
        this.fieldItemResource = fieldItemResource;
    }
}
