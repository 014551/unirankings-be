package cz.cvut.fel.unirankings.topuniversities.pageobjects;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/** Page object model for the page with the list of rankings. */
public class RankingsListPage {

  static final String TOP_UNIVERSITIES_DOMAIN = "https://www.topuniversities.com";
  static final String RANKING_LIST_PATH = "/university-rankings/world-university-rankings/%d";

  /** Javascript script used to click on the button provided as the argument. */
  static final String JS_CLICK_SCRIPT = "arguments[0].click();";

  static final String SORT_BY_DROPDOWN_CLASS_NAME = "sort_by_dropdown";

  /** Web driver timeout. */
  private static final int TIMEOUT_IN_SECONDS = 10;

  /** Web driver polling interval. */
  private static final int POLLING_INTERVAL_IN_SECONDS = 3;

  /** Years that have to be treated differently in the URL path. */
  private static final List<String> INCORRECT_URL_PATH_YEARS =
      Arrays.asList("2014", "2015", "2016", "2017");

  /** Instance of Selenium web driver. */
  private final WebDriver driver;

  /** Explicit waits for selenium operations. */
  private final Wait<WebDriver> wait;

  /** Target page URL. */
  private final String url;

  public RankingsListPage(WebDriver driver, String year) {

    int urlYear = Integer.parseInt(year);
    if (INCORRECT_URL_PATH_YEARS.contains(year)) {
      urlYear = urlYear - 1;
    }

    url = TOP_UNIVERSITIES_DOMAIN + String.format(RANKING_LIST_PATH, urlYear);

    this.driver = driver;
    wait =
        new FluentWait<>(driver)
            .withTimeout(Duration.ofSeconds(TIMEOUT_IN_SECONDS))
            .pollingEvery(Duration.ofSeconds(POLLING_INTERVAL_IN_SECONDS))
            .ignoring(NoSuchElementException.class);
  }

  /** Navigates to the target page. */
  public void navigate() {
    driver.navigate().to(url);
  }

  /** Removes sliding popup about cookies. */
  public void removeSlidingPopup() {
    WebElement slidingPopup;
    By id = By.id("sliding-popup");
    try {
      slidingPopup = wait.until(driver -> driver.findElement(id));
    } catch (TimeoutException e) {
      throw new NoSuchElementException(
          String.format("Sliding popup was not found by id = %s", id), e);
    }
    JavascriptExecutor js = (JavascriptExecutor) driver;
    js.executeScript(
        "var element = arguments[0];" + "element.parentNode.removeChild(element);", slidingPopup);
  }

  /**
   * Expands number of results on the page to the specified amount.
   *
   * @param numOfResults number of results to show
   */
  public void expandResults(int numOfResults) {
    WebElement sortDropdown = driver.findElement(By.className(SORT_BY_DROPDOWN_CLASS_NAME));
    sortDropdown.click();

    WebElement dropDownVal;
    try {
      dropDownVal =
          wait.until(
              driver ->
                  driver.findElement(
                      By.xpath(
                          String.format("//div[@class='item'][@data-value='%d']", numOfResults))));

    } catch (TimeoutException ex) {
      throw new NoSuchElementException(
          String.format(
              "Dropdown value for the given number of results = %d was not found.", numOfResults),
          ex);
    }
    dropDownVal.click();
  }

  /** Switches to ranking indicators tab. */
  public void switchToRankingIndicatorsTab() {
    WebElement rankingIndicatorsTabButton;
    By xpath = By.xpath("//a[@href='#university-rankings-indicators']");
    try {
      rankingIndicatorsTabButton = wait.until(driver -> driver.findElement(xpath));

    } catch (TimeoutException ex) {
      throw new NoSuchElementException(
          String.format("Ranking indicators tab button was not found by xpath = %s.", xpath), ex);
    }
    JavascriptExecutor ex = (JavascriptExecutor) driver;
    ex.executeScript(JS_CLICK_SCRIPT, rankingIndicatorsTabButton);
  }

  /** Waits until ranking indicators' table is loaded. */
  public void waitUntilRankingIndicatorsTableIsLoaded() {
    By xpath =
        By.xpath(
            "//div[@id='ranking-data-load_ind']/div[contains(@class, 'row') and contains(@class, 'ind-row')]");
    try {
      wait.until(driver -> driver.findElement(xpath));
    } catch (TimeoutException ex) {
      throw new NoSuchElementException(
          String.format("Ranking indicators table was not found by xpath = %s", xpath), ex);
    }
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
   * Checks if the next page is present.
   *
   * @return true if present
   */
  public boolean isNextPagePresent() {
    return driver.findElements(By.xpath("//a[@class='page-link next']")).size() > 0;
  }

  /** Clicks to load the next page with results. */
  public void goToNextPage() {
    if (isNextPagePresent()) {
      WebElement nextPageButton = driver.findElement(By.xpath("//a[@class='page-link next']"));
      JavascriptExecutor ex = (JavascriptExecutor) driver;
      ex.executeScript(JS_CLICK_SCRIPT, nextPageButton);
    }
  }

  /** Quits the driver. */
  public void quit() {
    driver.quit();
  }
}
