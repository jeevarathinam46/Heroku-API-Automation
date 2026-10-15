package com.api.test;

import static io.restassured.RestAssured.*;

import com.utils.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class GetBookingIdAPITest {


    @Test
    public void getBookingId(){

         given()
                .baseUri(ConfigManager.getValue("BASE_URI"))
                .when()
                .get("/booking/22")
                .then()
                .statusCode(200)
                .log().body()
                .body("depositpaid", Matchers.equalTo(true))
                .body("totalprice", Matchers.notNullValue())
                 .body("bookingdates",Matchers.hasKey("checkin"), "bookingdates",Matchers.hasKey("checkout"));

    }
}
