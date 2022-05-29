package cz.cvut.fel.unirankings.configuration.service;

import cz.cvut.fel.unirankings.configuration.executor.Executor;
import cz.cvut.fel.unirankings.configuration.model.Job;
import cz.cvut.fel.unirankings.configuration.model.JobProcessingType;
import cz.cvut.fel.unirankings.configuration.model.JobRequest;
import cz.cvut.fel.unirankings.configuration.model.JobResult;
import cz.cvut.fel.unirankings.configuration.repository.JobRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

  private static final Logger LOGGER = LoggerFactory.getLogger(JobServiceImpl.class);

  private final List<Executor> executors;
  private final JobRepository jobRepository;
  private final Clock clock;

  public JobServiceImpl(List<Executor> executors, JobRepository jobRepository, Clock clock) {
    this.executors = executors;
    this.jobRepository = jobRepository;
    this.clock = clock;
  }

  @Override
  public List<Job> getAllJobs() {
    return jobRepository.findAll();
  }

  @Override
  public Job getLatestSuccessfulJob(JobRequest jobRequest) {
    if (jobRequest.getRankingIdentifier() == null) {
      throw new IllegalArgumentException("Ranking identifier was not provided");
    }
    if (jobRequest.getYear() == null) {
      throw new IllegalArgumentException("Ranking year was not provided");
    }

    return jobRepository.findFirstByRankingIdentifierAndRankingYearAndResult(
        jobRequest.getRankingIdentifier(),
        jobRequest.getYear(),
        JobResult.SUCCESS,
        Sort.by(Sort.Direction.ASC, "processingDate"));
  }

  @Override
  public void saveSuccessfulCachedJob(JobRequest jobRequest) {
    saveJob(jobRequest, JobProcessingType.CACHE);
  }

  @Override
  public void saveSuccessfulJob(JobRequest jobRequest) {
    saveJob(jobRequest, JobProcessingType.FULL);
  }

  private void saveJob(JobRequest jobRequest, JobProcessingType jobProcessingType) {
    jobRepository.save(
        new Job(
            jobRequest.getRankingIdentifier(),
            jobRequest.getYear(),
            LocalDateTime.now(clock),
            JobResult.SUCCESS,
            jobProcessingType,
            null));
  }

  @Override
  public void saveFailedJob(JobRequest jobRequest, String errorMessage) {
    jobRepository.save(
        new Job(
            jobRequest.getRankingIdentifier(),
            jobRequest.getYear(),
            LocalDateTime.now(clock),
            JobResult.FAIL,
            JobProcessingType.FULL,
            StringUtils.left(errorMessage, Job.MAX_ERROR_LENGTH)));
  }

  @Override
  public void runNewJob(JobRequest jobRequest) {
    LOGGER.info(
        "Processing started with the following arguments: %s %s"
            .formatted(jobRequest.getRankingIdentifier(), jobRequest.getYear()));

    Job successfulJob;
    try {
      successfulJob = getLatestSuccessfulJob(jobRequest);
    } catch (IllegalArgumentException e) {
      LOGGER.info("Getting latest successful job failed", e);
      saveFailedJob(jobRequest, e.getMessage());
      return;
    }

    if (successfulJob != null) {
      LOGGER.info(
          "Successful job found for given user input with processing date = {} and processing type = {}",
          successfulJob.getProcessingDate(),
          successfulJob.getProcessingType());
      saveSuccessfulCachedJob(jobRequest);
      return;
    }

    try {
      execute(jobRequest);
    } catch (Exception e) {
      LOGGER.error("Execution for given user input failed.", e);
      saveFailedJob(jobRequest, e.getMessage());
      return;
    }

    saveSuccessfulJob(jobRequest);

    LOGGER.info("Processing finished.");
  }

  private void execute(JobRequest jobRequest) throws Exception {
    Executor executor =
        executors.stream()
            .filter(exec -> exec.isCorrectExecutor(jobRequest.getRankingIdentifier()))
            .findFirst()
            .orElseThrow();

    executor.execute(jobRequest);
  }
}
