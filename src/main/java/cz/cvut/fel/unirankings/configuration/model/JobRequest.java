package cz.cvut.fel.unirankings.configuration.model;

public class JobRequest {

  private final RankingIdentifier rankingIdentifier;

  private final String year;

  public JobRequest(RankingIdentifier rankingIdentifier, String year) {
    this.rankingIdentifier = rankingIdentifier;
    this.year = year;
  }

  public RankingIdentifier getRankingIdentifier() {
    return rankingIdentifier;
  }

  public String getYear() {
    return year;
  }
}
