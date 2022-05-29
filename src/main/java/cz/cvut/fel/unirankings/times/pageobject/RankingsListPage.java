package cz.cvut.fel.unirankings.times.pageobject;

import org.openqa.selenium.WebDriver;

public class RankingsListPage {

  static final String THE_DOMAIN = "https://www.timeshighereducation.com";
  static final String RANKING_LIST_PATH =
      "/world-university-rankings/%s/world-ranking#!/page/0/length/-1/sort_by/rank/sort_order/asc/cols/scores";

  private final WebDriver driver;

  private final String url;

  public RankingsListPage(WebDriver driver, String year) {
    url = THE_DOMAIN + String.format(RANKING_LIST_PATH, year);

    this.driver = driver;
  }

  public void navigate() {
    driver.navigate().to(url);
  }

  public String getPageSource() {
    return driver.getPageSource();
  }

  public String getCurrentUrl() {
    return driver.getCurrentUrl();
  }

  public void quit() {
    driver.quit();
  }
}
