package com.stefania.my_bottles.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.stefania.my_bottles.BaseFunctionalTests;
import com.stefania.my_bottles.domain.Bottle;
import org.junit.Test;

import javax.ws.rs.core.MediaType;

import static com.jayway.restassured.RestAssured.given;
import static com.jayway.restassured.RestAssured.when;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class BottlesControllerFunctionalTests extends BaseFunctionalTests {

    private ObjectMapper mapper = new ObjectMapper();


    @Test
    public void getBottles_usernameStefania_returnsBottles() {
        given().log().all()
                .get("/bottles/stefania")
                .then().log().all()
                .statusCode(200)
                .body("[0].id", equalTo(0),
                        "[0].name", equalTo("Prosecco"),
                        "[0].producer", equalTo("Martini"));
    }

    @Test
    public void createAndRetrieveBottle_usernameStefania_successful() throws JsonProcessingException {
        Bottle bottle = new Bottle("Coldsaliz", "Valdobbiane");

        int id = given().log().all()
                .body(mapper.writeValueAsString(bottle))
                .accept(MediaType.APPLICATION_JSON)
                .contentType(ContentType.JSON)
                .when()
                .post("/bottles/stefania")
                .then().log().all()
                .statusCode(200)
                .body("name", containsString("Coldsaliz"),
                        "producer", containsString("Valdobbiane"))
                .extract()
                .path("id");

        given().log().all()
                .when()
                .get("/bottles/stefania")
                .then().log().all()
                .statusCode(200)
                .body("["+id+"].name", equalTo("Coldsaliz"));
    }
}
