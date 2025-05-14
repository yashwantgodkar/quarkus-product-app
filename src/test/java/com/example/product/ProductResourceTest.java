package com.example.product;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import org.junit.jupiter.api.Test;

import static org.hamcrest.Matchers.*;

@QuarkusTest
public class ProductResourceTest {

    @Test
    public void testCreateProduct() {
        RestAssured.given()
            .contentType("application/json")
            .body("{ \"name\": \"Book\", \"description\": \"A good book\", \"price\": 10.0, \"quantity\": 50 }")
            .when().post("/products")
            .then()
            .statusCode(200)
            .body("name", is("Book"));
    }

    @Test
    public void testGetProducts() {
        RestAssured.get("/products")
            .then()
            .statusCode(200)
            .body("size()", greaterThanOrEqualTo(0));
    }
}
