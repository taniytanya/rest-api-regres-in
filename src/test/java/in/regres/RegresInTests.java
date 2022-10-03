package in.regres;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.json.JSONObject;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegresInTests extends TestData {

    @DisplayName("Success register 200")
    @Test
    void registerTest() {
        JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("email", email);
        bodyRequest.put("password", password);

        JSONObject expectedResponse = new JSONObject();
        expectedResponse.put("id", id);
        expectedResponse.put("token", token);

        Response extractResponse = given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(bodyRequest.toString())
                .when()
                .post(basePath + register)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .extract().response();

        String extractResponseString = extractResponse.asString();


        assertEquals(extractResponseString, expectedResponse.toString());


    }

    @DisplayName("Unsuccessful register without password 400")
    @Test
    void negative415RegisterTest() {
        JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("email", email);

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(bodyRequest.toString())
                .when()
                .post(basePath + register)
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error",is(errorMessage));
    }


    @DisplayName("List user test 200")
    @Test
    void listUserTest() {

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .when()
                .get(basePath + listUser)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(12));
    }

    @DisplayName("Successful login 200")
    @Test
    void loginTest() {
        JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("email", emailLogin);
        bodyRequest.put("password", passwordLogin);

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(bodyRequest.toString())
                .when()
                .post(basePath + login)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("token",is(notNullValue()))
        ;

    }

    @DisplayName("Update user 200")
    @Test
    void updateJobTest() {

        given()
                .log().uri()
                .log().body()
                .when()
                .put(basePath + updateUser)
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

}
