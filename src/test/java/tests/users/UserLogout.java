package tests.users;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import specs.Specs;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UserLogout {
    @Epic("PetStore API")
    @Feature("User management")
    @Story("Logout current user")
    @DisplayName("Logout user")
    @Test
    void logoutCurrentUser () {
        step("Logout current user", () ->
        given()
                .spec(Specs.request)
                .when()
                .get("/user/logout")
                .then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", equalTo("ok"))
        );
    }
}
