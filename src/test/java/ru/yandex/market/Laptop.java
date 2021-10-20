package ru.yandex.market;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Laptop {
    WebDriverWait wait;

    @Test
    public void test() {
        // Для Windows
        //System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        // Для Linux и Mac
        //System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver");
        //WebDriver driver = new ChromeDriver();
        //Edge
        System.setProperty("webdriver.edge.driver", "src/test/resources/drivers/msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

        wait = new WebDriverWait(driver, 100, 2000);
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        
        driver.get("https://market.yandex.ru/");


        WebElement search = driver.findElement(By.xpath("//input[@type='text' and @id='header-search']"));
        Assert.assertTrue("Страница маркета не загрузилась", search.isDisplayed());
        fillInputField(search, "Ноутбуки");

        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit' and @data-r='search-button']"));
        waitUtilElementToBeClickable(searchButton);
        searchButton.click();

        WebElement hp = driver.findElement(By.xpath("//div[@class='_1exhf']"));
        Assert.assertTrue("Страница с HP не загрузилась", hp.isDisplayed());
        waitUtilElementToBeClickable(hp);
        hp.click();

        WebElement dprice = driver.findElement(By.xpath("//button[@data-autotest-id='dprice']"));
        Assert.assertTrue("Страница dprice не загрузилась", hp.isDisplayed());
        waitUtilElementToBeClickable(dprice);
        dprice.click();

        WebElement aprice = driver.findElement(By.xpath("//button[@data-autotest-id='aprice']"));
        Assert.assertTrue("Страница aprice не загрузилась", hp.isDisplayed());
        waitUtilElementToBeClickable(aprice);
        aprice.click();

        List<WebElement> laptops = driver.findElements(By.xpath("//h3[@data-zone-name='title']"));

        for (int i = 0; i < 10; i++) {
            System.out.println(laptops.get(i).getText());
        }

        /*
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        */

        driver.quit();
    }



    /**
     * Явное ожидание того, что элемент станет кликабельным
     *
     * @param element - веб-элемент
     */

    private void waitUtilElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }


    /**
     * Заполнение поля опредленным значением
     *
     * @param element - веб-элемент
     * @param value   - значение
     */

    private void fillInputField(WebElement element, String value) {
        waitUtilElementToBeClickable(element);
        element.click();
        element.clear();
        element.sendKeys(value);
        boolean checkFlag = wait.until(ExpectedConditions.attributeContains(element, "value", value));
        Assert.assertTrue("Поле было заполнено некорректно", checkFlag);

    }


}