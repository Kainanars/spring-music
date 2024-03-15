package com.sd3.market.entities;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="TB_PRODUCTS")
public class Product extends AbstractEntity {
    @Column(name = "NM_PRODUCT")
    private String Name;
    @Column(name = "VL_PRICE")
    private Double Price;

    @Column(name = "DS_Description")
    private String Description;

    @Column(name = "NR_Stock")
    private int quantityInStock;

    public Product(String name, Double price, String description, int quantityInStock) {
        Name = name;
        Price = price;
        Description = description;
        this.quantityInStock = quantityInStock;
    }

    public Product() {

    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double price) {
        Price = price;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}
