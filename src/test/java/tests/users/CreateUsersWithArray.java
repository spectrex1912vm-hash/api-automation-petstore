package tests.users;

import factories.UserFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import models.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import specs.Specs;

import java.util.List;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CreateUsersWithArray {
    @Epic("PetStore API")
    @Feature("User management")
    @Story("Create multiple users with array")
    @DisplayName("Create users with array")
    @Test
    void createUsersWithArray() {
        List<User> users = UserFactory.createRandomUsers(3);
        step("Create users with array", () ->
                given()
                        .spec(Specs.request)
                        .body(users)
                        .when()
                        .post("/user/createWithArray")
                        .then()
                        .statusCode(200)
                        .body("code", equalTo(200))
                        .body("message", equalTo("ok"))
        );
    }
}
