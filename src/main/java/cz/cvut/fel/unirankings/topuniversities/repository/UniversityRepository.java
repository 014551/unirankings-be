package cz.cvut.fel.unirankings.topuniversities.repository;

import cz.cvut.fel.unirankings.topuniversities.model.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("qsUniversityRepository")
public interface UniversityRepository extends JpaRepository<University, Long> {

  University findFirstByNameAndCountry(String name, String country);

  @Query(
      "SELECT u "
          + "FROM qsUniversity u "
          + "JOIN FETCH u.ranks r "
          + "JOIN FETCH u.rankingIndicators ri "
          + "WHERE 1=1 "
          + "AND r.year = ri.year "
          + "AND (:year IS NULL OR r.year = :year) "
          + "AND (:country IS NULL OR u.country = :country) "
          + "AND (:name IS NULL OR u.name LIKE '%:name%')")
  List<University> findUniversities(
      @Param("year") String year, @Param("country") String country, @Param("name") String name);
}
