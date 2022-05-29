package cz.cvut.fel.unirankings.times.mapper;

import cz.cvut.fel.unirankings.times.model.Rank;
import cz.cvut.fel.unirankings.times.model.RankingScore;
import cz.cvut.fel.unirankings.times.model.University;
import cz.cvut.fel.unirankings.times.model.parser.UniversityRankingScore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ParsingModelMapperImplTest {

  private ParsingModelMapper mapper;

  @BeforeEach
  public void setup() {
    mapper = new ParsingModelMapperImpl();
  }

  @Test
  void test_map_to_universities() {
    UniversityRankingScore urScore1 =
        new UniversityRankingScore(1,
            "1",
            "testName",
            "/path/to/detail",
            "United Kingdom",
            "99.9",
            "12.2",
            "97.6",
            "22",
            "12.2",
            "12.21");
    UniversityRankingScore urScore2 =
        new UniversityRankingScore(2, null, null, null, null, null, null, null, null, null, null);
    List<UniversityRankingScore> urScores = List.of(urScore1, urScore2);

    List<University> universities = mapper.mapToUniversities(urScores, "2018");
    Assertions.assertNotNull(universities);
    Assertions.assertEquals(2, universities.size());

    University university1 = universities.get(0);

    Assertions.assertEquals(urScore1.getName(), university1.getName());
    Assertions.assertEquals(urScore1.getDetailLinkPath(), university1.getDetailLinkPath());
    Assertions.assertEquals(urScore1.getCountry(), university1.getCountry());

    RankingScore rankingScore1 = university1.getRankingScores().iterator().next();
    Assertions.assertEquals(urScore1.getOriginalOrderIdx(), rankingScore1.getOriginalOrderIdx());
    Assertions.assertEquals("2018", rankingScore1.getYear());
    Assertions.assertEquals(urScore1.getOverallScore(), rankingScore1.getOverallScore());
    Assertions.assertEquals(urScore1.getTeachingScore(), rankingScore1.getTeachingScore());
    Assertions.assertEquals(urScore1.getResearchScore(), rankingScore1.getResearchScore());
    Assertions.assertEquals(urScore1.getCitationsScore(), rankingScore1.getCitationsScore());
    Assertions.assertEquals(
        urScore1.getIndustryIncomeScore(), rankingScore1.getIndustryIncomeScore());
    Assertions.assertEquals(
        urScore1.getInternationalOutlookScore(), rankingScore1.getInternationalOutlookScore());

    Rank rank1 = university1.getRanks().iterator().next();
    Assertions.assertEquals(urScore1.getOriginalOrderIdx(), rank1.getOriginalOrderIdx());
    Assertions.assertEquals("2018", rank1.getYear());
    Assertions.assertEquals(urScore1.getRank(), rank1.getRank());

    University university2 = universities.get(1);

    Assertions.assertEquals(urScore2.getName(), university2.getName());
    Assertions.assertEquals(urScore2.getDetailLinkPath(), university2.getDetailLinkPath());
    Assertions.assertEquals(urScore2.getCountry(), university2.getCountry());

    RankingScore rankingScore2 = university2.getRankingScores().iterator().next();
    Assertions.assertEquals(urScore2.getOriginalOrderIdx(), rankingScore2.getOriginalOrderIdx());
    Assertions.assertEquals("2018", rankingScore2.getYear());
    Assertions.assertEquals(urScore2.getOverallScore(), rankingScore2.getOverallScore());
    Assertions.assertEquals(urScore2.getTeachingScore(), rankingScore2.getTeachingScore());
    Assertions.assertEquals(urScore2.getResearchScore(), rankingScore2.getResearchScore());
    Assertions.assertEquals(urScore2.getCitationsScore(), rankingScore2.getCitationsScore());
    Assertions.assertEquals(
        urScore2.getIndustryIncomeScore(), rankingScore2.getIndustryIncomeScore());
    Assertions.assertEquals(
        urScore2.getInternationalOutlookScore(), rankingScore2.getInternationalOutlookScore());

    Rank rank2 = university2.getRanks().iterator().next();
    Assertions.assertEquals(urScore2.getOriginalOrderIdx(), rank2.getOriginalOrderIdx());
    Assertions.assertEquals("2018", rank2.getYear());
    Assertions.assertEquals(urScore2.getRank(), rank2.getRank());
  }
}
