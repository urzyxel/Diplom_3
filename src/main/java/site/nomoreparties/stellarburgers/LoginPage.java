package site.nomoreparties.stellarburgers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    // форма Авторизации:
        //Поля
    private By editEmail = By.xpath("//div[@class='input__container']//label[text()='Email']/following-sibling::input[@name='name']"); // поле Email
    private By editPassword = By.xpath("//div[@class='input__container']//label[text()='Пароль']/following-sibling::input[@type='password']"); // поле Email
        //Кнопки
    private By buttonAuthorization = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']"); // кнопка Войти
        //Ссылки
    private By linkRegister = By.xpath("//a[text()='Зарегистрироваться']"); // ссылка Зарегистироваться
    private By linkRestore = By.xpath("//a[text()='Восстановить пароль']"); // ссылка Восстановить пароль
        //Надписи
    private By labelEntrance = By.xpath("//a[text()='Восстановить пароль']"); // ссылка Восстановить пароль

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLinkRegister(){
        driver.findElement(linkRegister).click();
    }

    public void clickButtonAuthorization(){
        driver.findElement(buttonAuthorization).click();
    }

    public void clickLinkRestore(){
        driver.findElement(linkRestore).click();
    }

    public void fillFieldAuthorization(String email, String password){
        driver.findElement(editEmail).sendKeys(email);
        driver.findElement(editPassword).sendKeys(password);
    }

    public String getTextLabelEntrance(){
        return driver.findElement(labelEntrance).getText();
    }
}
