package cz.cvut.fel.unirankings.times.service;

import cz.cvut.fel.unirankings.times.model.University;

import java.util.List;

public interface UniversityService {

  List<University> findUniversities(String year, String country, String name);

  List<University> findUniversities();

  University findUniversity(Long id);
}
