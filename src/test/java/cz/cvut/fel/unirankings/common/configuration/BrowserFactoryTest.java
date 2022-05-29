package cz.cvut.fel.unirankings.common.configuration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;

class BrowserFactoryTest {

  @Test
  public void test_create_browser() {
    IBrowserFactory browserFactory = new BrowserFactory();
    WebDriver browser = browserFactory.createBrowser();

    Assertions.assertNotNull(browser);
    browser.quit();
  }
}
