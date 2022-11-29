public class XPath {

    static String search = "//*[@class = 'search']//input[@placeholder='...']";

    public static String searchText(String text) {
        return "//a[text()='"+ text +"']";
    }
}
