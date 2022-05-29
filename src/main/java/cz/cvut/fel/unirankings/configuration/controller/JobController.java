package cz.cvut.fel.unirankings.configuration.controller;

import cz.cvut.fel.unirankings.configuration.model.Job;
import cz.cvut.fel.unirankings.configuration.model.JobRequest;
import cz.cvut.fel.unirankings.configuration.service.JobService;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.InvalidArgumentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/jobs")
@CrossOrigin
public class JobController {

  private final JobService jobService;

  public JobController(JobService jobService) {
    this.jobService = jobService;
  }

  @GetMapping
  public ResponseEntity<List<Job>> getAllJobs() {
    return ResponseEntity.ok(jobService.getAllJobs());
  }

  @PostMapping
  @ResponseStatus(value = HttpStatus.CREATED)
  public void runNewJob(@RequestBody JobRequest jobRequest) {
    if (StringUtils.isBlank(jobRequest.getYear())) {
      throw new InvalidArgumentException("year value is required");
    }
    jobService.runNewJob(jobRequest);
  }
}
