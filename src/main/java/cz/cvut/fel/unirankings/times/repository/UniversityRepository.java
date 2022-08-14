package cz.cvut.fel.unirankings.times.repository;

import cz.cvut.fel.unirankings.times.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/** Repository for universities. */
@Repository("theUniversityRepository")
public interface UniversityRepository extends JpaRepository<University, Long> {

  University findFirstByNameAndCountry(String name, String country);

  /**
   * Retrieves university with ranks and ranking scores for the specified year, country and name. If
   * any of the parameters is not specified, then it is omitted.
   *
   * @param year ranking year
   * @param country university country
   * @param name university name or part of the name
   * @return universities with ranks and ranking scores
   */
  @Query(
      "SELECT u "
          + "FROM theUniversity u "
          + "JOIN FETCH u.ranks r "
          + "JOIN FETCH u.rankingScores rs "
          + "WHERE 1=1 "
          + "AND r.year = rs.year "
          + "AND (:year IS NULL OR r.year = :year) "
          + "AND (:country IS NULL OR u.country = :country) "
          + "AND (:name IS NULL OR u.name LIKE '%:name%')")
  List<University> findUniversities(
      @Param("year") String year, @Param("country") String country, @Param("name") String name);
}
