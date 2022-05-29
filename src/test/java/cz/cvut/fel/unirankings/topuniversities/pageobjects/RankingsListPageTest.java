package cz.cvut.fel.unirankings.topuniversities.pageobjects;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

import static cz.cvut.fel.unirankings.topuniversities.pageobjects.RankingsListPage.SORT_BY_DROPDOWN_CLASS_NAME;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RankingsListPageTest {

  private static final String YEAR = "2022";

  @Mock(extraInterfaces = {JavascriptExecutor.class})
  private WebDriver webDriver;

  private AutoCloseable closeable;

  private RankingsListPage page;

  @BeforeEach
  public void setup() {
    closeable = MockitoAnnotations.openMocks(this);
    page = new RankingsListPage(webDriver, YEAR);
  }

  @AfterEach
  public void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  public void test_navigate() {
    WebDriver.Navigation navigation = Mockito.mock(WebDriver.Navigation.class);
    Mockito.when(webDriver.navigate()).thenReturn(navigation);

    page.navigate();

    Mockito.verify(webDriver).navigate();
    Mockito.verify(navigation)
        .to(
            RankingsListPage.TOP_UNIVERSITIES_DOMAIN
                + String.format(RankingsListPage.RANKING_LIST_PATH, Integer.valueOf(YEAR)));
  }

  @Test
  public void test_remove_sliding_popup_positive() {
    WebElement slidingPopupWebElement = mock(WebElement.class);
    when(webDriver.findElement(any(By.ById.class))).thenReturn(slidingPopupWebElement);

    page.removeSlidingPopup();

    verify((JavascriptExecutor) webDriver).executeScript(anyString(), eq(slidingPopupWebElement));
  }

  @Test
  public void test_remove_sliding_popup_negative() {
    when(webDriver.findElement(any(By.ById.class)))
        .thenThrow(new NoSuchElementException("Element was not found."));

    NoSuchElementException ex =
        assertThrows(NoSuchElementException.class, () -> page.removeSlidingPopup());
    assertTrue(ex.getMessage().contains("Sliding popup was not found by id = "));
  }

  @Test
  public void test_expand_results_positive() {
    WebElement sortByDropdownWebElement = mock(WebElement.class);
    when(webDriver.findElement(By.className(SORT_BY_DROPDOWN_CLASS_NAME)))
        .thenReturn(sortByDropdownWebElement);

    WebElement dropDownValueWebElement = mock(WebElement.class);
    when(webDriver.findElement(any(By.ByXPath.class))).thenReturn(dropDownValueWebElement);

    int numOfResults = 100;
    page.expandResults(numOfResults);

    verify(sortByDropdownWebElement).click();
    verify(webDriver, times(2)).findElement(any(By.class));
    // TODO: need to verify, that numOfResults was used in the xpath expression
    verify(dropDownValueWebElement).click();
  }

  @Test
  public void test_expand_results_dropdown_not_found() {
    String expectedExMessage = "Element was not found";
    when(webDriver.findElement(By.className(SORT_BY_DROPDOWN_CLASS_NAME)))
        .thenThrow(new NoSuchElementException(expectedExMessage));

    int numOfResults = 100;
    NoSuchElementException ex =
        assertThrows(NoSuchElementException.class, () -> page.expandResults(numOfResults));
    assertTrue(ex.getMessage().contains(expectedExMessage));
  }

  @Test
  public void test_expand_results_dropdown_value_not_found() {
    WebElement sortByDropdownWebElement = mock(WebElement.class);
    when(webDriver.findElement(By.className(SORT_BY_DROPDOWN_CLASS_NAME)))
        .thenReturn(sortByDropdownWebElement);

    when(webDriver.findElement(any(By.ByXPath.class)))
        .thenThrow(new NoSuchElementException("Element was not found"));

    int numOfResults = 100;
    NoSuchElementException ex =
        assertThrows(NoSuchElementException.class, () -> page.expandResults(numOfResults));
    assertTrue(
        ex.getMessage()
            .contains(
                String.format(
                    "Dropdown value for the given number of results = %d was not found",
                    numOfResults)));

    verify(sortByDropdownWebElement).click();
  }

  @Test
  public void test_switch_to_ranking_indicators_tab_positive() {
    WebElement rankingIndicatorsTabButton = mock(WebElement.class);
    when(webDriver.findElement(any(By.ByXPath.class))).thenReturn(rankingIndicatorsTabButton);

    page.switchToRankingIndicatorsTab();

    verify((JavascriptExecutor) webDriver)
        .executeScript(RankingsListPage.JS_CLICK_SCRIPT, rankingIndicatorsTabButton);
  }

  @Test
  public void test_switch_to_ranking_indicators_tab_button_not_found() {
    when(webDriver.findElement(any(By.ByXPath.class)))
        .thenThrow(new NoSuchElementException("Element was not found"));

    NoSuchElementException ex =
        assertThrows(NoSuchElementException.class, () -> page.switchToRankingIndicatorsTab());
    assertTrue(ex.getMessage().contains("Ranking indicators tab button was not found by xpath = "));
  }

  @Test
  public void test_wait_until_ranking_indicators_table_is_loaded_positive() {
    when(webDriver.findElement(any(By.ByXPath.class))).thenReturn(mock(WebElement.class));

    page.waitUntilRankingIndicatorsTableIsLoaded();
  }

  @Test
  public void test_wait_until_ranking_indicators_table_is_loaded_negative() {
    when(webDriver.findElement(any(By.ByXPath.class)))
        .thenThrow(new NoSuchElementException("Element was not found"));

    NoSuchElementException ex =
        assertThrows(
            NoSuchElementException.class, () -> page.waitUntilRankingIndicatorsTableIsLoaded());

    assertTrue(ex.getMessage().contains("Ranking indicators table was not found by xpath = "));
  }

  @Test
  public void test_get_page_source() {
    String testPageSource = "test page source";
    when(webDriver.getPageSource()).thenReturn(testPageSource);

    String actualPageSource = page.getPageSource();

    assertEquals(testPageSource, actualPageSource);
    verify(webDriver).getPageSource();
  }

  @Test
  public void test_is_next_page_present_positive() {
    when(webDriver.findElements(any(By.ByXPath.class))).thenReturn(List.of(mock(WebElement.class)));

    assertTrue(page.isNextPagePresent());
  }

  @Test
  public void test_is_next_page_present_negative() {
    when(webDriver.findElements(any(By.ByXPath.class))).thenReturn(new ArrayList<>());

    assertFalse(page.isNextPagePresent());
  }

  @Test
  public void test_go_to_next_page() {
    WebElement nextPageButton = mock(WebElement.class);
    when(webDriver.findElements(any(By.ByXPath.class))).thenReturn(List.of(nextPageButton));
    when(webDriver.findElement(any(By.ByXPath.class))).thenReturn(nextPageButton);

    page.goToNextPage();

    verify((JavascriptExecutor) webDriver)
        .executeScript(RankingsListPage.JS_CLICK_SCRIPT, nextPageButton);
  }
}
