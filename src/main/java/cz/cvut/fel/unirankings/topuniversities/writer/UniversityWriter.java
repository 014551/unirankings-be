package cz.cvut.fel.unirankings.topuniversities.writer;

import cz.cvut.fel.unirankings.topuniversities.model.University;

import java.util.List;

public interface UniversityWriter {

  void write(List<University> rankingIndicators);
}
