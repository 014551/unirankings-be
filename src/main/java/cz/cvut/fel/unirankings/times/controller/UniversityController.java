package cz.cvut.fel.unirankings.times.controller;

import cz.cvut.fel.unirankings.times.model.University;
import cz.cvut.fel.unirankings.times.service.UniversityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("theUniversityController")
@RequestMapping("/the")
@CrossOrigin
public class UniversityController {

  private final UniversityService service;

  public UniversityController(UniversityService service) {
    this.service = service;
  }

  @GetMapping(value = "/universities", produces = "application/json")
  public ResponseEntity<List<University>> getAllUniversities(
      @RequestParam(required = false) String year,
      @RequestParam(required = false) String country,
      @RequestParam(required = false) String name) {
    List<University> result = new ArrayList<>();
    if (StringUtils.isNotBlank(year)) {
      result.addAll(service.findUniversities(year, country, name));
    } else {
      result.addAll(service.findUniversities());
    }

    return new ResponseEntity<>(result, new HttpHeaders(), HttpStatus.OK);
  }

  @GetMapping(value = "/universities/{id}", produces = "application/json")
  public ResponseEntity<University> getUniversity(@PathVariable("id") long id) {

    University university = service.findUniversity(id);
    if (university != null) {
      return new ResponseEntity<>(university, new HttpHeaders(), HttpStatus.OK);
    } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
