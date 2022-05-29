package cz.cvut.fel.unirankings.topuniversities.parsers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Qualifier("qsParser20142016")
public class RankingIndicatorParser20142016 extends AbstractRankingIndicatorParser {

  public RankingIndicatorParser20142016() {
    super();
  }

  @Override
  protected String getInternationalStudentsRatioCssQuery() {
    return ".overall-score-span-ind.ind_37";
  }

  @Override
  protected String getInternationalFacultyRatioCssQuery() {
    return ".overall-score-span-ind.ind_75";
  }

  @Override
  protected String getFacultyStudentRatioCssQuery() {
    return ".overall-score-span-ind.ind_74";
  }

  @Override
  protected String getCitationsPerFacultyCssQuery() {
    return ".overall-score-span-ind.ind_73";
  }

  @Override
  protected String getAcademicReputationCssQuery() {
    return ".overall-score-span-ind.ind_76";
  }

  @Override
  protected String getEmployerReputationCssQuery() {
    return ".overall-score-span-ind.ind_77";
  }

  @Override
  public boolean isCorrectParser(String year) {
    return Arrays.asList("2014", "2015", "2016").contains(year);
  }
}
