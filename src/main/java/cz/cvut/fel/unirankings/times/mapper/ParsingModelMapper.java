package cz.cvut.fel.unirankings.times.mapper;

import cz.cvut.fel.unirankings.times.model.University;
import cz.cvut.fel.unirankings.times.model.parser.UniversityRankingScore;

import java.util.List;

/** Mapper to convert extracted data objects to database model objects. */
public interface ParsingModelMapper {

  /**
   * Maps extracted university ranking score objects to university objects as defined in database
   * model.
   *
   * @param urScores extracted university ranking score objects
   * @param year of extracted university ranking score objects
   * @return list of universities
   */
  List<University> mapToUniversities(final List<UniversityRankingScore> urScores, String year);
}
