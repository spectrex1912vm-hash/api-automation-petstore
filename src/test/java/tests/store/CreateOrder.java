package tests.store;

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

public class CreateOrder {
    @Epic("PetStore API")
    @Feature("Store management")
    @Story("Create order")
    @DisplayName("Create order test")
    @Test
    void createOrderTest() {
        Order order = OrderFactory.createRandomOrder();
        Response response = StoreSteps.placeOrder(order);

        response.then()
                .statusCode(200)
                .body("id",equalTo(order.getId().intValue()))
                .body("petId", equalTo(order.getPetId().intValue()))
                .body("quantity",equalTo(order.getQuantity()))
                .body("status", equalTo(order.getStatus()))
                .body("complete", equalTo(order.getComplete()));

    }
}
