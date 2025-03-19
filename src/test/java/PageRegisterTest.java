import api.module.Endpoints;
import api.response.user.SuccessCreateUserResponse;
import generationData.GenerationTestData;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.User;
import org.junit.Test;
import site.nomoreparties.stellarburgers.HomePage;
import site.nomoreparties.stellarburgers.LoginPage;
import site.nomoreparties.stellarburgers.RegisterPage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


public class PageRegisterTest extends SeleniumBase {

    Endpoints endpoints = new Endpoints();
    GenerationTestData generationTestData = new GenerationTestData();
    private static List<SuccessCreateUserResponse> userList = new ArrayList<>();

    @Test
    @Epic(value = "Регистрация пользователя")
    @Feature(value = "Проверка на успешную регистрацию")
    @DisplayName("Регистрация пользователя")
    public void successRegistrationTest() {
        HomePage homePage = new HomePage(driver);
        LoginPage pageLogin = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        int expectedStatusCode = 200; // код ответа при успешной авторизации
        User user = generationTestData.generationUserAccount();
        Allure.step("Заполнение данных для регистрации в форме", () -> {
            homePage.clickLinkPersonalAccount();
            pageLogin.clickLinkRegister();
            registerPage.fillFieldRegister(user.getName(), user.getEmail(), user.getPassword());
            registerPage.clickButtonRegister();
        });
        Response response = endpoints.loginUser(user.getEmail(), user.getPassword());
        assertEquals(expectedStatusCode, response.getStatusCode());
        userList.add(response.as(SuccessCreateUserResponse.class));
        endpoints.deleteUser(userList);
    }

    @Test
    @Epic(value = "Регистрация пользователя")
    @Feature(value = "Проверка ошибки для не корректного пароля по длинне менее 6 символов")
    @DisplayName("Регистрация пользователя: проверка на минимальную длинну пароля")
    public void minimumLengthRegistrationTest() {
        HomePage homePage = new HomePage(driver);
        LoginPage pageLogin = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        User user = generationTestData.generationUserAccount();
        String expectedResult = "Некорректный пароль"; // ожидаемый текст ошибки
        Allure.step("Заполнение данных для регистрации в форме", () -> {
            homePage.clickLinkPersonalAccount();
            pageLogin.clickLinkRegister();
            registerPage.fillFieldRegister(user.getName(), user.getEmail(), user.getPassword().substring(0, 5));
            registerPage.clickButtonRegister();
        });
        assertEquals(expectedResult, registerPage.getTextErrorRegister());
    }
}
