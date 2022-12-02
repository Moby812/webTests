import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Options {

    static String site = "https://joyreactor.cc/";
    static String login = "NoGifs";
    static String pass = "NoGifs";
    static String user = "Moby8";
    static String tag = "котэ";

    public static void Driver() {
        System.setProperty("webdriver.chrome.driver", ".//drivers/chromedriver.exe");
    }

    static String dir = "C:/Users/"+user+"/Pictures/pic/";

    public static Double sortRateList(List<WebElement> b){
        List<Double> rate = new ArrayList<>();
        for (int i = 0; i < b.size()-1; i++) {
            rate.add(Double.parseDouble(b.get(i).getText()));
        }
        Collections.sort(rate);
        Collections.reverse(rate);
        return rate.get(0);
    }
}