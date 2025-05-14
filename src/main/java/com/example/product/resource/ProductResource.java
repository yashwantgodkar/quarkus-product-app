package com.example.product.resource;

import com.example.product.entity.Product;
import com.example.product.service.ProductService;
import io.smallrye.mutiny.Uni;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.logging.Logger;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductResource {

    @Inject
    ProductService productService;

    private static final Logger LOG = Logger.getLogger(ProductResource.class);

    @POST
    public Uni<Product> create(Product product) {
        LOG.infof("ProductResource :: create");
        return productService.create(product);
    }

    @GET
    public Uni<List<Product>> getAll() {
        LOG.infof("ProductResource :: getAll");
        return productService.getAll();
    }

    @GET
    @Path("/{id}")
    public Uni<Response> getById(@PathParam("id") Long id) {
        LOG.infof("ProductResource :: getById: %d", id);
        return productService.getById(id);
    }

    @PUT
    @Path("/{id}")
    public Uni<Product> update(@PathParam("id") Long id, Product product) {
        LOG.infof("ProductResource :: update: %d", id);
        return productService.update(id, product);
    }

    @DELETE
    @Path("/{id}")
    public Uni<Boolean> delete(@PathParam("id") Long id) {
        LOG.infof("ProductResource :: delete: %d", id);
        return productService.deleteById(id);
    }

    @GET
    @Path("/{id}/stock")
    public Uni<Boolean> checkStock(@PathParam("id") Long id, @QueryParam("count") int count) {
        LOG.infof("ProductResource :: checkStock: %d", id);
        return productService.checkStock(id, count);
    }

    @GET
    @Path("/sorted")
    public Uni<List<Product>> getAllSortedByPrice() {
        LOG.infof("ProductResource :: getAllSortedByPrice");
        return productService.getAllSortedByPrice();
    }
}
