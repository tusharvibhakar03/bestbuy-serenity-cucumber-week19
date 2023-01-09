package com.bestbuy.cucumber.steps;

import com.bestbuy.productandstoreinfo.StoreSteps;
import com.bestbuy.utils.TestUtils;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.hasValue;

public class StepDefStore {
    static ValidatableResponse response;
    static String nameStore = null;
    static int storeId;
    @Steps
    StoreSteps storeSteps;

    @When("^User sends a GET request to list endpoint for store$")
    public void userSendsAGETRequestToListEndpointForStore() {
        response = storeSteps.getAllStoreInfo();
    }


    @Then("^User must get back a valid status code  for store 200$")
    public void userMustGetBackAValidStatusCodeForStore() {
        response.statusCode(200);
    }


    @When("^I create a new Store by providing the information name \"([^\"]*)\", address \"([^\"]*)\", city \"([^\"]*)\", state \"([^\"]*)\", zip \"([^\"]*)\", type  \"([^\"]*)\", address- \"([^\"]*)\", lat \"([^\"]*)\",lng \"([^\"]*)\",hours \"([^\"]*)\",services \"([^\"]*)\"$")
    public void iCreateANewStoreByProvidingTheInformationNameAddressCityStateZipTypeAddressLatLngHoursServices(String name, String address, String city, String state, String zip, String type, String address2, String lat, String lng, String hours, String services) {
        Map<String, Object> services1 = new HashMap<>();
        Map<String, Object> servicesData = new HashMap<String, Object>();
        servicesData.put("id", 100);
        servicesData.put("name", services);
        servicesData.put("createdAt", "2023-02-01");
        servicesData.put("updatedAt", "2023-02-01");
        Map<String, Object> storeServices = new HashMap<String, Object>();
        storeServices.put("createdAt", "2023-02-01");
        storeServices.put("updatedAt", "2023-02-01");
        storeServices.put("stordId", 101);
        storeServices.put("serviceId", 11);
        servicesData.put("storeservices", storeServices);
        services1.put("services", servicesData);
        Map<String, Object> servicesList = new HashMap<>();
        servicesList.put("name", services1);
        response = storeSteps.createStore(name, address, city, state, zip, type, address2, Integer.parseInt(lat), Integer.parseInt(lng), hours, servicesList);
    }

    @Then("^I verify that the store  with lat \"([^\"]*)\" is created$")
    public void iVerifyThatTheStoreWithLatIsCreated(String lat) {
        response.statusCode(201);
        nameStore = nameStore + TestUtils.getRandomValue();
        HashMap<String, Object> storeMap = storeSteps.getProductInfoByName(Integer.parseInt(lat));
        Assert.assertThat(storeMap, hasValue(Integer.parseInt(lat)));
        storeId = (int) storeMap.get("id");
        System.out.println("Newly created Id" + storeId);
    }

    @When("^I update a store with storeId and give information storeId \"([^\"]*)\" name \"([^\"]*)\", address \"([^\"]*)\", city \"([^\"]*)\", state \"([^\"]*)\", zip \"([^\"]*)\", type  \"([^\"]*)\", address- \"([^\"]*)\", lat \"([^\"]*)\",lng \"([^\"]*)\",hours \"([^\"]*)\",services \"([^\"]*)\"$")
    public void iUpdateAStoreWithStoreIdAndGiveInformationStoreIdNameAddressCityStateZipTypeAddressLatLngHoursServices(String Id,String name, String address, String city, String state, String zip, String type, String address2, String lat, String lng, String hours, String services) {
        nameStore = name+"-updated-data";
        Map<String, Object> services1 = new HashMap<>();
        Map<String, Object> servicesData = new HashMap<String, Object>();
        servicesData.put("id", 100);
        servicesData.put("name", services);
        servicesData.put("createdAt", "2023-02-01");
        servicesData.put("updatedAt", "2023-02-01");
        Map<String, Object> storeServices = new HashMap<String, Object>();
        storeServices.put("createdAt", "2023-02-01");
        storeServices.put("updatedAt", "2023-02-01");
        storeServices.put("stordId", 101);
        storeServices.put("serviceId", 11);
        servicesData.put("storeservices", storeServices);
        services1.put("services", servicesData);
        Map<String, Object> servicesList = new HashMap<>();
        servicesList.put("name", services1);
        response = storeSteps.updateStore(storeId,nameStore, address, city, state, zip, type, address2, Integer.parseInt(lat), Integer.parseInt(lng), hours,servicesList);
    }

    @Then("^I verify that the store with id storeId \"([^\"]*)\" is updated$")
    public void iVerifyThatTheStoreWithIdStoreIdIsUpdated(String Id) {
        response.statusCode(200);
        storeSteps.getProductId(storeId);

    }

    @When("^I delete store with store Id$")
    public void iDeleteStoreWithStoreId() {
        response= storeSteps.deleteProduct(storeId);
        response.statusCode(200);

    }

    @Then("^I verify using  store id  that  data is deleted$")
    public void iVerifyUsingStoreIdThatDataIsDeleted() {
        System.out.println("check store id "+storeId);
        response= storeSteps.getProductId(storeId);
        response.statusCode(404);

    }
}
