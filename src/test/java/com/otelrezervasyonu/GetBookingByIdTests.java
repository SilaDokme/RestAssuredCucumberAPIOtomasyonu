package com.otelrezervasyonu;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetBookingByIdTests extends BaseTest{


    @Test
    public void getBookingById(){
        //Çağrıyı oluşturmamız
        //Response kontroller
        //curl -i https://restful-booker.herokuapp.com/booking/1
        given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/701")
                .then()
                .log().all()
                .statusCode(200);

    }


    @Test
    public void getBookingById2() {

        Response newBooking = creatBooking();
        int reservationId = newBooking.jsonPath().getJsonObject("bookingid");

        Response response = given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking/" + reservationId);
        //.get("https://restful-booker.herokuapp.com/booking/37");
        response
                .then()
                .statusCode(200);
        response.prettyPrint();

        String firstname =  response.jsonPath().getJsonObject("firstname");
        String lastname =  response.jsonPath().getJsonObject("lastname");
        int totalprice = response.jsonPath().getJsonObject("totalprice");
        Assertions.assertEquals("Dökme" , lastname);
        Assertions.assertEquals("Sıla" , firstname);
        Assertions.assertEquals(200 , totalprice );
    }
}
