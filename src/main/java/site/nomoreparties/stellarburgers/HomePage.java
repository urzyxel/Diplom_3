package site.nomoreparties.stellarburgers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;
        //Ссылки
    private By linkBun = By.xpath("//span[contains(text(), 'Булки')]"); // ссылка Булки
    private By linkSauce = By.xpath("//span[contains(text(), 'Соусы')]"); // ссылка Соусы
    private By linkMain = By.xpath("//span[contains(text(), 'Начинки')]"); // ссылка Начинки
        //Кнопки
    private By buttonPersonalAccount = By.xpath("//p[@class='AppHeader_header__linkText__3q_va ml-2' and text()='Личный Кабинет']"); // ссылка Личный кабинет
    private By buttonLoginOrCreateOrder = By.xpath("//button[@class='button_button__33qZ0 button_button_type_primary__1O7Bx button_button_size_large__G21Vg']"); // кнопка Войти в аккаунт или Оформить заказ

    private By labelAssembleBurger = By.cssSelector("h1.text.text_type_main-large.mb-5.mt-10"); // надпись Соберите Бургер
    private By labelEntrance = By.xpath("//div[@class='Auth_login__3hAey']/h2"); // надпись Вход

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLinkPersonalAccount(){
        driver.findElement(buttonPersonalAccount).click();
    }

    public void clickButtonLoginAccount(){
        driver.findElement(buttonLoginOrCreateOrder).click();
    }

    public String getTextLabelEntranceOrOrderCreate(){
        return driver.findElement(buttonLoginOrCreateOrder).getText();
    }

    public String getTextLabelAssembleBurger(){
        return driver.findElement(labelAssembleBurger).getText();
    }

    public String getTextLabelEntrance(){
        return driver.findElement(labelEntrance).getText();
    }

    public void clickLinkBun(){
        driver.findElement(linkBun).click();
    }

    public void clickLinkSauce(){
        driver.findElement(linkSauce).click();
    }

    public void clickLinkMain(){
        driver.findElement(linkMain).click();
    }
}
