package cz.cvut.fel.unirankings.topuniversities.parsers;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.enums.CSVReaderNullFieldIndicator;
import com.opencsv.exceptions.CsvException;
import cz.cvut.fel.unirankings.topuniversities.model.parser.UniversityRankingIndicator;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public abstract class AbstractRankingIndicatorParserTest {

  private static final Logger LOGGER =
      LoggerFactory.getLogger(AbstractRankingIndicatorParserTest.class);

  protected String getHtmlFromResources(String fileName) throws IOException {
    File file = new File(getResourcePath() + "/" + fileName);
    return FileUtils.readFileToString(file, StandardCharsets.UTF_8);
  }

  protected List<UniversityRankingIndicator> getExpectedUniversities(String fileName)
      throws IOException, CsvException {
    File file = new File(getResourcePath() + "/" + fileName);

    CSVParser csvParser =
        new CSVParserBuilder()
            .withSeparator(';')
            .withFieldAsNull(CSVReaderNullFieldIndicator.EMPTY_SEPARATORS)
            .build();
    try (CSVReader reader =
        new CSVReaderBuilder(new FileReader(file.getAbsolutePath()))
            .withCSVParser(csvParser)
            .build()) {
      return reader.readAll().stream()
          .map(
              line -> {
                LOGGER.debug(
                    "Processing the following CSV line with expected university:\n{}",
                    Arrays.toString(line));
                return getExpectedUniversity(line);
              })
          .collect(Collectors.toList());
    }
  }

  protected void assertUniversityRankingIndicatorsEqual(
      List<UniversityRankingIndicator> expected, List<UniversityRankingIndicator> actual) {
    Assertions.assertNotNull(actual);
    Assertions.assertEquals(expected.size(), actual.size());

    for (int i = 0; i < expected.size(); i++) {
      LOGGER.debug("Asserting university = {}", expected.get(i).getName());
      Assertions.assertEquals(expected.get(i), actual.get(i));
    }
  }

  protected abstract UniversityRankingIndicator getExpectedUniversity(String[] line);

  protected abstract String getResourcePath();
}
