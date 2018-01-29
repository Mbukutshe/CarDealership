package com.wiseman.cardealership.Objects;

/**
 * Created by Wiseman on 2018-01-25.
 */

public class VehicleObject {
    private int image;
    private String price;
    private String featurers;
    private  String name;

    public VehicleObject(int image, String price, String featurers, String name) {
        this.image = image;
        this.price = price;
        this.featurers = featurers;
        this.name = name;
    }
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getFeaturers() {
        return featurers;
    }

    public void setFeaturers(String featurers) {
        this.featurers = featurers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
