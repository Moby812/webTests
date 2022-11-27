import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class MainClass {
    public static void main(String[] args) {
        Options.Driver();
        WebDriver driver = new ChromeDriver();

        driver.get("https://joyreactor.cc/");

// TODO: 27.11.2022  "Мобильная/темная версия"
// TODO: 27.11.2022  "котэ"
// TODO: 27.11.2022  Найти на странице картинку с рейтингом поста > 25
// TODO: 27.11.2022  Сохронить картинку в указанную деррикторию

        driver.quit();
    }
}
