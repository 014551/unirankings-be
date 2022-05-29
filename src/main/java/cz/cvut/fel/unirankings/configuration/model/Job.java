package cz.cvut.fel.unirankings.configuration.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "job")
public class Job {

  public static final int MAX_ERROR_LENGTH = 512;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "ranking_identifier")
  private RankingIdentifier rankingIdentifier;

  @Column(name = "ranking_year")
  private String rankingYear;

  @Column(columnDefinition = "TIMESTAMP", name = "processing_date")
  private LocalDateTime processingDate;

  @Column(name = "processing_result")
  private JobResult result;

  @Column(name = "processing_type")
  private JobProcessingType processingType;

  @Column(name = "error", length = MAX_ERROR_LENGTH)
  private String error;

  public Job() {}

  public Job(
      RankingIdentifier rankingIdentifier,
      String rankingYear,
      LocalDateTime processingDate,
      JobResult result,
      JobProcessingType processingType,
      String error) {
    this.rankingIdentifier = rankingIdentifier;
    this.rankingYear = rankingYear;
    this.processingDate = processingDate;
    this.result = result;
    this.processingType = processingType;
    this.error = error;
  }

  public long getId() {
    return id;
  }

  public RankingIdentifier getRankingIdentifier() {
    return rankingIdentifier;
  }

  public String getRankingYear() {
    return rankingYear;
  }

  public LocalDateTime getProcessingDate() {
    return processingDate;
  }

  public JobResult getResult() {
    return result;
  }

  public JobProcessingType getProcessingType() {
    return processingType;
  }

  public String getError() {
    return error;
  }
}
