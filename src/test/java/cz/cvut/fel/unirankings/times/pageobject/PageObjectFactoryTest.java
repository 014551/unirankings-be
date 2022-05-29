package cz.cvut.fel.unirankings.times.pageobject;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.WebDriver;

class PageObjectFactoryTest {

  private static final String YEAR = "2016";

  @Mock private WebDriver webDriver;
  private IPageObjectFactory pageObjectFactory;

  private AutoCloseable closeable;

  @BeforeEach
  public void setup() {
    closeable = MockitoAnnotations.openMocks(this);
    pageObjectFactory = new PageObjectFactory();
  }

  @AfterEach
  public void tearDown() throws Exception {
    closeable.close();
  }

  @Test
  public void test_create_rankings_list_page() {
    RankingsListPage rankingsListPage = pageObjectFactory.createRankingsListPage(webDriver, YEAR);

    Assertions.assertNotNull(rankingsListPage);
  }
}
