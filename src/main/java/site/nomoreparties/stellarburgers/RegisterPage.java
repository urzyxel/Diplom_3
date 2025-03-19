package site.nomoreparties.stellarburgers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;

    // форма Регистрацция:
        //Поля
    private By editName = By.xpath("//div[@class='input__container']//label[text()='Имя']/following-sibling::input[@name='name']"); // поле Имя
    private By editEmail = By.xpath("//div[@class='input__container']//label[text()='Email']/following-sibling::input[@name='name']"); // поле Email
    private By editPassword = By.xpath("//div[@class='input__container']//label[text()='Пароль']/following-sibling::input[@type='password']"); // поле Email
        //Кнопки
    private By buttonRegister = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_medium__3zxIa']"); // кнопка Зарегистироваться
        //Ссылки
    private By linkLogin = By.className("Auth_link__1fOlj"); // ссылка Войти
        //Сообщениея
    private By labelErrorPassword = By.xpath("//p[@class='input__error text_type_main-default']"); // сообщение: Некорректный пароль

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void fillFieldRegister(String name, String email, String password){
        driver.findElement(editName).sendKeys(name);
        driver.findElement(editEmail).sendKeys(email);
        driver.findElement(editPassword).sendKeys(password);
    }

    public void clickLinkLogin(){
        driver.findElement(linkLogin).click();
    }

    public void clickButtonRegister(){
        driver.findElement(buttonRegister).click();
    }

    public String getTextErrorRegister(){
        return driver.findElement(labelErrorPassword).getText();
    }
}
