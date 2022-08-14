package cz.cvut.fel.unirankings.times.service;

import cz.cvut.fel.unirankings.times.model.University;

import java.util.List;

/** Service to work with universities. */
public interface UniversityService {

  /**
   * Provides universities based on the input params.
   *
   * @param year ranking year
   * @param country university country
   * @param name university name
   * @return universities
   */
  List<University> findUniversities(String year, String country, String name);

  /**
   * Provides all universities available on the store.
   *
   * @return all universities from the store
   */
  List<University> findUniversities();

  /**
   * Provides university based on its id.
   *
   * @param id university id
   * @return university
   */
  University findUniversity(Long id);
}
