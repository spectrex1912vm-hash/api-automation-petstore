package tests.store.negative;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.StoreSteps;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;

public class GetOrderWithInvalidId {
    @Epic("PetStore API")
    @Feature("Store management")
    @Story("Get order with invalid ID")
    @DisplayName("Get order with invalid ID test")
    @Test
    void getOrderWithInvalidIdTest() {
        String invalidId = "dssdgsddgs";

        StoreSteps.getOrderById(invalidId)
                .then()
                .statusCode(404)
                .body("code", equalTo(404))
                .body("type", equalTo("unknown"))
                .body("message", containsString("NumberFormatException"));



    }
}
