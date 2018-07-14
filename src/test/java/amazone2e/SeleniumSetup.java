package amazone2e;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import amazone2e.Utils;

public class SeleniumSetup {
    public static WebDriver setup() {
        String chromedriver;
        try {
            chromedriver = System.getenv("OS").equals("Windows_NT")
                ? "libs/chromedriver_2.39_win32.exe"
                : "libs/chromedriver_2.39_mac64";
        } catch (NullPointerException error) {
            System.out.println("Defaulting to Mac OSX chromedriver");
            chromedriver = "libs/chromedriver_2.39_mac64";
        }
        System.setProperty("webdriver.chrome.driver", chromedriver);

        List<String> startArgs = new ArrayList<>();
        startArgs.add(0, "--disable-infobars");
        startArgs.add(0, "--no-sandbox");
        ChromeOptions chromeOpts = new ChromeOptions().addArguments(startArgs);

        if (Utils.isMobile()) {
            System.out.println("Starting tests in mobile viewport.");
            Map<String, String> mobileEmulation = new HashMap<>();
            mobileEmulation.put("deviceName", "Nexus 5");
            chromeOpts.setExperimentalOption("mobileEmulation", mobileEmulation);
        }

        return new ChromeDriver(chromeOpts);
    }

    public static void teardown(WebDriver browser) {
        browser.close();
    }
}
