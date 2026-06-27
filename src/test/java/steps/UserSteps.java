package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.user.User;
import specs.Specs;
import java.util.List;
import static io.restassured.RestAssured.given;


public class UserSteps {
    @Step("Create user")
    public static Response createUser(User userData) {
        return  given()
                .spec(Specs.request)
                .body(userData)
                .when()
                .post("/user");
    }

    @Step("Get user by user name")
    public static Response getUserByUserName(String username) {
        return given()
                .spec(Specs.request)
                .when()
                .get("/user/" + username);
    }

    @Step("Create user with Array")
    public static Response createUsersWithArray(List<User> users) {
        return given()
                .spec(Specs.request)
                .body(users)
                .when()
                .post("/user/createWithArray");
    }

    @Step("Create user with List")
    public static Response createUserWithList(List<User> users) {
        return given()
                .spec(Specs.request)
                .body(users)
                .when()
                .post("/user/createWithList");
    }

    @Step("Login user")
    public static Response login(User user) {
        return given()
                .spec(Specs.request)
                .queryParams("username", user.getUsername())
                .queryParams("password", user.getPassword())
                .when()
                .get("/user/login");
    }

    @Step("Logout current user")
    public static Response logout() {
        return given()
                .spec(Specs.request)
                .when()
                .get("/user/logout");
    }

    @Step("Update user")
    public static Response updateUser(String username, User updatedUser) {
        return given()
                .spec(Specs.request)
                .body(updatedUser)
                .when()
                .put("/user/" + username);
    }

    @Step("Delete user: {username}")
    public static Response deleteUser(String username) {
        return given()
                .spec(Specs.request)
                .when()
                .delete("/user/" + username);
    }


}
