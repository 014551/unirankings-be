package cz.cvut.fel.unirankings.topuniversities.service;

import cz.cvut.fel.unirankings.topuniversities.model.University;

import java.util.List;

public interface UniversityService {

  List<University> findUniversities(String year, String country, String name);

  University findUniversity(Long id);
}
