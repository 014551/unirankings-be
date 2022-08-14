package cz.cvut.fel.unirankings.common.extractor;

import java.util.List;

/**
 * Defines interface for extractor of university rankings list.
 * @param <T> university ranking object definition
 */
public interface ListExtractor<T> {

  /**
   * Extracts a list of university rankings for the specified year.
   *
   * @param year of the university rankings
   * @return list of university rankings
   * @throws Exception in case extraction is not successful
   */
  List<T> extract(String year) throws Exception;
}
