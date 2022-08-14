package cz.cvut.fel.unirankings.topuniversities.service;

import cz.cvut.fel.unirankings.topuniversities.model.University;

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
   * Provides university based on its id.
   *
   * @param id university id
   * @return university
   */
  University findUniversity(Long id);
}
