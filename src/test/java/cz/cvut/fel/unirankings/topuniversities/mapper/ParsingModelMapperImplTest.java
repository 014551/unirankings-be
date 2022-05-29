package cz.cvut.fel.unirankings.topuniversities.mapper;

import cz.cvut.fel.unirankings.topuniversities.model.Rank;
import cz.cvut.fel.unirankings.topuniversities.model.RankingIndicator;
import cz.cvut.fel.unirankings.topuniversities.model.University;
import cz.cvut.fel.unirankings.topuniversities.model.parser.UniversityRankingIndicator;
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
    UniversityRankingIndicator urScore1 =
        new UniversityRankingIndicator(
            1,
            "1",
            "testName",
            "/path/to/detail",
            "London",
            "United Kingdom",
            "99.9",
            "12.2",
            "97.6",
            "22",
            "12.2",
            "12.21",
            "12.45");
    UniversityRankingIndicator urScore2 =
        new UniversityRankingIndicator(
            2, null, null, null, null, null, null, null, null, null, null, null, null);
    List<UniversityRankingIndicator> urScores = List.of(urScore1, urScore2);

    List<University> universities = mapper.mapToUniversities(urScores, "2018");
    Assertions.assertNotNull(universities);
    Assertions.assertEquals(2, universities.size());

    University university1 = universities.get(0);

    Assertions.assertEquals(urScore1.getName(), university1.getName());
    Assertions.assertEquals(urScore1.getDetailLinkPath(), university1.getDetailLinkPath());
    Assertions.assertEquals(urScore1.getCity(), university1.getCity());
    Assertions.assertEquals(urScore1.getCountry(), university1.getCountry());

    RankingIndicator rankingIndicator1 = university1.getRankingIndicators().iterator().next();
    Assertions.assertEquals(
        urScore1.getOriginalRowElementIdx(), rankingIndicator1.getOriginalOrderIdx());
    Assertions.assertEquals("2018", rankingIndicator1.getYear());
    Assertions.assertEquals(urScore1.getOverallScore(), rankingIndicator1.getOverallScore());
    Assertions.assertEquals(
        urScore1.getInternationalStudentsRatio(),
        rankingIndicator1.getInternationalStudentsRatio());
    Assertions.assertEquals(
        urScore1.getInternationalFacultyRatio(), rankingIndicator1.getInternationalFacultyRatio());
    Assertions.assertEquals(
        urScore1.getFacultyStudentRatio(), rankingIndicator1.getFacultyStudentRatio());
    Assertions.assertEquals(
        urScore1.getCitationPerFaculty(), rankingIndicator1.getCitationPerFaculty());
    Assertions.assertEquals(
        urScore1.getAcademicReputation(), rankingIndicator1.getAcademicReputation());
    Assertions.assertEquals(
        urScore1.getEmployerReputation(), rankingIndicator1.getEmployerReputation());

    Rank rank1 = university1.getRanks().iterator().next();
    Assertions.assertEquals(urScore1.getOriginalRowElementIdx(), rank1.getOriginalOrderIdx());
    Assertions.assertEquals("2018", rank1.getYear());
    Assertions.assertEquals(urScore1.getRank(), rank1.getRank());

    University university2 = universities.get(1);

    Assertions.assertEquals(urScore2.getName(), university2.getName());
    Assertions.assertEquals(urScore2.getDetailLinkPath(), university2.getDetailLinkPath());
    Assertions.assertEquals(urScore2.getCountry(), university2.getCountry());

    RankingIndicator rankingIndicator2 = university2.getRankingIndicators().iterator().next();
    Assertions.assertEquals(
        urScore2.getOriginalRowElementIdx(), rankingIndicator2.getOriginalOrderIdx());
    Assertions.assertEquals("2018", rankingIndicator2.getYear());
    Assertions.assertEquals(urScore2.getOverallScore(), rankingIndicator2.getOverallScore());
    Assertions.assertEquals(
        urScore2.getInternationalStudentsRatio(),
        rankingIndicator2.getInternationalStudentsRatio());
    Assertions.assertEquals(
        urScore2.getInternationalFacultyRatio(), rankingIndicator2.getInternationalFacultyRatio());
    Assertions.assertEquals(
        urScore2.getFacultyStudentRatio(), rankingIndicator2.getFacultyStudentRatio());
    Assertions.assertEquals(
        urScore2.getCitationPerFaculty(), rankingIndicator2.getCitationPerFaculty());
    Assertions.assertEquals(
        urScore2.getAcademicReputation(), rankingIndicator2.getAcademicReputation());
    Assertions.assertEquals(
        urScore2.getEmployerReputation(), rankingIndicator2.getEmployerReputation());

    Rank rank2 = university2.getRanks().iterator().next();
    Assertions.assertEquals(urScore2.getOriginalRowElementIdx(), rank2.getOriginalOrderIdx());
    Assertions.assertEquals("2018", rank2.getYear());
    Assertions.assertEquals(urScore2.getRank(), rank2.getRank());
  }
}
