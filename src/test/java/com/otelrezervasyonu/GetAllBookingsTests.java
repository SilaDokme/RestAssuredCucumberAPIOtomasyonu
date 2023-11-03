package com.otelrezervasyonu;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class GetAllBookingsTests {

    //Çağrıyı oluşturmamız
    //Response kontroller
    //curl -i https://restful-booker.herokuapp.com/booking


    @Test //junit kullanıldığı için
    public  void getAllBookingTests(){

        given()
                .when()
                .get("https://restful-booker.herokuapp.com/booking")
                .then()
                .log().all()
                .statusCode(200);

    }
}
