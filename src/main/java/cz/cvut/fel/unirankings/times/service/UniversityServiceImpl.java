package cz.cvut.fel.unirankings.times.service;

import cz.cvut.fel.unirankings.times.model.University;
import cz.cvut.fel.unirankings.times.repository.UniversityRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("theUniversityService")
public class UniversityServiceImpl implements UniversityService {

  private final UniversityRepository repository;

  public UniversityServiceImpl(UniversityRepository repository) {
    this.repository = repository;
  }

  @Cacheable(value = "TheUniversity")
  public List<University> findUniversities(String year, String country, String name) {
    return repository.findUniversities(year, country, name);
  }

  public List<University> findUniversities() {
    return repository.findAll();
  }

  public University findUniversity(Long id) {
    return repository.findById(id).orElse(null);
  }
}
