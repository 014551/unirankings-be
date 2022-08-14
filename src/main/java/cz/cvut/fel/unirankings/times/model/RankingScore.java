package cz.cvut.fel.unirankings.times.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

/** Class represents university's ranking score according to timeshighereducation.com. */
@Entity(name = "theRankingScore")
@Table(name = "ranking_score", schema = "the")
public class RankingScore implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  /** Order of university ranking score in the list. */
  @Column(name = "original_order_idx")
  private Integer originalOrderIdx;

  /** Overall score. */
  @Column(name = "overall_score")
  private String overallScore;

  /** Teaching score. */
  @Column(name = "teaching_score")
  private String teachingScore;

  /** Research score. */
  @Column(name = "research_score")
  private String researchScore;

  /** Citations score. */
  @Column(name = "citations_score")
  private String citationsScore;

  /** Industry income score. */
  @Column(name = "industry_income_score")
  private String industryIncomeScore;

  /** International outlook score. */
  @Column(name = "international_outlook_score")
  private String internationalOutlookScore;

  /** Ranking score year. */
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
