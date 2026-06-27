package tests.store.negative;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.StoreSteps;

import static org.hamcrest.Matchers.equalTo;

public class GetNotExistingOrder {
    @Epic("PetStore API")
    @Feature("Store management")
    @Story("Get not existing order")
    @DisplayName("Get not existing order test")
    @Test
    void getNotExistingOrderTest() {
        Long id = 43_643_636L;
        Response getResponse = StoreSteps.getOrderById(id);
        getResponse.then()
                .statusCode(404)
                .body("code", equalTo(1))
                .body("type", equalTo("error"))
                .body("message", equalTo("Order not found"));
    }
}
