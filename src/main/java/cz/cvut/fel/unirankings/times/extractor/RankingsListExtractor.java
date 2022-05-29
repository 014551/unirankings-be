package cz.cvut.fel.unirankings.times.extractor;

import cz.cvut.fel.unirankings.common.configuration.IBrowserFactory;
import cz.cvut.fel.unirankings.common.extractor.ListExtractor;
import cz.cvut.fel.unirankings.times.model.parser.UniversityRankingScore;
import cz.cvut.fel.unirankings.times.pageobject.IPageObjectFactory;
import cz.cvut.fel.unirankings.times.pageobject.RankingsListPage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service("theRankingsListExtractor")
public class RankingsListExtractor implements ListExtractor<UniversityRankingScore> {

  private static final Logger LOGGER = LoggerFactory.getLogger(RankingsListExtractor.class);
  public static final String THE_PARSER_URL_PATH = "/TheParser";

  private final IBrowserFactory browserFactory;

  private final IPageObjectFactory pageObjectFactory;

  private final RestTemplate restClient;

  public RankingsListExtractor(
      IBrowserFactory browserFactory,
      IPageObjectFactory pageObjectFactory,
      RestTemplate restClient) {
    this.browserFactory = browserFactory;
    this.pageObjectFactory = pageObjectFactory;
    this.restClient = restClient;
  }

  public List<UniversityRankingScore> extract(String year) {
    RankingsListPage rankingsListPage =
        pageObjectFactory.createRankingsListPage(browserFactory.createBrowser(), year);
    rankingsListPage.navigate();

    List<UniversityRankingScore> universityRankingScores = parse(rankingsListPage);
    rankingsListPage.quit();
    return universityRankingScores;
  }

  private List<UniversityRankingScore> parse(RankingsListPage rankingsListPage) {
    String pageSource = rankingsListPage.getPageSource();
    if (StringUtils.isBlank(pageSource)) {
      LOGGER.warn("Page source is empty for the given URL {}", rankingsListPage.getCurrentUrl());
      return new ArrayList<>();
    }

    HttpEntity<String> request = new HttpEntity<>(pageSource);
    ResponseEntity<UniversityRankingScoreList> response =
        restClient.exchange(
            THE_PARSER_URL_PATH, HttpMethod.POST, request, UniversityRankingScoreList.class);

    return Objects.requireNonNull(response.getBody()).getUniversityRankingScores();
  }
}
