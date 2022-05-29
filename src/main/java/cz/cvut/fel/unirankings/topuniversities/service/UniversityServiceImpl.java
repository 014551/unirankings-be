package cz.cvut.fel.unirankings.topuniversities.service;

import cz.cvut.fel.unirankings.topuniversities.model.University;
import cz.cvut.fel.unirankings.topuniversities.repository.UniversityRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("qsUniversityService")
public class UniversityServiceImpl implements UniversityService {

  private final UniversityRepository repository;

  public UniversityServiceImpl(UniversityRepository repository) {
    this.repository = repository;
  }

  @Cacheable(value = "QsUniversity")
  public List<University> findUniversities(String year, String country, String name) {
    return repository.findUniversities(year, country, name);
  }

  public University findUniversity(Long id) {
    return repository.findById(id).orElse(null);
  }
}
