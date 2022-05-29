package cz.cvut.fel.unirankings.common.configuration;

import org.openqa.selenium.WebDriver;

public interface IBrowserFactory {

  WebDriver createBrowser();
}
