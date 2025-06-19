package com.api.stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.util.List;

import static org.testng.Assert.assertTrue;

public class api2StepsDefinitions {

    /**
     * given(): content type, cookies, headers, oauth
     * when(): request (post,get,patch, delete)
     * then(): assertions (status code, response body)
     */

    /**
     *   Response responseej = RestAssured
     *             .given()
     *                 .auth().preemptive().basic("admin", "admin")
     *                 .header("G-TOKEN", "ROM831ESV")
     *                 .header("Content-Type", "application/json")
     *             .when()
     *                 .get("/books") // Cambia por el endpoint correcto
     *             .then()
     *                 .extract().response();
     */

    String BASE_URL = "http://localhost:3000/";
    RequestSpecification request;
    Response response;
    String idRequest =  "36";
    String idRequestUpdate =  "30";

    @Before
    public void setup(){
        //before
    }

    @Given("I am an authorized user")
    public void i_am_an_authorized_user() {
        RestAssured.baseURI = BASE_URL;
        request = RestAssured.given().auth().preemptive().basic("admin","admin");
        request.header("G-TOKEN","ROM831ESV");
        request.header("Content-Type","application/json");

    }

    //Add
    @When("I send a POST request to add a new book")
    public void i_send_a_post_request_to_add_a_new_book() {
        response = request.body("{\n" +
                "    \"title\": \"El libro de la selva 2015-21\",\n" +
                "    \"author\": \"Christian Gaugart\",\n" +
                "    \"isbn\": \"1479104523\"\n" +
                "}").post("/books");
    }

    @Then("The response status code should be {int}")
    public void the_response_status_code_should_be(Integer code) {
        Assert.assertEquals(response.getStatusCode(),code);
    }
    @Then("The book should be appear in the inventory list")
    public void the_book_should_be_appear_in_the_inventory_list() {
        response = request.get("/books");
        System.out.println("Respuesta: "+ response.asString());

        List<String> libros = response.jsonPath().getList("title");
        assertTrue(libros.contains("El libro de la selva 2015-21"),
                "El libro no está en la lista");
    }

    //Delete
    @When("I send a DELETE request for an existing book")
    public void i_send_a_delete_request_for_an_existing_book() {
        response = request.delete("/books/"+idRequest);
    }
    @Then("The book should no longer appear in the inventory list")
    public void the_book_should_no_longer_appear_in_the_inventory_list() {
        response = request.get("/books/"+idRequest);
        Assert.assertEquals(response.getStatusCode(),404);
    }

    //Update
    @When("I send a PUT request with updated details for an existing book")
    public void i_send_a_put_request_with_updated_details_for_an_existing_book() {
        response = request.body("{\n" +
                "    \"title\": \"El libro de la selva 2015-13\",\n" +
                "    \"author\": \"Christian Gaugart\",\n" +
                "    \"isbn\": \"1479104523\"\n" +
                "}").put("/books/30");
    }
    @Then("The inventory should reflect the updated book information")
    public void the_inventory_should_reflect_the_updated_book_information() {
        response = request.get("/books/"+idRequestUpdate);
        //System.out.println(response.asString());
        Assert.assertEquals(response.getBody().jsonPath().getString("title"),"El libro de la selva 2015-13");
    }

}
