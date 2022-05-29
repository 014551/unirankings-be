package cz.cvut.fel.unirankings.topuniversities.parsers;

import cz.cvut.fel.unirankings.topuniversities.model.parser.UniversityRankingIndicator;
import org.jsoup.Jsoup;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class RankingIndicatorParser20202022Test extends AbstractRankingIndicatorParserTest {
  private static final String RESOURCE_PATH = "src/test/resources/topuniversities/parser/20202022";

  private RankingIndicatorParser parser;

  @BeforeEach
  public void setup() {
    parser = new RankingIndicatorParser20202022();
  }

  @Test
  public void test_university_rankings() throws Exception {
    String doc = getHtmlFromResources("TestUniversityRankings2021.html");
    List<UniversityRankingIndicator> expectedUniversities =
        getExpectedUniversities("TestUniversityRankings2021_expected.txt");

    List<UniversityRankingIndicator> actualUniversities = parser.parse(Jsoup.parse(doc));
    assertUniversityRankingIndicatorsEqual(expectedUniversities, actualUniversities);
  }

  @Override
  protected UniversityRankingIndicator getExpectedUniversity(String[] line) {
    return new UniversityRankingIndicator(
        line[0], line[1], line[2], line[3], line[4], line[5], line[6], line[7], line[8], line[9],
        line[10], line[11]);
  }

  @Override
  protected String getResourcePath() {
    return RESOURCE_PATH;
  }
}
