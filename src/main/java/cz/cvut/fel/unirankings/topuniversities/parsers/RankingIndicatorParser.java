package cz.cvut.fel.unirankings.topuniversities.parsers;

import cz.cvut.fel.unirankings.topuniversities.model.parser.UniversityRankingIndicator;
import org.jsoup.nodes.Document;

import java.util.List;

/** Parser for the documents from topuniverisites.com */
public interface RankingIndicatorParser {

  /**
   * Parses the input document with university rankings.
   *
   * @param document with university rankings
   * @return university rankings
   * @throws Exception exceptions in case of missing document
   */
  List<UniversityRankingIndicator> parse(Document document) throws Exception;

  /**
   * Helps to choose the right parsing logic based on the year of the university rankings.
   *
   * @param year of the university rankings.
   * @return true, if it is the right implementation, false - otherwise
   */
  boolean isCorrectParser(String year);
}
