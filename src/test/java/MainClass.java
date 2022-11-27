import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainClass {
    public static void main(String[] args) {
        Options.Driver();
        WebDriver driver = new ChromeDriver();

        driver.get("https://joyreactor.cc/");
        driver.findElement(By.xpath(XPath.searchText("Мобильная/темная версия"))).click();

        WebElement search = driver.findElement(By.xpath(XPath.search));
                search.sendKeys("котэ");
                search.sendKeys(Keys.ENTER);

// TODO: 27.11.2022  Дождаться появления на странице тега "котэ"
// TODO: 27.11.2022  Найти на странице картинку с post-rating > 25
// TODO: 27.11.2022  Найти на странице картинку с наивысшим post-rating
// TODO: 27.11.2022  Сохронить картинку в указанную деррикторию

        driver.quit();
    }
}
