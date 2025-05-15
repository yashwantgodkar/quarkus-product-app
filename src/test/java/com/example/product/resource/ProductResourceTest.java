package com.example.product.resource;

import com.example.product.entity.Product;
import com.example.product.service.ProductService;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class ProductResourceTest {

    @InjectMock
    ProductService productService;

    @Test
    void testCreate() {
        Product product = new Product();
        product.name = "TV";
        product.price = 1200.0;
        product.quantity = 3;

        when(productService.create(product)).thenReturn(Uni.createFrom().item(product));

        ProductResource resource = new ProductResource();
        resource.productService = productService;

        Product result = resource.create(product).await().indefinitely();
        assertEquals("TV", result.name);
        verify(productService, times(1)).create(product);
    }

    @Test
    void testGetAll() {
        List<Product> products = List.of(new Product());
        when(productService.getAll()).thenReturn(Uni.createFrom().item(products));

        ProductResource resource = new ProductResource();
        resource.productService = productService;

        List<Product> result = resource.getAll().await().indefinitely();
        assertEquals(1, result.size());
        verify(productService).getAll();
    }

    @Test
    void testGetById() {
        Long id = 1L;
        Product product = new Product();
        product.id = id;

        when(productService.getById(id)).thenReturn(Uni.createFrom().item(Response.ok(product).build()));

        ProductResource resource = new ProductResource();
        resource.productService = productService;

        Response response = resource.getById(id).await().indefinitely();
        assertEquals(200, response.getStatus());
        verify(productService).getById(id);
    }

    @Test
    void testUpdate() {
        Long id = 1L;
        Product product = new Product();
        product.name = "Speaker";

        when(productService.update(eq(id), any(Product.class)))
                .thenReturn(Uni.createFrom().item(product));

        ProductResource resource = new ProductResource();
        resource.productService = productService;

        Product updated = resource.update(id, product).await().indefinitely();
        assertEquals("Speaker", updated.name);
        verify(productService).update(id, product);
    }

    @Test
    void testDelete() {
        Long id = 1L;
        when(productService.deleteById(id)).thenReturn(Uni.createFrom().item(true));

        ProductResource resource = new ProductResource();
        resource.productService = productService;

        Boolean deleted = resource.delete(id).await().indefinitely();
        assertTrue(deleted);
        verify(productService).deleteById(id);
    }

    @Test
    void testCheckStock() {
        Long id = 1L;
        int count = 2;
        when(productService.checkStock(id, count)).thenReturn(Uni.createFrom().item(true));

        ProductResource resource = new ProductResource();
        resource.productService = productService;

        Boolean available = resource.checkStock(id, count).await().indefinitely();
        assertTrue(available);
        verify(productService).checkStock(id, count);
    }

    @Test
    void testGetAllSortedByPrice() {
        List<Product> sortedProducts = List.of(new Product());
        when(productService.getAllSortedByPrice()).thenReturn(Uni.createFrom().item(sortedProducts));

        ProductResource resource = new ProductResource();
        resource.productService = productService;

        List<Product> result = resource.getAllSortedByPrice().await().indefinitely();
        assertEquals(1, result.size());
        verify(productService).getAllSortedByPrice();
    }
}