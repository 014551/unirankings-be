package cz.cvut.fel.unirankings.times.controller;

import cz.cvut.fel.unirankings.times.repository.RankRepository;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController("theRankController")
@RequestMapping("/the")
@CrossOrigin
public class RankController {

  private final RankRepository repository;

  public RankController(RankRepository repository) {
    this.repository = repository;
  }

  @GetMapping("/ranks/years")
  public ResponseEntity<List<String>> getAvailableRankYears() {
    List<String> years = repository.findAvailableRankYears();

    return new ResponseEntity<>(years, new HttpHeaders(), HttpStatus.OK);
  }
}
