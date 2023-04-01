package br.com.gubee.interview.core.adapter.hero.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gubee.interview.model.Hero;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class HeroController {
  
  private final NamedParameterJdbcTemplate template;

  @GetMapping("/")
  public ResponseEntity<String> test() {
      var name = Hero.builder().name(template.getClass().getName()).build();
      return ResponseEntity.ok().body(name.getName());
  }
}
