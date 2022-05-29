package cz.cvut.fel.unirankings.topuniversities.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "qsRankingIndicator")
@Table(schema = "qs", name = "ranking_indicator")
public class RankingIndicator implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "original_order_idx")
  private Integer originalOrderIdx;

  @Column(name = "year")
  private String year;

  @Column(name = "overall_score")
  private String overallScore;

  @Column(name = "international_students_ratio")
  private String internationalStudentsRatio;

  @Column(name = "international_faculty_ratio")
  private String internationalFacultyRatio;

  @Column(name = "faculty_student_ratio")
  private String facultyStudentRatio;

  @Column(name = "citation_per_faculty")
  private String citationPerFaculty;

  @Column(name = "academic_reputation")
  private String academicReputation;

  @Column(name = "employer_reputation")
  private String employerReputation;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "university_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonBackReference
  private University university;

  public RankingIndicator() {}

  public RankingIndicator(
      Integer originalOrderIdx,
      String year,
      String overallScore,
      String internationalStudentsRatio,
      String internationalFacultyRatio,
      String facultyStudentRatio,
      String citationPerFaculty,
      String academicReputation,
      String employerReputation,
      University university) {
    this.originalOrderIdx = originalOrderIdx;
    this.year = year;
    this.overallScore = overallScore;
    this.internationalStudentsRatio = internationalStudentsRatio;
    this.internationalFacultyRatio = internationalFacultyRatio;
    this.facultyStudentRatio = facultyStudentRatio;
    this.citationPerFaculty = citationPerFaculty;
    this.academicReputation = academicReputation;
    this.employerReputation = employerReputation;
    this.university = university;
  }

  public long getId() {
    return id;
  }

  public Integer getOriginalOrderIdx() {
    return originalOrderIdx;
  }

  public String getYear() {
    return year;
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

  public University getUniversity() {
    return university;
  }

  public void setUniversity(University university) {
    this.university = university;
  }
}
