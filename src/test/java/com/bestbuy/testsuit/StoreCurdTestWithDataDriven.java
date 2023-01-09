package com.bestbuy.testsuit;

import com.bestbuy.productandstoreinfo.StoreSteps;
import com.bestbuy.testbase.TestBase;
import net.serenitybdd.junit.runners.SerenityParameterizedRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import net.thucydides.junit.annotations.UseTestDataFrom;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;
import java.util.Map;

@UseTestDataFrom("src/test/java/resources/testdata/storeinfo.csv")
@RunWith(SerenityParameterizedRunner.class)
public class StoreCurdTestWithDataDriven extends TestBase {
    private String name;
    private String type;
    private String address;
    private String address2;
    private String city;
    private String state;
    private String zip;
    private int  lat;
    private int  lng;
    private  int  services;
    private String hours;

     static int id;
    @Steps
    StoreSteps storeSteps;
    @Title("This will create new store")
    @Test
    public void test001() {
        Map<String,Object>services1= new HashMap<>();
        Map<String,Object>servicesData=new HashMap<String,Object>();
        servicesData.put("id",services);
        servicesData.put("name","viral");
        servicesData.put("createdAt","2023-02-01");
        servicesData.put("updatedAt","2023-02-01");
        Map<String,Object>storeServices=new HashMap<String,Object>();
        storeServices.put("createdAt","2023-02-01");
        storeServices.put("updatedAt","2023-02-01");
        storeServices.put("stordId",101);
        storeServices.put("serviceId",11);
        servicesData.put("storeservices",storeServices);
        services1.put("services",servicesData);
        Map<String,Object>servicesList = new HashMap<>();
        servicesList.put("name",services1);
           storeSteps.createStore(name,address,city,state,zip,type,address2,lat,lng,hours,servicesList);

    }

}
