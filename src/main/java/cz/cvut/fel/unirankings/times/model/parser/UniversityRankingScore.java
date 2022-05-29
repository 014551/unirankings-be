package cz.cvut.fel.unirankings.times.model.parser;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class UniversityRankingScore {

  private final int originalOrderIdx;

  private final String rank;

  private final String name;

  private final String detailLinkPath;

  private final String country;

  private final String overallScore;

  private final String teachingScore;

  private final String researchScore;

  private final String citationsScore;

  private final String industryIncomeScore;

  private final String internationalOutlookScore;

  public UniversityRankingScore(
      @JsonProperty("originalOrderIdx") int originalOrderIdx,
      @JsonProperty("rank") String rank,
      @JsonProperty("name") String name,
      @JsonProperty("detailLinkPath") String detailLinkPath,
      @JsonProperty("country") String country,
      @JsonProperty("overallScore") String overallScore,
      @JsonProperty("teachingScore") String teachingScore,
      @JsonProperty("researchScore") String researchScore,
      @JsonProperty("citationsScore") String citationsScore,
      @JsonProperty("industryIncomeScore") String industryIncomeScore,
      @JsonProperty("internationalOutlookScore") String internationalOutlookScore) {
    this.originalOrderIdx = originalOrderIdx;
    this.name = name;
    this.detailLinkPath = detailLinkPath;
    this.country = country;
    this.rank = rank;
    this.overallScore = overallScore;
    this.teachingScore = teachingScore;
    this.researchScore = researchScore;
    this.citationsScore = citationsScore;
    this.industryIncomeScore = industryIncomeScore;
    this.internationalOutlookScore = internationalOutlookScore;
  }

  public int getOriginalOrderIdx() {
    return originalOrderIdx;
  }

  public String getRank() {
    return rank;
  }

  public String getName() {
    return name;
  }

  public String getDetailLinkPath() {
    return detailLinkPath;
  }

  public String getCountry() {
    return country;
  }

  public String getOverallScore() {
    return overallScore;
  }

  public String getTeachingScore() {
    return teachingScore;
  }

  public String getResearchScore() {
    return researchScore;
  }

  public String getCitationsScore() {
    return citationsScore;
  }

  public String getIndustryIncomeScore() {
    return industryIncomeScore;
  }

  public String getInternationalOutlookScore() {
    return internationalOutlookScore;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UniversityRankingScore that = (UniversityRankingScore) o;
    return Objects.equals(originalOrderIdx, that.originalOrderIdx)
        && Objects.equals(rank, that.rank)
        && Objects.equals(name, that.name)
        && Objects.equals(detailLinkPath, that.detailLinkPath)
        && Objects.equals(country, that.country)
        && Objects.equals(overallScore, that.overallScore)
        && Objects.equals(teachingScore, that.teachingScore)
        && Objects.equals(researchScore, that.researchScore)
        && Objects.equals(citationsScore, that.citationsScore)
        && Objects.equals(industryIncomeScore, that.industryIncomeScore)
        && Objects.equals(internationalOutlookScore, that.internationalOutlookScore);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        originalOrderIdx,
        rank,
        name,
        detailLinkPath,
        country,
        overallScore,
        teachingScore,
        researchScore,
        citationsScore,
        industryIncomeScore,
        internationalOutlookScore);
  }
}
