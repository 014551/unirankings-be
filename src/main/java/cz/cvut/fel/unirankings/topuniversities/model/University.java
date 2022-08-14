package cz.cvut.fel.unirankings.topuniversities.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/** Class represents university according to topuniversities.com. */
@Entity(name = "qsUniversity")
@Table(schema = "qs", name = "university")
public class University implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  /** University full name. */
  @Column(name = "name")
  private String name;

  /** URL of the university detail page. */
  @Column(name = "detail_link_path")
  private String detailLinkPath;

  /** City, where university is located. */
  @Column(name = "city")
  private String city;

  /** Country, where university is located. */
  @Column(name = "country")
  private String country;

  /** University ranks for different years. */
  @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
  @JsonManagedReference
  private final Set<Rank> ranks = new HashSet<>();

  /** University ranking indicators for different years. */
  @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
  @JsonManagedReference
  private final Set<RankingIndicator> rankingIndicators = new HashSet<>();

  public University() {}

  public University(String name, String detailLinkPath, String city, String country) {
    this.name = name;
    this.detailLinkPath = detailLinkPath;
    this.city = city;
    this.country = country;
  }

  public void addRank(Rank rank) {
    rank.setUniversity(this);
    ranks.add(rank);
  }

  public void addRankingIndicator(RankingIndicator rankingIndicator) {
    rankingIndicator.setUniversity(this);
    rankingIndicators.add(rankingIndicator);
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

  public String getCity() {
    return city;
  }

  public String getCountry() {
    return country;
  }

  public Set<Rank> getRanks() {
    return ranks;
  }

  public Set<RankingIndicator> getRankingIndicators() {
    return rankingIndicators;
  }
}
