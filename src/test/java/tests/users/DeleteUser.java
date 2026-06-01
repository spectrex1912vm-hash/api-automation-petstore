package tests.users;

import factories.UserFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import specs.Specs;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class DeleteUser {
    @Epic("PetStore API")
    @Feature("User management")
    @Story("Delete user")
    @DisplayName("Delete user")
    @Test
    void deleteUser () {
        User user = UserFactory.createDefaultUser();
        step("Create user", () ->
        given()
                .spec(Specs.request)
                .body(user)
                .when()
                .post("/user")
                .then()
                .statusCode(200)
        );
        step("Delete user", () ->
        given()
                .spec(Specs.request)
                .when()
                .delete("/user/" + user.getUsername())
                .then()
                .statusCode(200)
                .log().body()
        );

        step("Verify delete user", () ->
        given()
                .spec((Specs.request))
                .when()
                .get("/user/" + user.getUsername())
                .then()
                .statusCode(404)
                .body("code", equalTo(1))
                .body("type", equalTo("error"))
                .body("message", equalTo("User not found"))
        );
    }
}
