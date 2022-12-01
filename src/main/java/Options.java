public class Options {

    static String site = "https://joyreactor.cc/";
    static String login = "NoGifs";
    static String pass = "NoGifs";
    static String tag = "котэ";

    public static void Driver() {
        System.setProperty("webdriver.chrome.driver", ".//drivers/chromedriver.exe");
    }

    // TODO: 27.11.2022 дерректория куда сохранять файл
    static String dir = ".//pic/";
}