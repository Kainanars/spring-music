package com.sd3.market.dtos;

import java.util.UUID;

public class ProductDto {
    public String Id;
    public  String Name;
    public  String Description;
    public  double Price;
    public  int QuantityInStock;

    public ProductDto() {
    }

    public ProductDto(UUID id, String name, String description, double price, int quantityInStock) {
        Id = id.toString();
        Name = name;
        Description = description;
        Price = price;
        QuantityInStock = quantityInStock;
    }

    public ProductDto(String name, String description, double price, int quantityInStock) {
        this.Name = name;
        this.Description = description;
        this.Price = price;
        this.QuantityInStock = quantityInStock;
    }
}
