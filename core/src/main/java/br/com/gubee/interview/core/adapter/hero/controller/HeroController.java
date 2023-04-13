package br.com.gubee.interview.core.adapter.hero.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gubee.interview.core.adapter.hero.dto.HeroDTO;
import br.com.gubee.interview.core.adapter.hero.dto.mapper.HeroMapper;
import br.com.gubee.interview.core.app.hero.service.HeroService;
import br.com.gubee.interview.model.Hero;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/hero")
public class HeroController {

  private final HeroService service;

  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<HeroDTO> createHero(@Validated @RequestBody HeroDTO dto) {
    Hero hero = HeroMapper.INSTANCE.heroDtoToHero(dto);
    HeroDTO resultDTO = HeroMapper.INSTANCE.heroToHeroDTO(service.create(hero));
    return ResponseEntity.status(HttpStatus.CREATED).body(resultDTO);
  }

  @GetMapping("id/{id}")
  public ResponseEntity<HeroDTO> findHeroByID(@PathVariable(value = "id") UUID id) {
    return ResponseEntity.ok().body(HeroMapper.INSTANCE.heroToHeroDTO(service.find(id)));
  }

  @GetMapping("name/{name}")
  public ResponseEntity<List<HeroDTO>> findHeroByID(@PathVariable(value = "name") String name) {
    return ResponseEntity.ok().body(HeroMapper.INSTANCE.heroToHeroDTO(service.findAllByName(name)));
  }

  @PutMapping("id/{id}")
  public ResponseEntity<HeroDTO> updateHeroByID(@PathVariable(value = "id") UUID id, @Validated @RequestBody HeroDTO dto) {
    Hero hero = HeroMapper.INSTANCE.heroDtoToHero(dto);
    Hero updated = service.update(id, hero);
    return ResponseEntity.ok().body(HeroMapper.INSTANCE.heroToHeroDTO(updated));
  }

  @DeleteMapping("id/{id}")
  public ResponseEntity<Object> deleteHeroByID(@PathVariable(value = "id") UUID id) {
    service.delete(id);
    return ResponseEntity.ok().build();
  }
}
