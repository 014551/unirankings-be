package cz.cvut.fel.unirankings.configuration.service;

import cz.cvut.fel.unirankings.configuration.model.Job;
import cz.cvut.fel.unirankings.configuration.model.JobRequest;

import java.util.List;

public interface JobService {

  List<Job> getAllJobs();

  Job getLatestSuccessfulJob(JobRequest jobRequest);

  void saveSuccessfulCachedJob(JobRequest jobRequest);

  void saveSuccessfulJob(JobRequest jobRequest);

  void saveFailedJob(JobRequest jobRequest, String errorMessage);

  void runNewJob(JobRequest jobRequest);
}
