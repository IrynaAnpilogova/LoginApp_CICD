package org;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.net.URL;


public class BaseTest {


    protected WebDriver driver; //declare the variable(interface)

    @BeforeAll
    static void oneTimeSetUp() {
        WebDriverManager.chromedriver().setup();//verifies the version of the browser(eg chrome, firefox, Edge etc.)
        // installed in your local machine by its own
        // uses the latest version of the driver
    }

    @BeforeEach
    void setUpBase() throws Exception {

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");

        // Set any desired options for the Chrome browser
        // For example, to run Chrome in headless mode:

        options.addArguments("--headless");

       // Create a new instance of the RemoteWebDriver
        driver = new RemoteWebDriver(new URL("http://my-selenium:4444/wd/hub"), options);
//        driver = new ChromeDriver(options);


    }


    @AfterEach
    void tearDown() {
        driver.quit();
    }
}
