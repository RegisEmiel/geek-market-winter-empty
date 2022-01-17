package com.geekbrains.geekmarketwinter.data_html;

import com.geekbrains.geekmarketwinter.entites.Category;

import java.io.Serializable;

public class ProdImg implements Serializable{

    private Long id;

    private Category category;

    private String vendorCode;

    private String title;

    private String shortDescription;

    private String fullDescription;

    private double price;

    private String path;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public ProdImg() {
    }

    public ProdImg(String title, String vendorCode, double price) {
        this.vendorCode = vendorCode;
        this.title = title;
        this.price = price;

        this.shortDescription = title;
        this.fullDescription = title;
    }

    @Override
    public String toString() {
        return "Product title = '" + title + "'";
    }

}
