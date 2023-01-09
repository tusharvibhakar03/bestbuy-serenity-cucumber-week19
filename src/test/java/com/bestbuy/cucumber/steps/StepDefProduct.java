package com.bestbuy.cucumber.steps;

import com.bestbuy.productandstoreinfo.ProductSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class StepDefProduct {
    static ValidatableResponse response;
    static String nameProduct = null;
    static int productId;
    @Steps
    ProductSteps productSteps;
    @When("^User sends a GET request to list endpoint$")
    public void userSendsAGETRequestToListEndpoint() {
        response = productSteps.getAllProductInfo();
    }

    @Then("^User must get back a valid status code 200$")
    public void userMustGetBackAValidStatusCode() {
      response.statusCode(200);

    }

    @When("^I create a new product by providing the information name \"([^\"]*)\", type \"([^\"]*)\", upc \"([^\"]*)\", description \"([^\"]*)\", model \"([^\"]*)\", price  \"([^\"]*)\", shipping \"([^\"]*)\", manufacturer \"([^\"]*)\"$")
    public void iCreateANewProductByProvidingTheInformationNameTypeUpcDescriptionModelPriceShippingManufacturer(String name, String type, String upc, String description, String model, String price, String shipping, String manufacturer)
    {
       response = productSteps.createProduct(name,type,upc,description,model,Integer.parseInt(price),Integer.parseInt(shipping),manufacturer);
    }
    @Then("^I verify that the product  with \"([^\"]*)\" is created$")
    public void iVerifyThatTheProductWithIsCreated(String price) {
     response.statusCode(201);
     nameProduct = nameProduct+ TestUtils.getRandomValue();
        HashMap<String,Object>productMap = productSteps.getProductInfoByName(Integer.parseInt(price));
        Assert.assertThat(productMap, hasValue(Integer.parseInt(price)));
        productId =(int) productMap.get("id");
        System.out.println("Newly created Id"+productId);
    }

  @When("^I update a product with product Id and give information ProductId \"([^\"]*)\" name \"([^\"]*)\", type \"([^\"]*)\", upc \"([^\"]*)\", description \"([^\"]*)\", model \"([^\"]*)\", price  \"([^\"]*)\", shipping \"([^\"]*)\" , manufacturer \"([^\"]*)\"$")
  public void iUpdateAProductWithProductIdAndGiveInformationProductIdNameTypeUpcDescriptionModelPriceShippingManufacturer(String Id, String name, String type, String upc, String description, String model, String price, String shipping, String manufacturer)  {
      response = productSteps.updateProduct(productId,name,type,upc,description,model,Integer.parseInt(price),Integer.parseInt(shipping),manufacturer);
  }

    @Then("^I verify that the product with id productId \"([^\"]*)\" is updated$")
    public void iVerifyThatTheProductWithIdProductIdIsUpdated(String Id) {
        response.statusCode(200);
        productSteps.getProductId(productId);
    }

    @When("^I delete product with product Id$")
    public void iDeleteProductWithProductId() {
        response= productSteps.deleteProduct(productId);
        response.statusCode(200);
    }

    @Then("^I verify using  product id  that  data is deleted$")
    public void iVerifyUsingProductIdThatDataIsDeleted() {
        System.out.println("check product id "+productId);
        response= productSteps.getProductId(productId);
        response.statusCode(404);

    }

    @Given("^Check if the BestBuy application can be accessed by users$")
    public void checkIfTheBestBuyApplicationCanBeAccessedByUsers() {
    }






}
