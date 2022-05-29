package cz.cvut.fel.unirankings.topuniversities.extractor;

import cz.cvut.fel.unirankings.common.configuration.BrowserFactory;
import cz.cvut.fel.unirankings.topuniversities.model.parser.UniversityRankingIndicator;
import cz.cvut.fel.unirankings.topuniversities.pageobjects.PageObjectFactory;
import cz.cvut.fel.unirankings.topuniversities.pageobjects.RankingsListPage;
import cz.cvut.fel.unirankings.topuniversities.parsers.RankingIndicatorParser;
import cz.cvut.fel.unirankings.topuniversities.parsers.RankingIndicatorParser20142016;
import cz.cvut.fel.unirankings.topuniversities.parsers.RankingIndicatorParser20172019;
import cz.cvut.fel.unirankings.topuniversities.parsers.RankingIndicatorParser20202022;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class RankingsListExtractorTest {

  private static final String RESOURCE_PATH = "src/test/resources/topuniversities/parser/20142016";
  private final List<RankingIndicatorParser> rankingIndicatorParsers =
      Arrays.asList(
          new RankingIndicatorParser20142016(),
          new RankingIndicatorParser20172019(),
          new RankingIndicatorParser20202022());

  @Mock private WebDriver webDriver;
  @Mock private BrowserFactory browserFactory;
  @Mock private PageObjectFactory pageObjectFactory;

  private AutoCloseable closeable;

  @BeforeEach
  public void setup() {
    closeable = MockitoAnnotations.openMocks(this);
  }

  @AfterEach
  void closeService() throws Exception {
    closeable.close();
  }

  @Test
  public void test_standard_execution() throws Exception {
    RankingsListPage rankingsListPage = mock(RankingsListPage.class);
    when(rankingsListPage.getPageSource())
        .thenReturn(getHtmlFromResources("TestUniversityRankings2016.html"));
    when(rankingsListPage.isNextPagePresent()).thenReturn(false);

    when(pageObjectFactory.createRankingsListPage(eq(webDriver), anyString()))
        .thenReturn(rankingsListPage);
    when(browserFactory.createBrowser()).thenReturn(webDriver);

    RankingsListExtractor rankingsListExtractor =
        new RankingsListExtractor(rankingIndicatorParsers, pageObjectFactory, browserFactory);
    List<UniversityRankingIndicator> universities = rankingsListExtractor.extract("2016");

    assertNotNull(universities);
    assertEquals(10, universities.size());

    verify(rankingsListPage).navigate();
    verify(rankingsListPage).removeSlidingPopup();
    verify(rankingsListPage).expandResults(100);
    verify(rankingsListPage).switchToRankingIndicatorsTab();
    verify(rankingsListPage).waitUntilRankingIndicatorsTableIsLoaded();
    verify(rankingsListPage).getPageSource();
  }

  @Test
  public void test_standard_execution_next_page_is_present() throws Exception {
    RankingsListPage rankingsListPage = mock(RankingsListPage.class);
    when(rankingsListPage.getPageSource())
        .thenReturn(getHtmlFromResources("TestUniversityRankings2016.html"));
    when(rankingsListPage.isNextPagePresent()).thenReturn(true).thenReturn(false);

    when(pageObjectFactory.createRankingsListPage(eq(webDriver), anyString()))
        .thenReturn(rankingsListPage);
    when(browserFactory.createBrowser()).thenReturn(webDriver);

    RankingsListExtractor rankingsListExtractor =
        new RankingsListExtractor(rankingIndicatorParsers, pageObjectFactory, browserFactory);
    List<UniversityRankingIndicator> universities = rankingsListExtractor.extract("2016");

    assertNotNull(universities);
    assertEquals(20, universities.size());

    verify(rankingsListPage).navigate();
    verify(rankingsListPage).removeSlidingPopup();
    verify(rankingsListPage).expandResults(100);
    verify(rankingsListPage).switchToRankingIndicatorsTab();
    verify(rankingsListPage).waitUntilRankingIndicatorsTableIsLoaded();
    verify(rankingsListPage, times(2)).getPageSource();
    verify(rankingsListPage).goToNextPage();
  }

  @Test
  public void test_empty_array_is_returned_when_page_source_is_blank() throws Exception {
    RankingsListPage rankingsListPage = mock(RankingsListPage.class);
    when(rankingsListPage.getPageSource()).thenReturn(" ");
    when(rankingsListPage.isNextPagePresent()).thenReturn(false);

    when(pageObjectFactory.createRankingsListPage(eq(webDriver), anyString()))
        .thenReturn(rankingsListPage);
    when(browserFactory.createBrowser()).thenReturn(webDriver);

    RankingsListExtractor rankingsListExtractor =
        new RankingsListExtractor(rankingIndicatorParsers, pageObjectFactory, browserFactory);
    List<UniversityRankingIndicator> universities = rankingsListExtractor.extract("2016");

    assertNotNull(universities);
    assertTrue(universities.isEmpty());
  }

  protected String getHtmlFromResources(String fileName) throws IOException {
    File file = new File(getResourcePath() + "/" + fileName);
    return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
  }

  protected String getResourcePath() {
    return RESOURCE_PATH;
  }
}
