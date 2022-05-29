package cz.cvut.fel.unirankings.topuniversities.writer;

import cz.cvut.fel.unirankings.topuniversities.model.Rank;
import cz.cvut.fel.unirankings.topuniversities.model.RankingIndicator;
import cz.cvut.fel.unirankings.topuniversities.model.University;
import cz.cvut.fel.unirankings.topuniversities.repository.UniversityRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

@Service("qsDbWriter")
public class DbWriter implements UniversityWriter {

  private UniversityRepository repository;

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

          Set<RankingIndicator> rankingIndicators = university.getRankingIndicators();
          rankingIndicators.forEach(dbUniversity::addRankingIndicator);

          repository.save(dbUniversity);
        });
  }
}
