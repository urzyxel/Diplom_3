import api.module.Endpoints;
import api.response.user.SuccessCreateUserResponse;
import generationData.GenerationTestData;
import io.qameta.allure.Allure;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.User;
import org.junit.*;
import site.nomoreparties.stellarburgers.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class LoginPersonalAccountTest extends SeleniumBase {
    private Endpoints endpoints = new Endpoints();
    private GenerationTestData generationTestData = new GenerationTestData();
    private User user = generationTestData.generationUserAccount();
    private List<SuccessCreateUserResponse> userList = new ArrayList<>();

    @Before
    public void setUp() {
        driver = getWebDriver("YANDEX");
        Assert.assertNotNull("Ошибка инициализации WebDriver", driver);
        driver.get("https://stellarburgers.nomoreparties.site/");
        int expectedStatusCode = 200; // код ответа при успешном создании пользователя
        Response response = endpoints.createUser(user.getName(), user.getEmail(), user.getPassword(), expectedStatusCode);
        assertEquals(expectedStatusCode, response.getStatusCode());
        userList.add(response.as(SuccessCreateUserResponse.class));
    }

    @Test
    @Epic(value = "Авторизация пользователя")
    @Feature(value = "Авторизация пользователя по кнопке «Войти в аккаунт» на главной")
    @DisplayName("Авторизация пользователя")
    public void authorizationButtonHomeTest() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        String expectedResult = "Оформить заказ"; // ожидаемый текст кнопки после авторизации
        Allure.step("Заполнение авторизационных данных", () -> {
            homePage.clickButtonLoginAccount();
            loginPage.fillFieldAuthorization(user.getEmail(), user.getPassword());
            loginPage.clickButtonAuthorization();
        });
        assertEquals(expectedResult, homePage.getTextLabelEntranceOrOrderCreate());
    }

    @Test
    @Epic(value = "Авторизация пользователя")
    @Feature(value = "Авторизация пользователя через кнопку в форме регистрации")
    @DisplayName("Авторизация пользователя")
    public void authorizationButtonPersonalAccountTest() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegisterPage registerPage = new RegisterPage(driver);
        String expectedResult = "Оформить заказ"; // ожидаемый текст кнопки после авторизации
        homePage.clickButtonLoginAccount();
        loginPage.clickLinkRegister();
        registerPage.clickLinkLogin();
        Allure.step("Заполнение авторизационных данных", () -> {
            loginPage.fillFieldAuthorization(user.getEmail(), user.getPassword());
            loginPage.clickButtonAuthorization();
        });
        assertEquals(expectedResult, homePage.getTextLabelEntranceOrOrderCreate());
    }

    @Test
    @Epic(value = "Авторизация пользователя")
    @Feature(value = "Авторизация пользователя через кнопку в форме восстановления пароля")
    @DisplayName("Авторизация пользователя")
    public void authorizationButtonRestoreAccountTest() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RestorePage restorePage = new RestorePage(driver);
        String expectedResult = "Оформить заказ"; // ожидаемый текст кнопки после авторизации
        homePage.clickButtonLoginAccount();
        loginPage.clickLinkRestore();
        restorePage.clickLinkLogin();
        Allure.step("Заполнение авторизационных данных", () -> {
            loginPage.fillFieldAuthorization(user.getEmail(), user.getPassword());
            loginPage.clickButtonAuthorization();
        });
        assertEquals(expectedResult, homePage.getTextLabelEntranceOrOrderCreate());
    }

    @Test
    @Epic(value = "Выход из личного кабинета")
    @Feature(value = "Разавторизация по кнопке Выход в личном кабинете")
    @DisplayName("Выход из личного кабинета")
    public void logoutButtonRestoreAccountTest() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RestorePage restorePage = new RestorePage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        String expectedResult = "Оформить заказ"; // ожидаемый текст кнопки после авторизации
        homePage.clickButtonLoginAccount();
        loginPage.clickLinkRestore();
        restorePage.clickLinkLogin();
        Allure.step("Заполнение авторизационных данных", () -> {
            loginPage.fillFieldAuthorization(user.getEmail(), user.getPassword());
            loginPage.clickButtonAuthorization();
        });
        assertEquals(expectedResult, homePage.getTextLabelEntranceOrOrderCreate());
        homePage.clickLinkPersonalAccount();
        expectedResult = "В этом разделе вы можете изменить свои персональные данные"; // Ожидаемый текст описания раздела Личный кабиент
        assertEquals(expectedResult, personalAccountPage.getTextLabelInfo());
        personalAccountPage.clickLinkLogout();
        expectedResult = "Вход";
        assertEquals(expectedResult, homePage.getTextLabelEntrance());
    }

    @After
    public void cleanUpResources() {
        if (userList.size() > 0) {
            endpoints.deleteUser(userList);
        }
    }
}
