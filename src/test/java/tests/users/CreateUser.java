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
public class CreateUser {

    @Epic("PetStore API")
    @Feature("User management")
    @Story("Create user")
    @DisplayName("Create new user with valid data")
    @Test
    void CreateUserTest() {
        User userData = UserFactory.createDefaultUser();

        step("Create a new user", () ->
        given()
                .spec(Specs.request)
                .body(userData)
                .when()
                .post("/user")
                .then()
                .spec(Specs.responseSpec)
                .body("message", equalTo("99135"))
                .log().body()
        );

        step("Get created user by username", () -> given()
                        .spec(Specs.request)
                .when()
                        .get("/user/" + userData.getUsername())
                .then()
                .statusCode(200)
                .body("id", equalTo(99135))
        );
    }
}
