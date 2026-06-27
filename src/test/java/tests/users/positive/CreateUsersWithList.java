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

public class CreateUsersWithList {

    @Epic("PetStore API")
    @Feature("User management")
    @Story("Create multiple users with list")
    @DisplayName("Create user with list")
    @Test
    void createUsersWithListTest() {
        List<User> users = UserFactory.createRandomUsers(3);
        Response response = UserSteps.createUserWithList(users);
        response.then()
                .log().all()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("message", equalTo("ok"));
    }
}
