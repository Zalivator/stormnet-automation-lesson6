package org.example;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumApiTest {

    WebDriver driver;

    @BeforeAll
    static void loadDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupDriver() {
        driver = new ChromeDriver();
        driver.get("https://demo.guru99.com/test/guru99home/");
    }

    @AfterEach
    void closeBrowser() {
        Set<String> set = driver.getWindowHandles();
        for (String id : set) {
            driver.switchTo().window(id);
            driver.close();
        }
    }

    @Test
    void navigateToTest() {
        driver.get("https://google.com");
        driver.get("https://selenide.org");
        System.out.println("t");
    }

    @Test
    void findElementTest() {
        By securityLocator = By.xpath("//a[@href='http://demo.guru99.com/Security/SEC_V1/index.php']");
        WebElement element = driver.findElement(securityLocator);
        System.out.println(element.getText());
    }

    @Test
    void jsTest() {
        WebElement element = (WebElement) ((JavascriptExecutor) driver)
                    .executeScript("return document.querySelector('#philadelphia-field-email')");
        System.out.println(element.getAttribute("placeholder"));
    }

    @Test
    void findListElementsTest() {
        List<WebElement> list = driver.findElements(By.xpath("//li[contains(@class, 'parent')]"));
        long count = list.stream()
                    .filter(i -> i.getText().equals("Testing"))
                    .count();
        System.out.println(count);
    }

    @Test
    void windowTest() {
        driver.get("https://demo.guru99.com/test/guru99home/");
        System.out.println(driver.getTitle());
        String firstId = driver.getWindowHandle();
        driver.switchTo().newWindow(WindowType.WINDOW).get("https://google.com");
        System.out.println(driver.getTitle());
        driver.switchTo().window(firstId);
        System.out.println(driver.getTitle());
    }

    @Test
    void iFrameTest() {
        driver.switchTo().frame("a077aa5e");
        By jmeter = By.xpath("//img[@src='Jmeter720.png']");
        driver.findElement(jmeter);
        driver.switchTo().defaultContent();
        By securityLocator = By.xpath("//a[@href='http://demo.guru99.com/Security/SEC_V1/index.php']");
        driver.findElement(securityLocator);
    }

    @Test
    void navigationBackAndTo() {
        System.out.println("y");
    }

    @Test
    void cookieTest() {
        Cookie cookie = new Cookie("password", "123", "");
        driver.manage().addCookie(cookie);
        System.out.println("t");
    }

    @Test
    void smartWaits() {
        driver.get("https://blocsapp.com/");

        By locator = By.xpath("//button[@aria-label='Scroll to top button']");

        new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.elementToBeClickable(locator));

        System.out.println("t");

    }

    @Test
    void webElementActionsTest() {
        By emailLocator = By.xpath("//input[@placeholder='Enter Email']");
        WebElement element = driver.findElement(emailLocator);
        element.sendKeys("testing");
        element.clear();
        System.out.println(element.getLocation());
        System.out.println(element.getSize());
        System.out.println(element.isDisplayed());
        System.out.println(":t");
    }

}
