package cz.cvut.fel.unirankings.topuniversities.mapper;

import cz.cvut.fel.unirankings.topuniversities.model.University;
import cz.cvut.fel.unirankings.topuniversities.model.parser.UniversityRankingIndicator;

import java.util.List;

public interface ParsingModelMapper {

  List<University> mapToUniversities(
      final List<UniversityRankingIndicator> universityRankingIndicators, String year);
}
