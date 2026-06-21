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
import static org.assertj.core.api.Assertions.assertThat;

public class GetOrderById {
    @Epic("PetStore API")
    @Feature("Store management")
    @Story("Find order by ID")
    @DisplayName("Get order by ID test")
    @Test
    void getOrderByIdTest() {
        Order expectedOrder = OrderFactory.createRandomOrder();
        Response createResponse = StoreSteps.placeOrder(expectedOrder);
        Long id = createResponse.jsonPath().getLong("id");

        Response getResponse = StoreSteps.getOrderById(id);

        Order actualOrder = getResponse.as(Order.class);

        getResponse.then()
                        .statusCode(200);

        assertThat(actualOrder)
                .usingRecursiveComparison()
                .ignoringFields("shipDate")
                .isEqualTo(expectedOrder);



    }
}
