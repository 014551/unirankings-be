package cz.cvut.fel.unirankings.topuniversities.controller;

import cz.cvut.fel.unirankings.topuniversities.model.University;
import cz.cvut.fel.unirankings.topuniversities.service.UniversityService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController("qsUniversityController")
@RequestMapping("/qs")
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
    List<University> list = new ArrayList<>();
    if (StringUtils.isNotBlank(year)) {
      list.addAll(service.findUniversities(year, country, name));
    }

    return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
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
