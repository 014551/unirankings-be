package cz.cvut.fel.unirankings.topuniversities.writer;

import cz.cvut.fel.unirankings.topuniversities.model.University;

import java.util.List;

/** Defines interface for saving universities to the store. */
public interface UniversityWriter {

  /**
   * Saves universities to the store.
   *
   * @param rankingIndicators universities
   */
  void write(List<University> rankingIndicators);
}
