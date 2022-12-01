public class XPath {

    static String search = "//*[@class = 'search']//input[@placeholder='...']";
    static String enterSite = "//a[@href='/login']";
    static String enterSite2 = "//input[@value='Войти']";
    static String login = "//input[@id='signin_username']";
    static String pass = "//input[@id='signin_password' and @type='password']";

    public static String searchText(String text) {
        return "//a[text()='"+ text +"']";
    }
    public static String searchConteinsText(String text) {
        return "//a[contains(text(),'"+ text +"']";
    }
}
