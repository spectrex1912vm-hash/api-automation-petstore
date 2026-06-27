package tests.store.e2e;

import factories.OrderFactory;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import models.store.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.StoreSteps;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class OrderLifecycleE2E {
    @Epic("PetStore API")
    @Feature("Store management")
    @Story("Order lifecycle")
    @DisplayName("Order e2e test")
    @Test
    void orderLifecycleFullTest() {
        Order order = OrderFactory.createRandomOrder();

        Response createResponse = StoreSteps.placeOrder(order);
        createResponse.then()
                .statusCode(200);


        Order actualOrder = StoreSteps.getOrderById(order.getId())
                .then()
                .statusCode(200)
                .extract()
                .as(Order.class);

        assertThat(actualOrder)
                .usingRecursiveComparison()
                .ignoringFields("shipDate")
                .isEqualTo(order);

        Response deleteResponse = StoreSteps.deleteOrder(order.getId());
        deleteResponse.then()
                .statusCode(200)
                .body("code", equalTo(200))
                .body("type", equalTo("unknown"))
                .body("message", equalTo(order.getId().toString()));

        StoreSteps.getOrderById(order.getId())
                .then()
                .statusCode(404)
                .body("message", equalTo("Order not found"));



    }
}
