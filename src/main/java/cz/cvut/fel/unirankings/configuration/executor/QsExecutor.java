package cz.cvut.fel.unirankings.configuration.executor;

import cz.cvut.fel.unirankings.common.extractor.ListExtractor;
import cz.cvut.fel.unirankings.configuration.model.RankingIdentifier;
import cz.cvut.fel.unirankings.configuration.model.JobRequest;
import cz.cvut.fel.unirankings.topuniversities.mapper.ParsingModelMapper;
import cz.cvut.fel.unirankings.topuniversities.model.University;
import cz.cvut.fel.unirankings.topuniversities.model.parser.UniversityRankingIndicator;
import cz.cvut.fel.unirankings.topuniversities.writer.UniversityWriter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QsExecutor implements Executor {

  private final UniversityWriter writer;
  private final ListExtractor<UniversityRankingIndicator> listExtractor;
  private final ParsingModelMapper mapper;

  public QsExecutor(
      UniversityWriter writer,
      ListExtractor<UniversityRankingIndicator> listExtractor,
      ParsingModelMapper mapper) {
    this.writer = writer;
    this.listExtractor = listExtractor;
    this.mapper = mapper;
  }

  @Override
  public void execute(JobRequest jobRequest) throws Exception {
    List<UniversityRankingIndicator> universityRankingIndicators =
        listExtractor.extract(jobRequest.getYear());
    List<University> universities =
        mapper.mapToUniversities(universityRankingIndicators, jobRequest.getYear());
    writer.write(universities);
  }

  @Override
  public boolean isCorrectExecutor(RankingIdentifier rankingIdentifier) {
    return rankingIdentifier == RankingIdentifier.QS_WORLD_UNIVERSITY_RANKINGS;
  }
}
