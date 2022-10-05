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

public class RegresInTests {
    TestData testData = new TestData();

    @DisplayName("Success register 200")
    @Test
    void registerTest() {
        JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("email", testData.email);
        bodyRequest.put("password", testData.password);

        JSONObject expectedResponse = new JSONObject();
        expectedResponse.put("id", testData.id);
        expectedResponse.put("token", testData.token);

        Response extractResponse = given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(bodyRequest.toString())
                .when()
                .post(testData.basePathEndpoint + testData.registerEndpoint)
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
        bodyRequest.put("email", testData.email);

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(bodyRequest.toString())
                .when()
                .post(testData.basePathEndpoint + testData.registerEndpoint)
                .then()
                .log().status()
                .log().body()
                .statusCode(400)
                .body("error",is(testData.errorMessage));
    }


    @DisplayName("List user test 20")
    @Test
    void listUserTest() {

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .when()
                .get(testData.basePathEndpoint + testData.listUserEndpoint)
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("total", is(12));
    }

    @DisplayName("Successful loginUserEndpoint 200")
    @Test
    void loginTest() {
        JSONObject bodyRequest = new JSONObject();
        bodyRequest.put("email", testData.emailLogin);
        bodyRequest.put("password", testData.passwordLogin);

        given()
                .log().uri()
                .log().body()
                .contentType(JSON)
                .body(bodyRequest.toString())
                .when()
                .post(testData.basePathEndpoint + testData.loginUserEndpoint)
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
                .put(testData.basePathEndpoint + testData.updateUserEndpont)
                .then()
                .log().status()
                .log().body()
                .statusCode(200);
    }

}
