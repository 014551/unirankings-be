package cz.cvut.fel.unirankings.configuration.executor;

import cz.cvut.fel.unirankings.configuration.model.RankingIdentifier;
import cz.cvut.fel.unirankings.configuration.model.JobRequest;

public interface Executor {

  void execute(JobRequest jobRequest) throws Exception;

  boolean isCorrectExecutor(RankingIdentifier rankingIdentifier);
}
