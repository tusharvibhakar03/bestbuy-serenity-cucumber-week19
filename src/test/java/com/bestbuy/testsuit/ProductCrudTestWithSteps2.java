package com.bestbuy.testsuit;

import com.bestbuy.productandstoreinfo.ProductSteps1;
import com.bestbuy.testbase.TestBase;
import com.bestbuy.utils.TestUtils;
import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;


import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class ProductCrudTestWithSteps2 extends TestBase {

   static String name ="Duracel11"+ TestUtils.getRandomValue();
   static String type ="max";
   static String upc ="ABC";
  static String model ="AAA";
   static int price = 10;
   static int shipping = 20;
   static  String description ="asdf";
   static  String manufacturer = "asdfsd";

    static int productId;
    @Steps
    ProductSteps1 productSteps;

    @Title("This will create new product")
    @Test
    public void test001() {
               ValidatableResponse response = productSteps.createProduct( name,type,upc,description,model,price,shipping,manufacturer);
        response.log().all().statusCode(201);
        productId=response.log().all().extract().path("id");
        System.out.println("created id "+productId);
    }
  //  @Title("Verify if the student was added to the application")
//    @Test
//    public void test002(){
//
//      //  HashMap<String, Object> studentMap = productSteps.getProductInfoByName(productId);
//        Map<String,?> studentMap = productSteps.getProductInfoByName(productId);
//        System.out.println("name"+name);
//        Assert.assertThat(studentMap, hasValue(name));
//      //  productId = (int) studentMap.get("id");
//        System.out.println("created new data"+productId);
//
//    }
  @Title("This test will Update the product information")
  @Test
  public void test003() {
      name = name + "_updated";
    productSteps.updateProduct(productId,name).statusCode(200).log().all();
     HashMap<String,Object> productMapData = productSteps.getProductInfoById(productId);
      Assert.assertThat(productMapData,hasValue(name));
  }
    @Title("Delete the store and verify if the store is deleted!")
    @Test
    public void test004() {
        System.out.println("Id which wanted to delete"+productId);
       productSteps.deleteProduct(productId).statusCode(200);
        productSteps.getProductId(productId).statusCode(404);

    }


}
