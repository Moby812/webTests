import org.apache.commons.io.FileUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

import org.apache.http.impl.client.CloseableHttpClient;
import static org.apache.commons.io.FileUtils.copyInputStreamToFile;

public class MainClass {
    public static void main(String[] args) throws IOException {
        Options.Driver();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.manage().window().minimize();

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
// TODO: 02.12.2022 Проверить через jUnit, что вход на сайт выполнен успешно

        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath.best)));
        driver.findElement(By.xpath(XPath.best)).click();

        List<WebElement> postRating = driver.findElements(By.xpath("//span[number(text()>25)]"));

        double rate = Options.sortRateList(postRating);
        System.out.println("Максимальный рейтинг поста = " + rate);

        String postContainer = driver.findElement(By.xpath("//span[number(text()>="+rate+")]/ancestor::div[@class='postContainer']")).getAttribute("id");
        String img = driver.findElement(By.xpath("//div[@id='"+postContainer+"']//div[@class='image']//img")).getAttribute("src");
        System.out.println("Название контейнера поста: "+postContainer);
        System.out.println("Линк на картинку поста: " +img);

// TODO:
//        https://habr.com/ru/post/497922/
//        https://stackoverflow.com/questions/6813704/how-to-download-an-image-using-selenium-any-version

        //реализация через org.apache.http client
        File fileToSave = new File(Options.dir + postContainer + "_"+ rate + ".jpeg");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(img);
        HttpResponse response = httpClient.execute(httpGet, new BasicHttpContext());
        copyInputStreamToFile(response.getEntity().getContent(), fileToSave);

        System.out.printf("Файл '%s' был сохранён в деррикторию: %s \n", postContainer + "_"+ rate + ".jpeg", Options.dir);
// TODO: 02.12.2022 Проверить через jUnit, что файл сохранился

        driver.findElement(By.xpath(XPath.searchText("Мобильная/темная версия"))).click();
        System.out.println("Выполнен переход на Мобильную версию сайта");
// TODO: 29.11.2022 Отключить SFW контент (через JS)

        WebElement search = driver.findElement(By.xpath(XPath.search));
        search.sendKeys(Options.tag);
        search.sendKeys(Keys.ENTER);
        System.out.printf("Был выполнен поиск по тегу '%s'\n", Options.tag);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(XPath.searchText(Options.tag))));

        //случайному посту ставится лайк
        List<WebElement> catsPosts = driver.findElements(By.xpath("//button[@class='vote-button vote-plus']"));
        int randomNum = ThreadLocalRandom.current().nextInt(0, catsPosts.size()-1);
        int randomPost = randomNum + 1;
        System.out.println("Случайный пост для лайка: " + randomPost);
        catsPosts.get(randomNum).click();
// TODO: 29.11.2022 убедиться, что рейтинг увеличился

        //делается скрин страницы
        Date dateNow = new Date();
        SimpleDateFormat format = new SimpleDateFormat("hhmm");
        String scrName = "screenshot_"+ format.format(dateNow)+ ".png";

//        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//img[contains(@src,'.jpeg')]")));
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot,new File(Options.dir+scrName));
        System.out.printf("Скриншот '%s' был сохранён в деррикторию: '%s'\n",scrName,Options.dir);

        driver.quit();
    }
}
