package cz.cvut.fel.unirankings.times.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "theRankingScore")
@Table(name = "ranking_score", schema = "the")
public class RankingScore implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "original_order_idx")
  private Integer originalOrderIdx;

  @Column(name = "overall_score")
  private String overallScore;

  @Column(name = "teaching_score")
  private String teachingScore;

  @Column(name = "research_score")
  private String researchScore;

  @Column(name = "citations_score")
  private String citationsScore;

  @Column(name = "industry_income_score")
  private String industryIncomeScore;

  @Column(name = "international_outlook_score")
  private String internationalOutlookScore;

  @Column(name = "year")
  private String year;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "university_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonBackReference
  private University university;

  public RankingScore() {}

  public RankingScore(
      Integer originalOrderIdx,
      String overallScore,
      String teachingScore,
      String researchScore,
      String citationsScore,
      String industryIncomeScore,
      String internationalOutlookScore,
      String year,
      University university) {
    this.originalOrderIdx = originalOrderIdx;
    this.overallScore = overallScore;
    this.teachingScore = teachingScore;
    this.researchScore = researchScore;
    this.citationsScore = citationsScore;
    this.industryIncomeScore = industryIncomeScore;
    this.internationalOutlookScore = internationalOutlookScore;
    this.year = year;
    this.university = university;
  }

  public long getId() {
    return id;
  }

  public Integer getOriginalOrderIdx() {
    return originalOrderIdx;
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

  public String getYear() {
    return year;
  }

  public University getUniversity() {
    return university;
  }

  public void setUniversity(University university) {
    this.university = university;
  }
}
