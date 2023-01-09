package com.bestbuy.productandstoreinfo;

import com.bestbuy.constants.EndPoints;
import com.bestbuy.constants.Path;
import com.bestbuy.model.ProductPojo;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

import java.util.HashMap;


public class ProductSteps1 {
    @Step("Creating student with name : {0}, type: {1}, upc: {2}, description: {3} ,model:{4},price:{5},shipping:{6} and manufacturer: {7}")
    public ValidatableResponse createProduct(String name,String type,String upc,String description, String model, int price, int shipping,String manufacturer){
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        productPojo.setType(type);
        productPojo.setUpc(upc);
        productPojo.setDescription(description);
        productPojo.setModel(model);
        productPojo.setPrice(price);
        productPojo.setShipping(shipping);
        productPojo.setManufacturer(manufacturer);
        return SerenityRest.given().log().all()
               .basePath(Path.PRODUCT)
               .contentType(ContentType.JSON)
               .when()
               .body(productPojo)
               .post().then();


    }
    @Step("Get product details of id : {0}")
    public HashMap<String, Object> getProductInfoById(int productID){
        HashMap<String, Object> productMap = SerenityRest.given().log().all()
                .when()
               // .pathParams("id", productID)
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then()
                .statusCode(200)
                .extract()
                .path("");
        return productMap;

    }
    @Step("Update product details of id: {0}")
    public ValidatableResponse updateProduct(int productId, String name) {
        ProductPojo productPojo = new ProductPojo();
        productPojo.setName(name);
        return SerenityRest.given().log().all()
                .header("Content-Type", "application/json")
                .body(productPojo)
                .pathParam("id", productId)
                .when()
                .patch(EndPoints.UPDATE_PRODUCT_BY_ID)
                .then();
    }
    @Step("Deleting student information with studentId: {0}")

    public ValidatableResponse deleteProduct(int productId){
        return  SerenityRest.given().log().all()
                .basePath(Path.PRODUCT)
                .pathParam("id",productId)
                .when()
                .delete(EndPoints.DELETE_PRODUCT_BY_ID)
                .then();
    }
    @Step("Getting student information with studentId: {0}")

    public ValidatableResponse getProductId(int productId){
        return SerenityRest.given().log().all()
                .basePath(Path.PRODUCT)
                .pathParam("id",productId)
                .when()
                .get(EndPoints.GET_SINGLE_PRODUCT_BY_ID)
                .then();

    }
}
