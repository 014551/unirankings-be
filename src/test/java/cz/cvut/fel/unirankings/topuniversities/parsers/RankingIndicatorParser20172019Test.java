package cz.cvut.fel.unirankings.topuniversities.parsers;

import cz.cvut.fel.unirankings.topuniversities.model.parser.UniversityRankingIndicator;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class RankingIndicatorParser20172019Test extends AbstractRankingIndicatorParserTest {

  private static final String RESOURCE_PATH = "src/test/resources/topuniversities/parser/20172019";

  private RankingIndicatorParser parser;

  @BeforeEach
  public void setup() {
    parser = new RankingIndicatorParser20172019();
  }

  @Test
  public void test_university_rankings() throws Exception {
    String doc = getHtmlFromResources("TestUniversityRankings2018.html");
    List<UniversityRankingIndicator> expectedUniversities =
        getExpectedUniversities("TestUniversityRankings2018_expected.txt");

    List<UniversityRankingIndicator> actualUniversities = parser.parse(Jsoup.parse(doc));
    assertUniversityRankingIndicatorsEqual(expectedUniversities, actualUniversities);
  }

  @Override
  protected UniversityRankingIndicator getExpectedUniversity(String[] line) {
    return new UniversityRankingIndicator(
        line[0], line[1], line[2], line[3], line[4], line[5], line[11], line[10], line[9], line[7],
        line[6], line[8]);
  }

  @Override
  protected String getResourcePath() {
    return RESOURCE_PATH;
  }
}
