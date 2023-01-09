package com.bestbuy.testsuit;


import com.bestbuy.productandstoreinfo.ProductSteps;
import com.bestbuy.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

@UseTestDataFrom("src/test/java/resources/testdata/productinfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class ProductCrudTestWithDataDrivenTest extends TestBase {

    private String name;
    private String type;
    private String upc;
    private String description;
    private String model;
    private int  price;
    private int shipping;
     private String manufacturer;
    @Steps
    ProductSteps productSteps;

    @Title("This will create new product")
    @Test
    public void test001() {

        productSteps.createProduct(name, type, upc, description, model, price, shipping, manufacturer).statusCode(201);
    }


}
