package com.otelrezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.security.PublicKey;

import static io.restassured.RestAssured.given;

public class CreateBookingTests extends BaseTest {

    @Test
    public void createBookingTests(){


//        curl -X POST \
//        https://restful-booker.herokuapp.com/booking \
//        -H 'Content-Type: application/json' \
//        -d '{
//        "firstname" : "Jim",
//                "lastname" : "Brown",
//                "totalprice" : 111,
//                "depositpaid" : true,
//                "bookingdates" : {
//            "checkin" : "2018-01-01",
//                    "checkout" : "2019-01-01"
//        },
//        "additionalneeds" : "Breakfast"
//    }'

        //Body oluşturma
//        JSONObject body = new JSONObject();
//        body.put("firstname","Sıla");
//        body.put("lastname","Dökme");
//        body.put("totalprice",200);
//        body.put("depositpaid",true);
//
//        JSONObject bookingDates = new JSONObject();
//        bookingDates.put("checkin","2018-01-01");
//        bookingDates.put("checkout","2019-01-01");
//
//        body.put("bookingdates", bookingDates);
//        body.put("additionalneeds", "Evcil hayvan kabul eden oda");


        //Çağrıyı gerçekleştir

        Response response = creatBooking();

//        Response response = given()
//                .when()
//                .contentType(ContentType.JSON)
//                .body(bookingObject())
//                .post("https://restful-booker.herokuapp.com/booking");
//
//        response.prettyPrint();
//
//
//        //Assertionları yaz
//
//        response
//                .then()
//                .statusCode(200);




        Assertions.assertEquals("Sıla", response.jsonPath().getJsonObject("booking.firstname"));
        Assertions.assertEquals("Dökme", response.jsonPath().getJsonObject("booking.lastname"));
        Assertions.assertEquals("true", response.jsonPath().getJsonObject("booking.depositpaid"));
        Assertions.assertEquals(200,(Integer) response.jsonPath().getJsonObject("booking.totalprice"));


    }
}
