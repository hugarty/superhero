package br.com.gubee.interview.core.adapter.hero.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gubee.interview.core.app.hero.service.HeroService;
import br.com.gubee.interview.model.Hero;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/hero")
public class HeroController {

  private final HeroService service;

  @GetMapping("id/{id}")
  public ResponseEntity<Hero> findHeroByID(@PathVariable(value = "id") UUID id) {
    return ResponseEntity.ok().body(service.find(id));
  }

  @GetMapping("name/{name}")
  public ResponseEntity<List<Hero>> findHeroByID(@PathVariable(value = "name") String name) {
    return ResponseEntity.ok().body(service.findAllByName(name));
  }


}
