package cz.cvut.fel.unirankings.times.extractor;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.cvut.fel.unirankings.times.model.parser.UniversityRankingScore;

import java.util.List;

public class UniversityRankingScoreList {

  private List<UniversityRankingScore> universityRankingScores;

  public UniversityRankingScoreList(
      @JsonProperty("universityRankingScores")
          List<UniversityRankingScore> universityRankingScores) {
    this.universityRankingScores = universityRankingScores;
  }

  public List<UniversityRankingScore> getUniversityRankingScores() {
    return universityRankingScores;
  }

  public void setUniversityRankingScores(List<UniversityRankingScore> universityRankingScores) {
    this.universityRankingScores = universityRankingScores;
  }
}
