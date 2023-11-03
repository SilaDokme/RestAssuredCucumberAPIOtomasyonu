package com.otelrezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

public class UpdateBookingTests extends BaseTest {
    @Test
    public void updateBookingTests(){

        //        curl -X PUT \
//        https://restful-booker.herokuapp.com/booking/1 \
//        -H 'Content-Type: application/json' \
//        -H 'Accept: application/json' \
//        -H 'Cookie: token=abc123' \
//        -d '{
//        "firstname" : "James",
//                "lastname" : "Brown",
//                "totalprice" : 111,
//                "depositpaid" : true,
//                "bookingdates" : {
//            "checkin" : "2018-01-01",
//                    "checkout" : "2019-01-01"
//        },
//        "additionalneeds" : "Breakfast"
//    }'

        //token oluşturma
       String token =  createToken();


        //Rezervasyon oluşturma
        Response creatBookingObject = creatBooking();
       int bookingId = creatBookingObject.jsonPath().getJsonObject("bookingid");

        //Request yap
        Response response = given()
                .contentType(ContentType.JSON)
                .header("Cookie","token=" + token)
                .body(bookingObject("Aslı","Çiçek",150))
                .put("https://restful-booker.herokuapp.com/booking/" + bookingId);

        response.prettyPrint();

        //Assertion / Test yaz
        String firstName = response.jsonPath().getJsonObject("firstname");
        String lastName = response.jsonPath().getJsonObject("lastname");
        int totaPrice = response.jsonPath().getJsonObject("totaprice");

        Assertions.assertEquals("Ayla",firstName);
        Assertions.assertEquals("Çiçek",lastName);
        Assertions.assertEquals(150,totaPrice);

    }


    //token oluşturma
    public String createToken(){
        JSONObject body = new JSONObject();
        body.put("username","admin");
        body.put("password","password123");


        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(body.toString())
                .log().all()
                .post("https://restful-booker.herokuapp.com/auth");

        response.prettyPrint();

        return response.jsonPath().getJsonObject("token");

    }
}
