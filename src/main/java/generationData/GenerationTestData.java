package generationData;

import com.github.javafaker.Faker;
import io.qameta.allure.Allure;
import model.User;

public class GenerationTestData {

    public User generationUserAccount() {
        Faker faker = new Faker();
        String[] name = new String[1];
        String[] email = new String[1];
        String[] password = new String[1];
        Allure.step("Формирование случайных тестовых данных", () -> {
            name[0] = faker.name().username();
            email[0] = faker.internet().emailAddress();
            password[0] = faker.internet().password();
        });
        return new User(name[0], email[0], password[0]);
    }
}
