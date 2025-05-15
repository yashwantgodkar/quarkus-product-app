package com.example.product.service;

import com.example.product.entity.Product;
import com.example.product.exception.NotFoundException;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.hibernate.reactive.panache.common.WithTransaction;
import io.quarkus.hibernate.reactive.panache.common.WithSession;
import jakarta.ws.rs.core.Response;

import java.util.List;

/**
 * The type Product service.
 */
@ApplicationScoped
public class ProductService {

    /**
     * Create uni.
     *
     * @param product the product
     * @return the uni
     */
    @WithTransaction
    public Uni<Product> create(Product product) {
        return product.persist();
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    @WithSession
    public Uni<List<Product>> getAll() {
        return Product.listAll();
    }

    /**
     * Gets by id.
     *
     * @param id the id
     * @return the by id
     */
    @WithSession
    public Uni<Response> getById(Long id) {
        return Product.findById(id)
                .onItem().ifNull().failWith(()-> new NotFoundException("product not found with id : " + id))
                .onItem().transform(product -> Response.ok(product).build());
    }

    /**
     * Delete by id uni.
     *
     * @param id the id
     * @return the uni
     */
    @WithTransaction
    public Uni<Boolean> deleteById(Long id) {
        return Product.deleteById(id);
    }

    /**
     * Update uni.
     *
     * @param id      the id
     * @param updated the updated
     * @return the uni
     */
    @WithTransaction
    public Uni<Product> update(Long id, Product updated) {
        return Product.<Product>findById(id)
                .onItem().ifNotNull().transformToUni(product -> {
                    product.name = updated.name;
                    product.description = updated.description;
                    product.price = updated.price;
                    product.quantity = updated.quantity;
                    return product.persist();
                });
    }

    @WithSession
    public Uni<Boolean> checkStock(Long id, int count) {
        return Product.<Product>findById(id)
                .onItem().ifNotNull().transform(product -> product.quantity >= count);
    }

    @WithSession
    public Uni<List<Product>> getAllSortedByPrice() {
        return Product.list("ORDER BY price ASC");
    }
}
