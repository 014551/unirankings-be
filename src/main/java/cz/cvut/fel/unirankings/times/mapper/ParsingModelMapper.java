package cz.cvut.fel.unirankings.times.mapper;

import cz.cvut.fel.unirankings.times.model.University;
import cz.cvut.fel.unirankings.times.model.parser.UniversityRankingScore;

import java.util.List;

public interface ParsingModelMapper {

    List<University> mapToUniversities(final List<UniversityRankingScore> urScores, String year);

}
