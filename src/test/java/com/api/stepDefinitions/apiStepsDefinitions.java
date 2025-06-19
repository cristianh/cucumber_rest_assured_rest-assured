package com.api.stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;


import static org.testng.AssertJUnit.assertEquals;

public class apiStepsDefinitions {

    /**
     * given(): content type, cookies, headers, oauth
     * when(): request (post,get,patch, delete)
     * then(): assertions (status code, response body)
     */

    String BASE_URL = "http://localhost:3000/";
    RequestSpecification request;
    Response response;

    @Before
    public void setup(){
        //before
    }

    @Given("A list of books is available")
    public void a_list_of_books_is_available() {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        request.header("G-TOKEN","ROM831ESV");
        response = request.get("/books/29");
        System.out.println("Respuesta: "+ response.print());
    }

    @When("I add a book to my inventory list")
    public void i_add_a_book_to_my_inventory_list() {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given();
        request.header("G-TOKEN","ROM831ESV");
        request.header("Content-Type","application/json");
        response = request.body("{\n" +
                "    \"title\": \"El libro de la selva 2015\",\n" +
                "    \"author\": \"Christian Gaugart\",\n" +
                "    \"isbn\": \"1479104523\"\n" +
                "}").post("/books");


    }
    @Then("The book is added {int}")
    public void the_book_is_added(Integer code) {
        Assert.assertEquals(response.getStatusCode(),code);
    }

    @When("I delete a book from my inventory list")
    public void i_delete_a_book_from_my_inventory_list() {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given().auth().preemptive().basic("admin","admin");
        request.header("G-TOKEN","ROM831ESV");
        response = request.delete("/books/34");
    }
    @Then("The book is removed {int}")
    public void the_book_is_removed(Integer code) {
        Assert.assertEquals(response.getStatusCode(),code);
    }
    @When("I update a book from my inventory list")
    public void i_update_a_book_from_my_inventory_list() {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given().auth().preemptive().basic("admin","admin");
        request.header("G-TOKEN","ROM831ESV");
        request.header("Content-Type","application/json");
        response = request.body("{\n" +
                "    \"title\": \"El libro de la selva 2015-11\",\n" +
                "    \"author\": \"Christian Gaugart\",\n" +
                "    \"isbn\": \"1479104523\"\n" +
                "}").put("/books/31");
    }

    @Then("The book is updated {int}")
    public void the_book_is_updated(Integer code) {
        Assert.assertEquals(response.getStatusCode(),code);
    }
}
