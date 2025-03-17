import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.HomePage;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class SectionConstructorTest extends SeleniumBase {

    @Test
    @Epic(value = "Переходы между начинками")
    @Feature(value = "Переход Булки -> Соусы")
    @DisplayName("Переходы между начинками")
    public void transitionBunSauceTest() {
        HomePage homePage = new HomePage(driver);
        String expectedResult = "Соусы";

        WebElement scrollElement = driver.findElement(By.className("BurgerIngredients_ingredients__menuContainer__Xu3Mo"));
        Long initialScrollTop = (Long) ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollTop;", scrollElement);

        homePage.clickLinkSauce();
        // Ожидание завершения прокрутки
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until((ExpectedCondition<Boolean>) d -> {
            Long newScrollTop1 = (Long) ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollTop;", scrollElement);
            return newScrollTop1 > initialScrollTop; // Ждем, пока значение scrollTop увеличится
        });

        assertEquals(expectedResult, firstVisibleIngredient());
    }

    @Test
    @Epic(value = "Переходы между начинками")
    @Feature(value = "Переход Соус -> Начинки")
    @DisplayName("Переходы между начинками")
    public void transitionSauceMainTest() {
        HomePage homePage = new HomePage(driver);
        String expectedResult = "Начинки";
        WebElement scrollElement = driver.findElement(By.className("BurgerIngredients_ingredients__menuContainer__Xu3Mo"));
        Long initialScrollTop = (Long) ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollTop;", scrollElement);

        homePage.clickLinkSauce();
        homePage.clickLinkMain();

        // Ожидание завершения прокрутки
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until((ExpectedCondition<Boolean>) d -> {
            Long newScrollTop1 = (Long) ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollTop;", scrollElement);
            return newScrollTop1 > initialScrollTop; // Ждем, пока значение scrollTop увеличится
        });

        assertEquals(expectedResult, firstVisibleIngredient());
    }

    @Test
    @Epic(value = "Переходы между начинками")
    @Feature(value = "Переход Соусы -> Начинки")
    @DisplayName("Переходы между начинками")
    public void transitionBunMainTest() {
        HomePage homePage = new HomePage(driver);
        String expectedResult = "Начинки";
        WebElement scrollElement = driver.findElement(By.className("BurgerIngredients_ingredients__menuContainer__Xu3Mo"));
        Long initialScrollTop = (Long) ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollTop;", scrollElement);
        homePage.clickLinkMain();
        // Ожидание завершения прокрутки
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until((ExpectedCondition<Boolean>) d -> {
            Long newScrollTop1 = (Long) ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollTop;", scrollElement);
            return newScrollTop1 > initialScrollTop; // Ждем, пока значение scrollTop увеличится
        });
        assertEquals(expectedResult, firstVisibleIngredient());
    }

    @Test
    @Epic(value = "Переходы между начинками")
    @Feature(value = "Переход Соусы -> Булки")
    @DisplayName("Переходы между начинками")
    public void transitionSauceBunTest() {
        HomePage homePage = new HomePage(driver);
        String expectedResult = "Булки";
        WebElement scrollElement = driver.findElement(By.className("BurgerIngredients_ingredients__menuContainer__Xu3Mo"));
        Long initialScrollTop = (Long) ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollTop;", scrollElement);
        homePage.clickLinkSauce();
        homePage.clickLinkBun();
        // Ожидание завершения прокрутки
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until((ExpectedCondition<Boolean>) d -> {
            Long newScrollTop1 = (Long) ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollTop;", scrollElement);
            return newScrollTop1 > initialScrollTop; // Ждем, пока значение scrollTop увеличится
        });
        assertEquals(expectedResult, firstVisibleIngredient());
    }

    @Test
    @Epic(value = "Переходы между начинками")
    @Feature(value = "Переход Начинки -> Булки")
    @DisplayName("Переходы между начинками")
    public void transitionMainBunTest() {
        HomePage homePage = new HomePage(driver);
        String expectedResult = "Булки";
        WebElement scrollElement = driver.findElement(By.className("BurgerIngredients_ingredients__menuContainer__Xu3Mo"));
        Long initialScrollTop = (Long) ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollTop;", scrollElement);
        homePage.clickLinkMain();
        homePage.clickLinkBun();
        // Ожидание завершения прокрутки
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until((ExpectedCondition<Boolean>) d -> {
            Long newScrollTop1 = (Long) ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollTop;", scrollElement);
            return newScrollTop1 > initialScrollTop; // Ждем, пока значение scrollTop увеличится
        });
        assertEquals(expectedResult, firstVisibleIngredient());
    }

    @Test
    @Epic(value = "Переходы между начинками")
    @Feature(value = "Переход Начинки -> Соус")
    @DisplayName("Переходы между начинками")
    public void transitionMainSauceTest() {
        HomePage homePage = new HomePage(driver);
        String expectedResult = "Соусы";
        WebElement scrollElement = driver.findElement(By.className("BurgerIngredients_ingredients__menuContainer__Xu3Mo"));
        Long initialScrollTop = (Long) ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollTop;", scrollElement);
        homePage.clickLinkMain();
        homePage.clickLinkSauce();
        // Ожидание завершения прокрутки
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until((ExpectedCondition<Boolean>) d -> {
            Long newScrollTop1 = (Long) ((JavascriptExecutor) driver).executeScript("return arguments[0].scrollTop;", scrollElement);
            return newScrollTop1 > initialScrollTop; // Ждем, пока значение scrollTop увеличится
        });
        assertEquals(expectedResult, firstVisibleIngredient());
    }

    public String firstVisibleIngredient() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Список заголовков
        String[] sections = {"Булки", "Соусы", "Начинки"};
        WebElement firstVisibleSection = null;
        for (String section : sections) {
            try {
                // Ищем заголовок секции
                WebElement sectionHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='" + section + "']")));
                // Проверяем, виден ли заголовок с помощью JavaScript
                if (isElementVisible(sectionHeader, driver)) {
                    firstVisibleSection = sectionHeader;
                    break; // Выход из цикла, если найден первый видимый элемент
                }
            } catch (Exception e) {
                // Игнорируем исключения, если элемент не найден
            }
        }

        if (firstVisibleSection != null) {
            System.out.println("Первый видимый элемент: " + firstVisibleSection.getText());
            return firstVisibleSection.getText();
        } else {
            System.out.println("Нет видимых элементов в секциях.");
            return "not";
        }
    }

    // Метод для проверки видимости элемента
    public boolean isElementVisible(WebElement element, WebDriver driver) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return (Boolean) js.executeScript(
                "var elem = arguments[0],                 " +
                        "    box = elem.getBoundingClientRect(),  " +
                        "    cx = box.left + box.width / 2,       " +
                        "    cy = box.top + box.height / 2,       " +
                        "    e = document.elementFromPoint(cx, cy); " +
                        "for (; e; e = e.parentElement) {           " +
                        "    if (e === elem) return true;          " +
                        "}                                         " +
                        "return false;                              ", element);
    }

}
