package tests.users.positive;

import factories.UserFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import models.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.UserSteps;
import java.util.List;
import static org.hamcrest.Matchers.equalTo;

public class CreateUsersWithArray {
    @Epic("PetStore API")
    @Feature("User management")
    @Story("Create multiple users with array")
    @DisplayName("Create users with array")
    @Test
    void createUsersWithArrayTest() {
        List<User> users = UserFactory.createRandomUsers(3);
        Response response = UserSteps.createUsersWithArray(users);

        response.then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", equalTo("ok"));

    }
}
