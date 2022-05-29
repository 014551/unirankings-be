package cz.cvut.fel.unirankings.common.extractor;

import java.util.List;

public interface ListExtractor<T> {

  List<T> extract(String year) throws Exception;
}
