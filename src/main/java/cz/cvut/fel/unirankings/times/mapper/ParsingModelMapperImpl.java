package cz.cvut.fel.unirankings.times.mapper;

import cz.cvut.fel.unirankings.times.model.Rank;
import cz.cvut.fel.unirankings.times.model.RankingScore;
import cz.cvut.fel.unirankings.times.model.University;
import cz.cvut.fel.unirankings.times.model.parser.UniversityRankingScore;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service("theParsingModelMapper")
public class ParsingModelMapperImpl implements ParsingModelMapper {

  @Override
  public List<University> mapToUniversities(
      final List<UniversityRankingScore> urScores, String year) {
    return urScores.stream()
        .map(urScore -> mapToUniversity(urScore, year))
        .collect(Collectors.toList());
  }

  private University mapToUniversity(final UniversityRankingScore urScore, String year) {
    University university =
        new University(urScore.getName(), urScore.getDetailLinkPath(), urScore.getCountry());

    Rank rank = new Rank(urScore.getOriginalOrderIdx(), year, urScore.getRank(), university);

    RankingScore rankingScore =
        new RankingScore(
            urScore.getOriginalOrderIdx(),
            urScore.getOverallScore(),
            urScore.getTeachingScore(),
            urScore.getResearchScore(),
            urScore.getCitationsScore(),
            urScore.getIndustryIncomeScore(),
            urScore.getInternationalOutlookScore(),
            year,
            university);

    university.getRanks().add(rank);
    university.getRankingScores().add(rankingScore);

    return university;
  }
}
