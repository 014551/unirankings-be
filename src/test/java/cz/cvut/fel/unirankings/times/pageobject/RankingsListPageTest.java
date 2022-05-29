package cz.cvut.fel.unirankings.times.pageobject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.WebDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RankingsListPageTest {

  private final String year = "2018";
  /** Tested page object. */
  private RankingsListPage page;

  private AutoCloseable closeable;

  @Mock private WebDriver webDriver;

  @BeforeEach
  public void setup() {
    closeable = MockitoAnnotations.openMocks(this);
    page = new RankingsListPage(webDriver, year);
  }

  @AfterEach
  public void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  public void test_navigate() {
    WebDriver.Navigation mockNavigate = mock(WebDriver.Navigation.class);
    when(webDriver.navigate()).thenReturn(mockNavigate);

    page.navigate();

    verify(webDriver).navigate();
    verify(mockNavigate)
        .to(RankingsListPage.THE_DOMAIN + String.format(RankingsListPage.RANKING_LIST_PATH, year));
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
  public void test_get_current_url() {
    String testCurrentUrl = "https://fel.cvut.cz";
    when(webDriver.getCurrentUrl()).thenReturn(testCurrentUrl);

    String actualCurrentUrl = page.getCurrentUrl();

    assertEquals(testCurrentUrl, actualCurrentUrl);
    verify(webDriver).getCurrentUrl();
  }
}
