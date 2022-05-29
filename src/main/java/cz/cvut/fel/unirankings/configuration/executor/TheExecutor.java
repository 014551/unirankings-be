package cz.cvut.fel.unirankings.configuration.executor;

import cz.cvut.fel.unirankings.common.extractor.ListExtractor;
import cz.cvut.fel.unirankings.configuration.model.RankingIdentifier;
import cz.cvut.fel.unirankings.configuration.model.JobRequest;
import cz.cvut.fel.unirankings.times.mapper.ParsingModelMapper;
import cz.cvut.fel.unirankings.times.model.University;
import cz.cvut.fel.unirankings.times.model.parser.UniversityRankingScore;
import cz.cvut.fel.unirankings.times.writer.UniversityWriter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheExecutor implements Executor {

  private final ListExtractor<UniversityRankingScore> extractor;
  private final UniversityWriter writer;
  private final ParsingModelMapper mapper;

  public TheExecutor(
      ListExtractor<UniversityRankingScore> extractor,
      UniversityWriter writer,
      ParsingModelMapper mapper) {
    this.extractor = extractor;
    this.writer = writer;
    this.mapper = mapper;
  }

  @Override
  public void execute(JobRequest jobRequest) throws Exception {
    List<UniversityRankingScore> universityRankingScores = extractor.extract(jobRequest.getYear());
    List<University> universities =
        mapper.mapToUniversities(universityRankingScores, jobRequest.getYear());
    writer.write(universities);
  }

  @Override
  public boolean isCorrectExecutor(RankingIdentifier rankingIdentifier) {
    return rankingIdentifier == RankingIdentifier.TIMES_HIGHER_EDUCATION;
  }
}
