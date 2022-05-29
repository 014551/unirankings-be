package cz.cvut.fel.unirankings.configuration.service;

import cz.cvut.fel.unirankings.configuration.executor.Executor;
import cz.cvut.fel.unirankings.configuration.model.*;
import cz.cvut.fel.unirankings.configuration.repository.JobRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import java.time.Clock;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

class JobServiceImplTest {
  private static final LocalDateTime LOCAL_DATE = LocalDateTime.of(2021, 1, 13, 13, 13, 13);

  private JobService jobService;

  @Mock private JobRepository jobRepository;
  @Mock private Clock clock;
  @Mock private List<Executor> executors;

  private AutoCloseable closeable;

  @BeforeEach
  public void setup() {
    closeable = MockitoAnnotations.openMocks(this);
    Clock fixedClock =
        Clock.fixed(LOCAL_DATE.atZone(ZoneId.systemDefault()).toInstant(), ZoneId.systemDefault());
    doReturn(fixedClock.instant()).when(clock).instant();
    doReturn(fixedClock.getZone()).when(clock).getZone();
    jobService = new JobServiceImpl(executors, jobRepository, clock);
  }

  @AfterEach
  void closeService() throws Exception {
    closeable.close();
  }

  @Test
  public void test_ranking_identifier_validation_when_get_successful_job() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () -> jobService.getLatestSuccessfulJob(new JobRequest(null, null)));
  }

  @Test
  public void test_year_validation_when_get_successful_job() {
    Assertions.assertThrows(
        IllegalArgumentException.class,
        () ->
            jobService.getLatestSuccessfulJob(
                new JobRequest(RankingIdentifier.QS_WORLD_UNIVERSITY_RANKINGS, null)));
  }

  @Test
  public void test_get_successful_job() {
    RankingIdentifier rankingIdentifier = RankingIdentifier.TIMES_HIGHER_EDUCATION;
    String year = "2018";
    jobService.getLatestSuccessfulJob(new JobRequest(rankingIdentifier, year));

    verify(jobRepository)
        .findFirstByRankingIdentifierAndRankingYearAndResult(
            rankingIdentifier,
            year,
            JobResult.SUCCESS,
            Sort.by(Sort.Direction.ASC, "processingDate"));
  }

  @Test
  public void test_save_successful_cached_job() {
    JobRequest jobRequest = new JobRequest(RankingIdentifier.TIMES_HIGHER_EDUCATION, "2016");

    jobService.saveSuccessfulCachedJob(jobRequest);

    ArgumentCaptor<Job> argument = ArgumentCaptor.forClass(Job.class);
    verify(jobRepository).save(argument.capture());
    assertEquals(jobRequest.getRankingIdentifier(), argument.getValue().getRankingIdentifier());
    assertEquals(jobRequest.getYear(), argument.getValue().getRankingYear());
    assertEquals(LOCAL_DATE, argument.getValue().getProcessingDate());
    assertEquals(JobResult.SUCCESS, argument.getValue().getResult());
    assertEquals(JobProcessingType.CACHE, argument.getValue().getProcessingType());
    assertNull(argument.getValue().getError());
  }

  @Test
  public void test_save_successful_job() {
    JobRequest jobRequest = new JobRequest(RankingIdentifier.QS_WORLD_UNIVERSITY_RANKINGS, "2022");

    jobService.saveSuccessfulJob(jobRequest);

    ArgumentCaptor<Job> argument = ArgumentCaptor.forClass(Job.class);
    verify(jobRepository).save(argument.capture());
    assertEquals(jobRequest.getRankingIdentifier(), argument.getValue().getRankingIdentifier());
    assertEquals(jobRequest.getYear(), argument.getValue().getRankingYear());
    assertEquals(LOCAL_DATE, argument.getValue().getProcessingDate());
    assertEquals(JobResult.SUCCESS, argument.getValue().getResult());
    assertEquals(JobProcessingType.FULL, argument.getValue().getProcessingType());
    assertNull(argument.getValue().getError());
  }

  @Test
  public void test_save_failed_job_short_error_message() {
    JobRequest jobRequest = new JobRequest(RankingIdentifier.QS_WORLD_UNIVERSITY_RANKINGS, "2022");

    String shortErrorMessage = "short_error_message";
    jobService.saveFailedJob(jobRequest, shortErrorMessage);

    ArgumentCaptor<Job> argument = ArgumentCaptor.forClass(Job.class);
    verify(jobRepository).save(argument.capture());
    assertEquals(jobRequest.getRankingIdentifier(), argument.getValue().getRankingIdentifier());
    assertEquals(jobRequest.getYear(), argument.getValue().getRankingYear());
    assertEquals(LOCAL_DATE, argument.getValue().getProcessingDate());
    assertEquals(JobResult.FAIL, argument.getValue().getResult());
    assertEquals(JobProcessingType.FULL, argument.getValue().getProcessingType());
    assertEquals(shortErrorMessage, argument.getValue().getError());
  }

  @Test
  public void test_save_failed_job_long_error_message() {
    JobRequest jobRequest = new JobRequest(RankingIdentifier.QS_WORLD_UNIVERSITY_RANKINGS, "2022");

    String actualErrorMessage = "a".repeat(Job.MAX_ERROR_LENGTH + 1);
    String expectedErrorMessage = "a".repeat(Job.MAX_ERROR_LENGTH);

    jobService.saveFailedJob(jobRequest, actualErrorMessage);

    ArgumentCaptor<Job> argument = ArgumentCaptor.forClass(Job.class);
    verify(jobRepository).save(argument.capture());
    assertEquals(jobRequest.getRankingIdentifier(), argument.getValue().getRankingIdentifier());
    assertEquals(jobRequest.getYear(), argument.getValue().getRankingYear());
    assertEquals(LOCAL_DATE, argument.getValue().getProcessingDate());
    assertEquals(JobResult.FAIL, argument.getValue().getResult());
    assertEquals(JobProcessingType.FULL, argument.getValue().getProcessingType());
    assertEquals(expectedErrorMessage, argument.getValue().getError());
  }
}
