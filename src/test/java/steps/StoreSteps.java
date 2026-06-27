package steps;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.store.Order;
import specs.Specs;
import static io.restassured.RestAssured.given;

public class StoreSteps {

    @Step("Get pet inventory")
    public static Response getPetInventory () {
        return given()
                .spec(Specs.request)
                .when()
                .get("/store/inventory");
    }

    @Step("Create order")
    public static Response placeOrder (Order order) {
        return  given()
                .spec(Specs.request)
                .body(order)
                .when()
                .post("/store/order");

    }

    @Step("Get order by id")
    public static Response getOrderById(Long id) {
        return given()
                .spec(Specs.request)
                .when()
                .get("/store/order/" + id);
    }

    @Step("Get order by id")
    public static Response getOrderById(String id) {
        return given()
                .spec(Specs.request)
                .when()
                .get("/store/order/" + id);
    }

    @Step("Delete order")
    public static Response deleteOrder(Long id) {
        return given()
                .spec(Specs.request)
                .when()
                .delete("/store/order/" + id);
    }

    @Step("Delete order")
    public static Response deleteOrder(String id) {
        return given()
                .spec(Specs.request)
                .when()
                .delete("/store/order/" + id);
    }
}
