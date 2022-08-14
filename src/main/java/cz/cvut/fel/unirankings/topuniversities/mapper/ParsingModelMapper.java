package cz.cvut.fel.unirankings.topuniversities.mapper;

import cz.cvut.fel.unirankings.topuniversities.model.University;
import cz.cvut.fel.unirankings.topuniversities.model.parser.UniversityRankingIndicator;

import java.util.List;

/** Mapper to convert extracted data objects to database model objects. */
public interface ParsingModelMapper {

  /**
   * Maps extracted university ranking indicator objects to university objects as defined in
   * database model.
   *
   * @param universityRankingIndicators extracted university ranking indicator objects
   * @param year of extracted university ranking indicator objects
   * @return list of universities
   */
  List<University> mapToUniversities(
      final List<UniversityRankingIndicator> universityRankingIndicators, String year);
}
