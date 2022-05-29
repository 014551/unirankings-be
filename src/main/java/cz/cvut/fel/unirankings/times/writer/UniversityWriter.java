package cz.cvut.fel.unirankings.times.writer;

import cz.cvut.fel.unirankings.times.model.University;

import java.util.List;

public interface UniversityWriter {

  void write(List<University> rankingIndicators);
}
