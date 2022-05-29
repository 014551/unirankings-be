package cz.cvut.fel.unirankings.times.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "theRank")
@Table(name = "rank", schema = "the")
public class Rank implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "original_order_idx")
  private Integer originalOrderIdx;

  @Column(name = "year")
  private String year;

  @Column(name = "rank")
  private String rank;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "university_id", nullable = false)
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonBackReference
  private University university;

  public Rank() {}

  public Rank(Integer originalOrderIdx, String year, String rank, University university) {
    this.originalOrderIdx = originalOrderIdx;
    this.year = year;
    this.rank = rank;
    this.university = university;
  }

  public long getId() {
    return id;
  }

  public int getOriginalOrderIdx() {
    return originalOrderIdx;
  }

  public String getYear() {
    return year;
  }

  public String getRank() {
    return rank;
  }

  public University getUniversity() {
    return university;
  }

  public void setUniversity(University university) {
    this.university = university;
  }
}
