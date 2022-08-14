package cz.cvut.fel.unirankings.times.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/** Class represents university according to timeshighereducation.com. */
@Entity(name = "theUniversity") /**/
@Table(name = "university", schema = "the")
public class University implements Serializable {

  /** University name. */
  @Column(name = "name")
  private String name;

  /** URL of the university detail page. */
  @Column(name = "detail_link_path")
  private String detailLinkPath;

  /** Country, where university is located. */
  @Column(name = "country")
  private String country;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  /** University ranks for different years. */
  @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
  @JsonManagedReference
  private Set<Rank> ranks = new HashSet<>();

  /** University ranking scores for different years. */
  @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
  @JsonManagedReference
  private Set<RankingScore> rankingScores = new HashSet<>();

  public University() {}

  public University(String name, String detailLinkPath, String country) {
    this.name = name;
    this.detailLinkPath = detailLinkPath;
    this.country = country;
  }

  public void addRank(Rank rank) {
    rank.setUniversity(this);
    ranks.add(rank);
  }

  public void addRankingScore(RankingScore rankingScore) {
    rankingScore.setUniversity(this);
    rankingScores.add(rankingScore);
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDetailLinkPath() {
    return detailLinkPath;
  }

  public String getCountry() {
    return country;
  }

  public Set<Rank> getRanks() {
    return ranks;
  }

  public void setRanks(Set<Rank> ranks) {
    this.ranks = ranks;
  }

  public Set<RankingScore> getRankingScores() {
    return rankingScores;
  }

  public void setRankingScores(Set<RankingScore> rankingScores) {
    this.rankingScores = rankingScores;
  }
}
