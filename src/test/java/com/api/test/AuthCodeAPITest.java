package com.api.test;

import com.api.requestmodel.UserCredentials;
import static com.utils.ConfigManager.*;
import io.restassured.http.ContentType;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class AuthCodeAPITest {

    private UserCredentials userCredentials;

    @BeforeTest
    public void setup(){
        userCredentials = new UserCredentials("admin","password123");
    }

    @Test
    public void authCodeTest(){

        String token = given()
                .baseUri(getValue("BASE_URI"))
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(userCredentials)
                .when()
                .post("/auth")
                .then()
                .statusCode(200)
                .log().all()
                .extract()
                .path("token");

        System.out.print(token);


    }
}
