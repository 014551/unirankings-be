package cz.cvut.fel.unirankings.topuniversities.model.parser;

import java.util.Objects;

public class UniversityRankingIndicator {

  private final Integer originalRowElementIdx;

  private final String name;

  private final String detailLinkPath;

  private final String city;

  private final String country;

  private final String rank;

  private final String overallScore;

  private final String internationalStudentsRatio;

  private final String internationalFacultyRatio;

  private final String facultyStudentRatio;

  private final String citationPerFaculty;

  private final String academicReputation;

  private final String employerReputation;

  public UniversityRankingIndicator(
      Integer originalRowElementIdx,
      String rank,
      String name,
      String detailLinkPath,
      String city,
      String country,
      String overallScore,
      String internationalStudentsRatio,
      String internationalFacultyRatio,
      String facultyStudentRatio,
      String citationPerFaculty,
      String academicReputation,
      String employerReputation) {
    this.originalRowElementIdx = originalRowElementIdx;
    this.name = name;
    this.detailLinkPath = detailLinkPath;
    this.city = city;
    this.country = country;
    this.rank = rank;
    this.overallScore = overallScore;
    this.internationalStudentsRatio = internationalStudentsRatio;
    this.internationalFacultyRatio = internationalFacultyRatio;
    this.facultyStudentRatio = facultyStudentRatio;
    this.citationPerFaculty = citationPerFaculty;
    this.academicReputation = academicReputation;
    this.employerReputation = employerReputation;
  }

  public UniversityRankingIndicator(
      String rank,
      String name,
      String detailLinkPath,
      String city,
      String country,
      String overallScore,
      String internationalStudentsRatio,
      String internationalFacultyRatio,
      String facultyStudentRatio,
      String citationPerFaculty,
      String academicReputation,
      String employerReputation) {
    this.originalRowElementIdx = null;
    this.name = name;
    this.detailLinkPath = detailLinkPath;
    this.city = city;
    this.country = country;
    this.rank = rank;
    this.overallScore = overallScore;
    this.internationalStudentsRatio = internationalStudentsRatio;
    this.internationalFacultyRatio = internationalFacultyRatio;
    this.facultyStudentRatio = facultyStudentRatio;
    this.citationPerFaculty = citationPerFaculty;
    this.academicReputation = academicReputation;
    this.employerReputation = employerReputation;
  }

  public UniversityRankingIndicator(int originalRowElementIdx, UniversityRankingIndicator other) {
    this.originalRowElementIdx = originalRowElementIdx;
    this.name = other.name;
    this.detailLinkPath = other.detailLinkPath;
    this.city = other.city;
    this.country = other.country;
    this.rank = other.rank;
    this.overallScore = other.overallScore;
    this.internationalStudentsRatio = other.internationalStudentsRatio;
    this.internationalFacultyRatio = other.internationalFacultyRatio;
    this.facultyStudentRatio = other.facultyStudentRatio;
    this.citationPerFaculty = other.citationPerFaculty;
    this.academicReputation = other.academicReputation;
    this.employerReputation = other.employerReputation;
  }

  public Integer getOriginalRowElementIdx() {
    return originalRowElementIdx;
  }

  public String getRank() {
    return rank;
  }

  public String getName() {
    return name;
  }

  public String getDetailLinkPath() {
    return detailLinkPath;
  }

  public String getCity() {
    return city;
  }

  public String getCountry() {
    return country;
  }

  public String getOverallScore() {
    return overallScore;
  }

  public String getInternationalStudentsRatio() {
    return internationalStudentsRatio;
  }

  public String getInternationalFacultyRatio() {
    return internationalFacultyRatio;
  }

  public String getFacultyStudentRatio() {
    return facultyStudentRatio;
  }

  public String getCitationPerFaculty() {
    return citationPerFaculty;
  }

  public String getAcademicReputation() {
    return academicReputation;
  }

  public String getEmployerReputation() {
    return employerReputation;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    UniversityRankingIndicator that = (UniversityRankingIndicator) o;
    return Objects.equals(originalRowElementIdx, that.originalRowElementIdx)
        && Objects.equals(name, that.name)
        && Objects.equals(detailLinkPath, that.detailLinkPath)
        && Objects.equals(city, that.city)
        && Objects.equals(country, that.country)
        && Objects.equals(rank, that.rank)
        && Objects.equals(overallScore, that.overallScore)
        && Objects.equals(internationalStudentsRatio, that.internationalStudentsRatio)
        && Objects.equals(internationalFacultyRatio, that.internationalFacultyRatio)
        && Objects.equals(facultyStudentRatio, that.facultyStudentRatio)
        && Objects.equals(citationPerFaculty, that.citationPerFaculty)
        && Objects.equals(academicReputation, that.academicReputation)
        && Objects.equals(employerReputation, that.employerReputation);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        originalRowElementIdx,
        name,
        detailLinkPath,
        city,
        country,
        rank,
        overallScore,
        internationalStudentsRatio,
        internationalFacultyRatio,
        facultyStudentRatio,
        citationPerFaculty,
        academicReputation,
        employerReputation);
  }
}
