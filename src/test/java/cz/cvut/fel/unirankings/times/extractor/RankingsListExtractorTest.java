package cz.cvut.fel.unirankings.times.extractor;

import cz.cvut.fel.unirankings.common.configuration.BrowserFactory;
import cz.cvut.fel.unirankings.times.model.parser.UniversityRankingScore;
import cz.cvut.fel.unirankings.times.pageobject.PageObjectFactory;
import cz.cvut.fel.unirankings.times.pageobject.RankingsListPage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.openqa.selenium.WebDriver;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RankingsListExtractorTest {

  @Mock private WebDriver webDriver;

  @Mock private BrowserFactory browserFactory;

  @Mock private PageObjectFactory pageObjectFactory;

  @Mock private RestTemplate restTemplate;

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
  public void test_standard_execution() {
    when(browserFactory.createBrowser()).thenReturn(webDriver);

    RankingsListPage rankingsListPage = mock(RankingsListPage.class);
    when(rankingsListPage.getPageSource())
        .thenReturn(
            "<html><head><title>First parse</title></head><body><p>Parsed HTML into a doc.</p></body></html>");

    when(pageObjectFactory.createRankingsListPage(eq(webDriver), anyString()))
        .thenReturn(rankingsListPage);

    when(restTemplate.exchange(
            anyString(), eq(HttpMethod.POST), any(), eq(UniversityRankingScoreList.class)))
        .thenReturn(
            new ResponseEntity<>(
                new UniversityRankingScoreList(
                    Arrays.asList(
                        mock(UniversityRankingScore.class), mock(UniversityRankingScore.class))),
                HttpStatus.OK));

    RankingsListExtractor rankingsListExtractor =
        new RankingsListExtractor(browserFactory, pageObjectFactory, restTemplate);
    List<UniversityRankingScore> universities = rankingsListExtractor.extract("2018");

    assertNotNull(universities);
    assertEquals(2, universities.size());

    verify(rankingsListPage).navigate();
    verify(rankingsListPage).getPageSource();
  }

  @Test
  public void test_empty_array_is_returned_when_page_source_is_blank() {
    when(browserFactory.createBrowser()).thenReturn(webDriver);

    RankingsListPage rankingsListPage = mock(RankingsListPage.class);
    when(rankingsListPage.getPageSource()).thenReturn(" ");

    when(pageObjectFactory.createRankingsListPage(eq(webDriver), anyString()))
        .thenReturn(rankingsListPage);

    RankingsListExtractor rankingsListExtractor =
        new RankingsListExtractor(browserFactory, pageObjectFactory, restTemplate);

    List<UniversityRankingScore> universities = rankingsListExtractor.extract("2018");
    assertNotNull(universities);
    assertTrue(universities.isEmpty());
  }

  @Test
  public void test_parse_is_not_called_when_page_source_is_blank() {
    when(browserFactory.createBrowser()).thenReturn(webDriver);

    RankingsListPage rankingsListPage = mock(RankingsListPage.class);
    when(rankingsListPage.getPageSource()).thenReturn("\n \n \t \t");

    when(pageObjectFactory.createRankingsListPage(eq(webDriver), anyString()))
        .thenReturn(rankingsListPage);

    RankingsListExtractor rankingsListExtractor =
        new RankingsListExtractor(browserFactory, pageObjectFactory, restTemplate);

    rankingsListExtractor.extract("2018");

    verify(restTemplate, times(0))
        .exchange(
            eq("/TheParser"), eq(HttpMethod.POST), any(), eq(UniversityRankingScoreList.class));
  }
}
