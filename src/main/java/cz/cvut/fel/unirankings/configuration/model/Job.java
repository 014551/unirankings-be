package cz.cvut.fel.unirankings.configuration.model;

import javax.persistence.*;
import java.time.LocalDateTime;

/** Class represents a job for running extraction process. */
@Entity
@Table(name = "job")
public class Job {

  public static final int MAX_ERROR_LENGTH = 512;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  /** Ranking identifier for the extraction. */
  @Column(name = "ranking_identifier")
  private RankingIdentifier rankingIdentifier;

  /** Ranking year. */
  @Column(name = "ranking_year")
  private String rankingYear;

  /** Timestamp when processing of the job is performed. */
  @Column(columnDefinition = "TIMESTAMP", name = "processing_date")
  private LocalDateTime processingDate;

  /** Job processing indicator. */
  @Column(name = "processing_result")
  private JobResult result;

  /** Job processing type.  */
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
