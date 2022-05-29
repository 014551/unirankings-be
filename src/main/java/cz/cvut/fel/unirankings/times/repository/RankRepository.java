package cz.cvut.fel.unirankings.times.repository;

import cz.cvut.fel.unirankings.times.model.Rank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("theRankRepository")
public interface RankRepository extends JpaRepository<Rank, Long> {

  @Query("SELECT DISTINCT (r.year) from theRank r")
  List<String> findAvailableRankYears();
}
