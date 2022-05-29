package cz.cvut.fel.unirankings.times.writer;

import cz.cvut.fel.unirankings.times.model.Rank;
import cz.cvut.fel.unirankings.times.model.RankingScore;
import cz.cvut.fel.unirankings.times.model.University;
import cz.cvut.fel.unirankings.times.repository.UniversityRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service("theDbWriter")
public class DbWriter implements UniversityWriter {

  private final UniversityRepository repository;

  public DbWriter(UniversityRepository repository) {
    this.repository = repository;
  }

  @Override
  @Transactional
  public void write(List<University> universities) {
    if (universities == null || universities.isEmpty()) {
      return;
    }

    universities.forEach(
        university -> {
          University dbUniversity =
              repository.findFirstByNameAndCountry(university.getName(), university.getCountry());

          if (dbUniversity == null) {
            repository.save(university);
            return;
          }

          Set<Rank> ranks = university.getRanks();
          ranks.forEach(dbUniversity::addRank);

          Set<RankingScore> rankingScores = university.getRankingScores();
          rankingScores.forEach(dbUniversity::addRankingScore);

          repository.save(dbUniversity);
        });
  }
}
