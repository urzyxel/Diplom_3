package site.nomoreparties.stellarburgers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalAccountPage {
    private WebDriver driver;
        //Поля
    private By editName = By.xpath("(//input[@class='text input__textfield text_type_main-default input__textfield-disabled'])[1]"); // поле Имя
    private By editLogin = By.xpath("(//input[@class='text input__textfield text_type_main-default input__textfield-disabled'])[2]"); // поле Логин
        //Тексты
    private By labelInfo = By.cssSelector("p.Account_text__fZAIn"); // Текст описания в личном кабинете
        //Кнопки
    private By buttonConstructor = By.cssSelector("p.AppHeader_header__linkText__3q_va.ml-2"); // кнопка Констурктор
        //Ссылки
    private By linkLogo = By.cssSelector("p.AppHeader_header__linkText__3q_va.ml-2"); // лого Stellar Burgers
    private By linkLogout = By.cssSelector(".Account_button__14Yp3.text.text_type_main-medium.text_color_inactive"); // ссылка Выйти

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTextLabelInfo(){
        return driver.findElement(labelInfo).getText();
    }

    public void clickButtonConstructor(){
        driver.findElement(buttonConstructor).click();
    }

    public void clickLinkLogo(){
        driver.findElement(linkLogo).click();
    }

    public void clickLinkLogout(){
        driver.findElement(linkLogout).click();
    }
}
