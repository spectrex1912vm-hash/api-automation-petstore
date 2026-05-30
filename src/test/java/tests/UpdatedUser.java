package tests;

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
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UpdatedUser {

    @Epic("PetStore API")
    @Feature("User management")
    @Story("Updated User")
    @DisplayName("Update user")
    @Test
    void updateUser() {
        User userData = UserFactory.createDefaultUser();
        User updatedUser = UserFactory.createDefaultUser()
                .toBuilder()
                .firstName("John1")
                .phone("1234567899")
                .email("test1@test.com")
                .build();

        step("Update user", () ->
        given()
                .spec(Specs.request)
                .body(updatedUser)
                .when()
                .put("/user/" + userData.getUsername())
                .then()
                .body("message", equalTo("99135"))
                .log().body()
        );
        step("Get updated user by username", () -> {
            User actualUser = given()
                    .spec(Specs.request)
                    .when()
                    .get("/user/" + userData.getUsername())
                    .then()
                    .statusCode(200)
                    .extract().as(User.class);

            assertAll(
                    () -> assertEquals("John1", actualUser.getFirstName()),
                    () -> assertEquals("1234567899", actualUser.getPhone()),
                    () -> assertEquals("test1@test.com", actualUser.getEmail())
            );
        });
    }
}
