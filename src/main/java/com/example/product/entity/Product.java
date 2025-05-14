package com.example.product.entity;

import io.quarkus.hibernate.reactive.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Represents a product in the inventory system.
 * Stores basic information like name, description, price, and available quantity.
 */
@Entity
@Table(name = "quarkus_products")
public class Product extends PanacheEntity {
    /**
     * The name of the product.
     */
    public String name;

    /**
     * A brief description of the product.
     */
    public String description;

    /**
     * The price of the product.
     */
    public Double price;

    /**
     * The quantity of the product available in stock.
     */
    public Integer quantity;
}
