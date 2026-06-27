package factories;

import com.github.javafaker.Faker;
import models.pet.Category;
import models.pet.Pet;
import models.pet.Tag;

import java.util.List;

public class PetFactory {

    public static Pet createRandomPet() {
        Faker faker = new Faker();

        return Pet.builder()
                .id(faker.number().randomNumber())
                .name(faker.animal().name())
                .category(Category.builder().
                        id(faker.number().randomNumber())
                        .name("Dog")
                        .build()
                )
                .photoUrls(List.of(
                        "https://test.com/photo.jpg"
                ))
                .tags(List.of(
                        Tag.builder()
                                .id(faker.number().randomNumber())
                                .name("friendly")
                                .build()
                ))
                .status("avaliable")
                .build();
    }

    public static Pet updateRandomPet(Pet pet) {
        Faker faker = new Faker();
        return pet.toBuilder()
                .name(faker.animal().name())
                .status("sold")
                .build();
    }
}
