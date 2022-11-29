import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;

public class MainClass {
    public static void main(String[] args) {
        Options.Driver();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        driver.get("https://joyreactor.cc/");
        driver.findElement(By.xpath(XPath.searchText("Лучшее"))).click();

// TODO: 27.11.2022  Найти на странице postContainer с наивысшим post-rating
//https://zennolab.com/discussion/threads/xpath-vzjat-naibolshee-chislo.86463/
//span[@class='post_rating']//span[number(text()>10)]

        WebElement postContainer = driver.findElement(By.xpath(XPath.search));
        WebElement postRating = driver.findElement(By.xpath(XPath.search));


// TODO: 27.11.2022  Сохронить картинку в указанную деррикторию


        driver.findElement(By.xpath(XPath.searchText("Мобильная/темная версия"))).click();
        WebElement search = driver.findElement(By.xpath(XPath.search));
        search.sendKeys("котэ");
        search.sendKeys(Keys.ENTER);

// TODO: 27.11.2022  Дождаться появления на странице тега "котэ"
// TODO: 27.11.2022  Случайному посту поставить "смайлик"
// TODO: 29.11.2022 убедиться, что открылось окно авторизации

//        WebElement tag = driver.findElements(By.xpath("//a[text()='котэ']"));

        driver.quit();
    }
}
