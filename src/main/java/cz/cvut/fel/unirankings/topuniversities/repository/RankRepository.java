package cz.cvut.fel.unirankings.topuniversities.repository;

import cz.cvut.fel.unirankings.topuniversities.model.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qsRankRepository")
public interface RankRepository extends JpaRepository<Rank, Long> {

  @Query("SELECT DISTINCT (r.year) from qsRank r")
  List<String> findAvailableRankYears();
}
