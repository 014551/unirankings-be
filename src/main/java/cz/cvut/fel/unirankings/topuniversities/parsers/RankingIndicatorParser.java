package cz.cvut.fel.unirankings.topuniversities.parsers;

import cz.cvut.fel.unirankings.topuniversities.model.parser.UniversityRankingIndicator;
import org.jsoup.nodes.Document;

import java.util.List;

public interface RankingIndicatorParser {

  List<UniversityRankingIndicator> parse(Document document) throws Exception;

  boolean isCorrectParser(String year);
}
