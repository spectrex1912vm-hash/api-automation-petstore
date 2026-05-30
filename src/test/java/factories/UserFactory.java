package factories;

import models.User;

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

}
