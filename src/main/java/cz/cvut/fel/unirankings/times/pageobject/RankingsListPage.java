package cz.cvut.fel.unirankings.times.pageobject;

import org.openqa.selenium.WebDriver;

/** Page object model for the page with the list of rankings. */
public class RankingsListPage {

  static final String THE_DOMAIN = "https://www.timeshighereducation.com";
  static final String RANKING_LIST_PATH =
      "/world-university-rankings/%s/world-ranking#!/page/0/length/-1/sort_by/rank/sort_order/asc/cols/scores";

  /** Instance of Selenium web driver. */
  private final WebDriver driver;

  /** Target page URL. */
  private final String url;

  public RankingsListPage(WebDriver driver, String year) {
    url = THE_DOMAIN + String.format(RANKING_LIST_PATH, year);

    this.driver = driver;
  }

  /** Navigates to the target page. */
  public void navigate() {
    driver.navigate().to(url);
  }

  /**
   * Gets pages source as a string.
   *
   * @return page source
   */
  public String getPageSource() {
    return driver.getPageSource();
  }

  /**
   * Get URL of the current page.
   *
   * @return current page URL
   */
  public String getCurrentUrl() {
    return driver.getCurrentUrl();
  }

  /** Quits the driver. */
  public void quit() {
    driver.quit();
  }
}
