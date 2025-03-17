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
import site.nomoreparties.stellarburgers.HomePage;
import site.nomoreparties.stellarburgers.LoginPage;
import site.nomoreparties.stellarburgers.PersonalAccountPage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TransitionSectionTest extends SeleniumBase {
    Endpoints endpoints = new Endpoints();
    GenerationTestData generationTestData = new GenerationTestData();
    private User user = generationTestData.generationUserAccount();
    private List<SuccessCreateUserResponse> userList = new ArrayList<>();

    @Before
    public void setUp() {
        driver = getWebDriver("CHROME");
        Assert.assertNotNull("Ошибка инициализации WebDriver", driver);
        driver.get("https://stellarburgers.nomoreparties.site/");
        int expectedStatusCode = 200; // код ответа при успешном создании пользователя
        Response response = endpoints.createUser(user.getName(), user.getEmail(), user.getPassword(), expectedStatusCode);
        assertEquals(expectedStatusCode, response.getStatusCode());
        userList.add(response.as(SuccessCreateUserResponse.class));
    }

    @Test
    @Epic(value = "Переход между разделами")
    @Feature(value = "Проверка перехода по клику на «Личный кабинет»")
    @DisplayName("Переход между разделами")
    public void transitionSectionPersonalAccountTest() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        String expectedResult = "Оформить заказ"; // ожидаемый текст кнопки после авторизации
        Allure.step("Заполнение авторизационных данных", () -> {
            homePage.clickButtonLoginAccount();
            loginPage.fillFieldAuthorization(user.getEmail(), user.getPassword());
            loginPage.clickButtonAuthorization();
        });
        assertEquals(expectedResult, homePage.getTextLabelEntranceOrOrderCreate());
        homePage.clickLinkPersonalAccount();
        expectedResult = "В этом разделе вы можете изменить свои персональные данные"; // Ожидаемый текст описания раздела Личный кабиент
        assertEquals(expectedResult, personalAccountPage.getTextLabelInfo());
    }

    @Test
    @Epic(value = "Переход между разделами")
    @Feature(value = "Проверка перехода из личного кабинета в конструктор")
    @DisplayName("Переход между разделами")
    public void transitionSectionPersonalAccountConstructorTest() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        String expectedResult = "Оформить заказ"; // ожидаемый текст кнопки после авторизации
        Allure.step("Заполнение авторизационных данных", () -> {
            homePage.clickButtonLoginAccount();
            loginPage.fillFieldAuthorization(user.getEmail(), user.getPassword());
            loginPage.clickButtonAuthorization();
        });
        assertEquals(expectedResult, homePage.getTextLabelEntranceOrOrderCreate());
        homePage.clickLinkPersonalAccount();
        expectedResult = "В этом разделе вы можете изменить свои персональные данные"; // Ожидаемый текст описания раздела Личный кабиент
        assertEquals(expectedResult, personalAccountPage.getTextLabelInfo());
        expectedResult = "Соберите бургер";
        personalAccountPage.clickButtonConstructor();
        assertEquals(expectedResult, homePage.getTextLabelAssembleBurger());
    }

    @Test
    @Epic(value = "Переход между разделами")
    @Feature(value = "Проверка перехода из личного кабинета по логотипу Stellar Burgers")
    @DisplayName("Переход между разделами")
    public void transitionSectionPersonalAccountLogoTest() {
        HomePage homePage = new HomePage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PersonalAccountPage personalAccountPage = new PersonalAccountPage(driver);
        String expectedResult = "Оформить заказ"; // ожидаемый текст кнопки после авторизации
        Allure.step("Заполнение авторизационных данных", () -> {
            homePage.clickButtonLoginAccount();
            loginPage.fillFieldAuthorization(user.getEmail(), user.getPassword());
            loginPage.clickButtonAuthorization();
        });
        assertEquals(expectedResult, homePage.getTextLabelEntranceOrOrderCreate());
        homePage.clickLinkPersonalAccount();
        expectedResult = "В этом разделе вы можете изменить свои персональные данные"; // Ожидаемый текст описания раздела Личный кабиент
        assertEquals(expectedResult, personalAccountPage.getTextLabelInfo());
        expectedResult = "Соберите бургер";
        personalAccountPage.clickLinkLogo();
        assertEquals(expectedResult, homePage.getTextLabelAssembleBurger());
    }

    @After
    public void cleanUpResources() {
        if (userList.size() > 0) {
            endpoints.deleteUser(userList);
        }
    }
}
