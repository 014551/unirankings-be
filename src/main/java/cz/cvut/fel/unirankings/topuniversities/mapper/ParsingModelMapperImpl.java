package cz.cvut.fel.unirankings.topuniversities.mapper;

import cz.cvut.fel.unirankings.topuniversities.model.Rank;
import cz.cvut.fel.unirankings.topuniversities.model.RankingIndicator;
import cz.cvut.fel.unirankings.topuniversities.model.University;
import cz.cvut.fel.unirankings.topuniversities.model.parser.UniversityRankingIndicator;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("qsParsingModelMapper")
public class ParsingModelMapperImpl implements ParsingModelMapper {

  public List<University> mapToUniversities(
      final List<UniversityRankingIndicator> urIndicators, String year) {
    return urIndicators.stream()
        .map(urIndicator -> mapToUniversity(urIndicator, year))
        .collect(Collectors.toList());
  }

  private University mapToUniversity(final UniversityRankingIndicator urIndicator, String year) {
    University university =
        new University(
            urIndicator.getName(),
            urIndicator.getDetailLinkPath(),
            urIndicator.getCity(),
            urIndicator.getCountry());

    Rank rank =
        new Rank(urIndicator.getOriginalRowElementIdx(), year, urIndicator.getRank(), university);

    RankingIndicator rankingIndicator =
        new RankingIndicator(
            urIndicator.getOriginalRowElementIdx(),
            year,
            urIndicator.getOverallScore(),
            urIndicator.getInternationalStudentsRatio(),
            urIndicator.getInternationalFacultyRatio(),
            urIndicator.getFacultyStudentRatio(),
            urIndicator.getCitationPerFaculty(),
            urIndicator.getAcademicReputation(),
            urIndicator.getEmployerReputation(),
            university);

    university.getRanks().add(rank);
    university.getRankingIndicators().add(rankingIndicator);

    return university;
  }
}
