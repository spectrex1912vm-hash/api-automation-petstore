package factories;

import com.github.javafaker.Faker;
import models.user.User;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class UserFactory {
    public static User createDefaultUser() {
        return User.builder()
                .id(99135)
                .username("John3")
                .firstName("John")
                .lastName("loonf")
                .email("test@test.com")
                .password("qatest")
                .phone("123456789")
                .userStatus(998)
                .build();
    }

    public static User createRandomUser() {

        Faker faker = new Faker();

        return User.builder()
                        .id(faker.number().numberBetween(1000, 9999))
                        .username("user_" + faker.name().username())
                        .firstName(faker.name().firstName())
                        .lastName(faker.name().lastName())
                        .email(faker.internet().emailAddress())
                        .password("qatest")
                        .phone(faker.phoneNumber().subscriberNumber(9))
                        .userStatus(1)
                        .build();

    }

    public static User updateRandomUser(User user) {
        Faker faker = new Faker();

        return user.toBuilder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .phone(faker.phoneNumber().subscriberNumber(9))
                .build();
    }

    public static List<User> createRandomUsers(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> createRandomUser())
                .toList();
    }

    public static Map<String,String>loginParams(User user) {
        return Map.of(
                "username", user.getUsername(),
                "password", user.getPassword()
        );
    }

}
