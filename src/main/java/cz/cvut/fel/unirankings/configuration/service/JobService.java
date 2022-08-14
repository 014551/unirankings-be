package cz.cvut.fel.unirankings.configuration.service;

import cz.cvut.fel.unirankings.configuration.model.Job;
import cz.cvut.fel.unirankings.configuration.model.JobRequest;

import java.util.List;

/** Service for managing processing jobs. */
public interface JobService {

  /**
   * Provides all jobs stored in the store.
   *
   * @return jobs from the store
   */
  List<Job> getAllJobs();

  /**
   * Provides the last successfully executed job based on the input job request.
   *
   * @param jobRequest job request to filter executed jobs
   * @return last successfully executed job
   */
  Job getLatestSuccessfulJob(JobRequest jobRequest);

  /**
   * Saves the successfully executed job that operated in the CACHE mode.
   *
   * @param jobRequest job request
   */
  void saveSuccessfulCachedJob(JobRequest jobRequest);

  /**
   * Saves the successfully executed job.
   *
   * @param jobRequest job request
   */
  void saveSuccessfulJob(JobRequest jobRequest);

  /**
   * Saves the failed job.
   *
   * @param jobRequest job request
   */
  void saveFailedJob(JobRequest jobRequest, String errorMessage);

  /**
   * Runs new job.
   *
   * @param jobRequest job request
   */
  void runNewJob(JobRequest jobRequest);
}
