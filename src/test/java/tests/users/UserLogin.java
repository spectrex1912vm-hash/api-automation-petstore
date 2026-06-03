package tests.users;

import factories.UserFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import models.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import specs.Specs;

import java.util.Map;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class UserLogin {
    @Epic("PetStore API")
    @Feature("User management")
    @Story("Login user with query params")
    @DisplayName("Login user")
    @Test
    void login (){
        User user = UserFactory.createDefaultUser();
        Map<String,String> params = UserFactory.loginParams(user);

        Response response = step("Login to PetStore", () ->
        given()
                .spec(Specs.request)
                .queryParams(params)
                .when()
                .get("/user/login")
        );

        step("Verify login response", () ->
                response.then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", containsString("logged in user session"))
        );
    }
}
