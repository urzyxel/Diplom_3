package site.nomoreparties.stellarburgers;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RestorePage {
    private WebDriver driver;

        //Ссылки
    private By linkLogin = By.className("Auth_link__1fOlj"); // ссылка Войти

    public RestorePage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLinkLogin(){
        driver.findElement(linkLogin).click();
    }
}
