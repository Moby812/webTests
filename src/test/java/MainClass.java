import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainClass {
    public static void main(String[] args) {
        Options.Driver();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().minimize();
//        driver.manage().window().maximize();

        driver.get(Options.site);
        WebDriverWait wait = (new WebDriverWait(driver, Duration.ofSeconds(15)));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//a[@href='/random']")));
        System.out.printf("Cайт: %s загрузился \n", Options.site);

        List<WebElement> enterSite = driver.findElements(By.xpath(XPath.enterSite));
        if (enterSite.size() == 0) System.out.println("Вход на сайт уже был ранее выполнен");
        else {
            System.out.printf("Выполняется вход на сайт под УЗ: %s \n", Options.login);
            enterSite.get(0).click();
            driver.findElement(By.xpath(XPath.login)).sendKeys(Options.login);
            driver.findElement(By.xpath(XPath.pass)).sendKeys(Options.pass);
            driver.findElement(By.xpath(XPath.enterSite2)).click();
            System.out.println("Вход выполнен успешно");
        }

        driver.findElement(By.xpath("//a[starts-with(text(),'Лучшее')]")).click();

        List<WebElement> postRating = driver.findElements(By.xpath("//span[number(text()>25)]"));

        // TODO: 02.12.2022  попробовать реализовать это через мапу
        double rate = Options.sortRateList(postRating);
        System.out.println("Максимальный рейтинг поста = " + rate);

        String postContainer = driver.findElement(By.xpath("//span[number(text()>="+rate+")]/ancestor::div[@class='postContainer']")).getAttribute("id");
        WebElement img = driver.findElement(By.xpath("//span[number(text()>="+rate+")]/ancestor::div[@class='postContainer']//div[@class='image']//img"));
        System.out.println("Название контейнера поста: "+postContainer);
        System.out.println("Линк на картинку поста: " +img.getAttribute("src"));

// TODO: 27.11.2022  Сохронить картинку в указанную деррикторию

        System.out.printf("Файл '%s' был сохранён в деррикторию: %s \n", "pic.jpeg", Options.dir);


        driver.findElement(By.xpath(XPath.searchText("Мобильная/темная версия"))).click();
// TODO: 29.11.2022 Отключить SFW контент (через JS)
        WebElement search = driver.findElement(By.xpath(XPath.search));
        search.sendKeys(Options.tag);
        search.sendKeys(Keys.ENTER);
        System.out.printf("Был выполнен поиск по тегу '%s'\n", Options.tag);

// TODO: 27.11.2022  Дождаться появления на странице тега "котэ"
// TODO: 27.11.2022  Случайному посту поставить "смайлик"
// TODO: 29.11.2022 убедиться, что рейтинг увеличился
// TODO: 01.12.2022 Сделать скрин страницы

//        WebElement tag = driver.findElements(By.xpath("//a[text()='котэ']"));

        driver.quit();
    }
}
