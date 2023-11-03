package com.otelrezervasyonu;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;

public class BaseTest {

    protected Response creatBooking(){
        Response response = given()
                .when()
                .contentType(ContentType.JSON)
                .body(bookingObject("Sıla", "Dökme",200))
                .post("https://restful-booker.herokuapp.com/booking");

        response.prettyPrint();

        return response;
    }

    protected String bookingObject(String firstName, String lastName, int totalPrice ){
        JSONObject body = new JSONObject();
        body.put("firstname","firstName");
        body.put("lastname","lastName");
        body.put("totalprice",totalPrice);
        body.put("depositpaid",true);

        JSONObject bookingDates = new JSONObject();
        bookingDates.put("checkin","2018-01-01");
        bookingDates.put("checkout","2019-01-01");

        body.put("bookingdates", bookingDates);
        body.put("additionalneeds", "Evcil hayvan kabul eden oda");

        return body.toString();
    }
}
