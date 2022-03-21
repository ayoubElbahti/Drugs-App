package com.drugs_app_v1.app;

public class CustomItem {

    private String spinnerItemName;
    private int spinnerItemImage;

    public CustomItem(String spinnerItemName, int spinnerItemImage) {
        this.spinnerItemName = spinnerItemName;
        this.spinnerItemImage = spinnerItemImage;
    }

    public String getSpinnerItemName() {
        return spinnerItemName;
    }

    public int getSpinnerItemImage() {
        return spinnerItemImage;
    }
}
