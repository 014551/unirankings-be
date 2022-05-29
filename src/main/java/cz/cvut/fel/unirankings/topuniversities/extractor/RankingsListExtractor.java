package cz.cvut.fel.unirankings.topuniversities.extractor;

import cz.cvut.fel.unirankings.common.configuration.IBrowserFactory;
import cz.cvut.fel.unirankings.common.extractor.ListExtractor;
import cz.cvut.fel.unirankings.topuniversities.model.parser.UniversityRankingIndicator;
import cz.cvut.fel.unirankings.topuniversities.pageobjects.IPageObjectFactory;
import cz.cvut.fel.unirankings.topuniversities.pageobjects.RankingsListPage;
import cz.cvut.fel.unirankings.topuniversities.parsers.RankingIndicatorParser;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service("qsRankingsListExtractor")
public class RankingsListExtractor implements ListExtractor<UniversityRankingIndicator> {

  private final List<RankingIndicatorParser> parsers;

  private final IPageObjectFactory pageObjectFactory;

  private final IBrowserFactory browserFactory;

  public RankingsListExtractor(
      List<RankingIndicatorParser> parsers,
      IPageObjectFactory pageObjectFactory,
      IBrowserFactory browserFactory) {
    this.parsers = parsers;
    this.pageObjectFactory = pageObjectFactory;
    this.browserFactory = browserFactory;
  }

  public List<UniversityRankingIndicator> extract(String year) throws Exception {
    RankingsListPage rankingsListPage =
        pageObjectFactory.createRankingsListPage(browserFactory.createBrowser(), year);
    RankingIndicatorParser parser =
        parsers.stream()
            .filter(rankingIndicatorParser -> rankingIndicatorParser.isCorrectParser(year))
            .findFirst()
            .orElseThrow();

    rankingsListPage.navigate();
    rankingsListPage.removeSlidingPopup();
    rankingsListPage.expandResults(100);
    rankingsListPage.switchToRankingIndicatorsTab();
    rankingsListPage.waitUntilRankingIndicatorsTableIsLoaded();

    List<UniversityRankingIndicator> universityRankingIndicators = parse(rankingsListPage, parser);

    rankingsListPage.quit();
    return universityRankingIndicators;
  }

  private List<UniversityRankingIndicator> parse(
      RankingsListPage rankingsListPage, RankingIndicatorParser parser) throws Exception {
    List<UniversityRankingIndicator> rankingIndicators = new ArrayList<>();
    Document doc;

    String pageSource = rankingsListPage.getPageSource();
    if (StringUtils.isNotBlank(pageSource)) {
      doc = Jsoup.parse(pageSource);
      rankingIndicators.addAll(parser.parse(doc));
    }

    while (rankingsListPage.isNextPagePresent()) {
      rankingsListPage.goToNextPage();
      Thread.sleep(1500);
      pageSource = rankingsListPage.getPageSource();
      if (StringUtils.isNotBlank(pageSource)) {
        doc = Jsoup.parse(pageSource);
        rankingIndicators.addAll(parser.parse(doc));
      }
    }

    return IntStream.range(0, rankingIndicators.size())
        .mapToObj(i -> new UniversityRankingIndicator(i, rankingIndicators.get(i)))
        .collect(Collectors.toList());
  }
}
