package tests.store.positive;

import factories.OrderFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import models.store.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.StoreSteps;

import static org.hamcrest.Matchers.equalTo;

public class DeleteOrderById {
    @Epic("PetStore API")
    @Feature("Store management")
    @Story("Delete order by order id")
    @DisplayName("Delete order by id test")
    @Test
    void deleteOrderTest() {
        Order order = OrderFactory.createRandomOrder();
        Response createResponse = StoreSteps.placeOrder(order);
        Long id = createResponse.jsonPath().getLong("id");

        Response deleteResponse = StoreSteps.deleteOrder(id);
        deleteResponse.then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo(id.toString()));

        StoreSteps.getOrderById(id)
                .then()
                .statusCode(404)
                .body("message", equalTo("Order not found"));

    }
}
