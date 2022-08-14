package cz.cvut.fel.unirankings.topuniversities.parsers;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Arrays;

/** Parser for university rankings from 2020, 2021, 2022. */
@Service
@Qualifier("qsParser20202022")
public class RankingIndicatorParser20202022 extends AbstractRankingIndicatorParser {

  public RankingIndicatorParser20202022() {
    super();
  }

  @Override
  protected String getInternationalStudentsRatioCssQuery() {
    return ".overall-score-span-ind.ind_14";
  }

  @Override
  protected String getInternationalFacultyRatioCssQuery() {
    return ".overall-score-span-ind.ind_18";
  }

  @Override
  protected String getFacultyStudentRatioCssQuery() {
    return ".overall-score-span-ind.ind_36";
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
    return Arrays.asList("2020", "2021", "2022").contains(year);
  }
}
