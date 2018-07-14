package amazone2e;

import cucumber.deps.com.thoughtworks.xstream.mapper.Mapper.Null;

public class Utils {
    public static String baseUrl(String region, String page) {
        String baseUrl = "http://www.amazon";

        switch(region) {
            case "UK":
                baseUrl = baseUrl + ".co.uk";
                break;
            default:
                baseUrl = baseUrl + ".com";
        }

        switch(page) {
            case "home":
                baseUrl = baseUrl + "/";
                break;
            default:
                baseUrl = baseUrl + "/";
        }

        return baseUrl;
    }

    public static Boolean isMobile() {
        try {
            return System.getProperty("BREAKPOINT").equals("mobile");
        } catch (NullPointerException e) {
            return false;
        }
    }
}
