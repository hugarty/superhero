package br.com.gubee.interview.core.adapter.hero.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import br.com.gubee.interview.core.app.hero.service.HeroService;
import br.com.gubee.interview.model.Hero;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class HeroController {

  private final HeroService service;

  @GetMapping("/{id}")
  public ResponseEntity<Hero> findHeroByID(@PathVariable(value = "id") UUID id) {
    Hero hero = service.find(id);
    return ResponseEntity.ok().body(hero);
  }
}
