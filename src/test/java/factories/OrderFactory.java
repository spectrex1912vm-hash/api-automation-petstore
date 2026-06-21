package factories;

import com.github.javafaker.Faker;
import models.store.Order;

import java.time.Instant;

public class OrderFactory {
    public static Order createRandomOrder() {
        Faker faker = new Faker();
        String[] statuses = {"placed", "approved", "delivered"};

        return Order.builder()
                .id(faker.number().randomNumber())
                .petId(faker.number().randomNumber())
                .quantity(faker.number().numberBetween(1, 100))
                .shipDate(Instant.now().toString())
                .status(statuses[faker.random().nextInt(statuses.length)])
                .complete(faker.bool().bool())
                .build();

    }
}
