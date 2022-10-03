package in.regres;

import io.restassured.RestAssured;
import io.restassured.RestAssured.*;
import io.restassured.matcher.RestAssuredMatchers;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;

public class TestData {

    public String email = "eve.holt@reqres.in";
    public String password = "pistol";
    public int id = 4;
    public String token = "QpwL5tke4Pnpja7X4";

    public String register = "/api/register";
    public String basePath ="https://reqres.in";
    public String listUser = "/api/users?page=2";
    public String login = "/api/login";
    public String updateUser = "/api/users/4";
    public String errorMessage = "Missing password";
    public String emailLogin = "eve.holt@reqres.in";
    public String passwordLogin = "cityslicka";


}
