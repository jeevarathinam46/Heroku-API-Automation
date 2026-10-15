package com.api.test;

import static io.restassured.RestAssured.*;

import com.api.requestmodel.BookingDetails;
import com.api.requestmodel.Bookingdates;
import com.utils.ConfigManager;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CreateBookingAPITest {

    private BookingDetails bookingDetails;
    private Bookingdates bookingdates;

    @BeforeTest
    public void setup(){
        bookingdates = new Bookingdates("2018-01-01","2019-01-01");
        bookingDetails = new BookingDetails("Jeeva","rathinam",1000,true,bookingdates,"Breakfast");
    }

    @Test
    public void createBooking(){

        given()
                .baseUri(ConfigManager.getValue("BASE_URI"))
                .contentType(ContentType.JSON)
                .body(bookingDetails)
                .when()
                .post("/booking")
                .then()
                .log().all()
                .statusCode(200)
                .body("bookingid", Matchers.notNullValue())
                .body("booking.totalprice", Matchers.greaterThan(999))
                .body("booking.bookingdates",Matchers.hasKey("checkin"), "booking.bookingdates",Matchers.hasKey("checkout"),"booking.additionalneeds",
                        Matchers.equalToIgnoringCase("Breakfast"))
                .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchema/bookingschema.json"));


    }
}
