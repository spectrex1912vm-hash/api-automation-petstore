package steps;

import io.restassured.response.Response;
import models.store.Order;
import org.openqa.selenium.devtools.v122.fetch.model.AuthChallengeResponse;
import specs.Specs;

import static io.restassured.RestAssured.given;

public class StoreSteps {
    public static Response getPetInventory () {
        return given()
                .spec(Specs.request)
                .when()
                .get("/store/inventory");
    }

    public static Response placeOrder (Order order) {
        return  given()
                .spec(Specs.request)
                .body(order)
                .when()
                .post("/store/order");

    }

    public static Response getOrderById(Long id) {
        return given()
                .spec(Specs.request)
                .when()
                .get("/store/order/" + id);
    }

    public static Response deleteOrder(Long id) {
        return given()
                .spec(Specs.request)
                .when()
                .delete("/store/order/" + id);
    }
}
