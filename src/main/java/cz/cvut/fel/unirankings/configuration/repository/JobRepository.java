package cz.cvut.fel.unirankings.configuration.repository;

import cz.cvut.fel.unirankings.configuration.model.Job;
import cz.cvut.fel.unirankings.configuration.model.JobResult;
import cz.cvut.fel.unirankings.configuration.model.RankingIdentifier;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

/** Repository for jobs. */
public interface JobRepository extends JpaRepository<Job, Long> {

  Job findFirstByRankingIdentifierAndRankingYearAndResult(
      RankingIdentifier rankingIdentifier, String rankingYear, JobResult result, Sort sorting);
}
