import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

public abstract class SeleniumBase {
    protected WebDriver driver;

    @Before
    public void setUp() {
        driver = getWebDriver("YANDEX");
        Assert.assertNotNull("Ошибка инициализации WebDriver", driver);
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    public static WebDriver getWebDriver(String browserName) {
        WebDriver webDriver;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("start-maximized");

        switch (browserName) {
            case "CHROME":
                webDriver = new ChromeDriver(options);
                break;
            case "YANDEX":
                System.setProperty("webdriver.chrome.driver", "C:/yadriver/yandexdriver.exe");
                options.setBinary("C:/Users/rudenko/AppData/Local/Yandex/YandexBrowser/Application/browser.exe");
                webDriver = new ChromeDriver(options);
                break;
            default:
                throw new IllegalArgumentException("Неизвестный браузер: " + browserName);
        }
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        return webDriver;
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
