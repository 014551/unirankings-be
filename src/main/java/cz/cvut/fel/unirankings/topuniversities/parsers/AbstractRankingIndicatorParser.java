package cz.cvut.fel.unirankings.topuniversities.parsers;

import cz.cvut.fel.unirankings.topuniversities.model.parser.UniversityRankingIndicator;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.stream.Collectors;

/** Class that contains the core logic of the document parsing. */
public abstract class AbstractRankingIndicatorParser implements RankingIndicatorParser {

  private static final String WRAP_IN_CSS_SELECTOR = ".td-wrap-in";

  public List<UniversityRankingIndicator> parse(Document document) throws Exception {
    if (document == null) {
      throw new Exception("Provided document is empty.");
    }
    return parseRankingIndicators(document);
  }

  private List<UniversityRankingIndicator> parseRankingIndicators(Document document) {
    Elements rankingIndicatorsTable = document.select("#ranking-data-load_ind");

    Elements indRowTags = rankingIndicatorsTable.select("div.row.ind-row");

    return indRowTags.stream().map(this::parseRankingIndicatorTag).collect(Collectors.toList());
  }

  protected UniversityRankingIndicator parseRankingIndicatorTag(Element rankingIndicatorTag) {
    String rank = getRank(rankingIndicatorTag);
    String name = getName(rankingIndicatorTag);
    String detailLink = getDetailLink(rankingIndicatorTag);
    String city = getCity(rankingIndicatorTag);
    String country = getCountry(rankingIndicatorTag);
    String overallScore = getOverallScore(rankingIndicatorTag);
    String internationalStudentsRatio = getInternationalStudentsRatio(rankingIndicatorTag);
    String internationalFacultyRatio = getInternationalFacultyRatio(rankingIndicatorTag);
    String facultyStudentRatio = getFacultyStudentRatio(rankingIndicatorTag);
    String citationPerFaculty = getCitationsPerFaculty(rankingIndicatorTag);
    String academicReputation = getAcademicReputation(rankingIndicatorTag);
    String employerReputation = getEmployerReputation(rankingIndicatorTag);

    return new UniversityRankingIndicator(
        rank,
        name,
        detailLink,
        city,
        country,
        overallScore,
        internationalStudentsRatio,
        internationalFacultyRatio,
        facultyStudentRatio,
        citationPerFaculty,
        academicReputation,
        employerReputation);
  }

  /**
   * Wraps {@link Element#text()} with {@code null} check.
   *
   * @param tag tag to get text
   * @return the value of {@link Element#text()} if the element is not null
   */
  private String getString(Element tag) {
    if (tag != null) {
      return tag.text();
    }
    return null;
  }

  /**
   * Searches for the first {@link AbstractRankingIndicatorParser#WRAP_IN_CSS_SELECTOR} and returns
   * its text content.
   *
   * @param tag tag that used as a base tag for searching
   * @return {@link Element#text()} value of the tag if found, null otherwise
   */
  private String getWrappedInString(Element tag) {
    if (tag != null) {
      Element tdWrapInTag = tag.selectFirst(WRAP_IN_CSS_SELECTOR);
      return getString(tdWrapInTag);
    }
    return null;
  }

  private String getRank(Element indRowTag) {
    Element uniRankTag = indRowTag.selectFirst("._univ-rank");
    return getString(uniRankTag);
  }

  private String getName(Element indRowTag) {
    Element uniLinkTag = indRowTag.selectFirst(".uni-link");
    return getString(uniLinkTag);
  }

  private String getDetailLink(Element indRowTag) {
    Element uniLinkTag = indRowTag.selectFirst(".uni-link");
    if (uniLinkTag != null) {
      return uniLinkTag.attr("href");
    }
    return null;
  }

  private String getUniLocation(Element indRowTag) {
    Elements uniLocationTags = indRowTag.select(".location");
    return uniLocationTags.text();
  }

  private String getCity(Element indRowTag) {
    String location = getUniLocation(indRowTag);
    String[] cityCountry = location.split(",");
    if (cityCountry.length > 0) {
      return cityCountry[0];
    }
    return null;
  }

  private String getCountry(Element indRowTag) {
    String location = getUniLocation(indRowTag);
    String[] cityCountry = location.split(",");
    if (cityCountry.length > 1) {
      return cityCountry[1];
    }
    return null;
  }

  private String getOverallScore(Element indRowTag) {
    Element overallScoreTag = indRowTag.selectFirst(".overall-score-span-ind.overall");
    return getWrappedInString(overallScoreTag);
  }

  private String getInternationalStudentsRatio(Element indRowTag) {
    Element internationalStudentsRatioTag =
        indRowTag.selectFirst(getInternationalStudentsRatioCssQuery());
    return getWrappedInString(internationalStudentsRatioTag);
  }

  protected abstract String getInternationalStudentsRatioCssQuery();

  private String getInternationalFacultyRatio(Element indRowTag) {
    Element internationalFacultyRatioTag =
        indRowTag.selectFirst(getInternationalFacultyRatioCssQuery());
    return getWrappedInString(internationalFacultyRatioTag);
  }

  protected abstract String getInternationalFacultyRatioCssQuery();

  private String getFacultyStudentRatio(Element indRowTag) {
    Element facultyStudentRatioTag = indRowTag.selectFirst(getFacultyStudentRatioCssQuery());
    return getWrappedInString(facultyStudentRatioTag);
  }

  protected abstract String getFacultyStudentRatioCssQuery();

  private String getCitationsPerFaculty(Element indRowTag) {
    Element citationsPerFacultyTag = indRowTag.selectFirst(getCitationsPerFacultyCssQuery());
    return getWrappedInString(citationsPerFacultyTag);
  }

  protected abstract String getCitationsPerFacultyCssQuery();

  private String getAcademicReputation(Element indRowTag) {
    Element academicReputationTag = indRowTag.selectFirst(getAcademicReputationCssQuery());
    return getWrappedInString(academicReputationTag);
  }

  protected abstract String getAcademicReputationCssQuery();

  private String getEmployerReputation(Element indRowTag) {
    Element employerReputationTag = indRowTag.selectFirst(getEmployerReputationCssQuery());
    return getWrappedInString(employerReputationTag);
  }

  protected abstract String getEmployerReputationCssQuery();
}
