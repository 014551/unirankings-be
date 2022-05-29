package cz.cvut.fel.unirankings.common.configuration;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

@Service
public class BrowserFactory implements IBrowserFactory {

  @Override
  public WebDriver createBrowser() {
//    System.setProperty("webdriver.chrome.whitelistedIps", "");
//    System.setProperty("webdriver.chrome.verboseLogging", "true");
    WebDriverManager.chromedriver().setup();
    ChromeOptions chromeOptions = new ChromeOptions().setHeadless(true);
//    chromeOptions.addArguments("--whitelisted-ips=''");
//    chromeOptions.addArguments("--verbose");
    chromeOptions.addArguments("--no-sandbox");
    return new ChromeDriver(chromeOptions);
  }
}
