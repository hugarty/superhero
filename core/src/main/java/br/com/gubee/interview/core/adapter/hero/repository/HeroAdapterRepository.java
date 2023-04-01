package br.com.gubee.interview.core.adapter.hero.repository;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import br.com.gubee.interview.core.app.hero.repository.HeroRepository;
import br.com.gubee.interview.model.Hero;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class HeroAdapterRepository implements HeroRepository {

  private final HeroCrudRepository repository;

  @Override
  public Hero findById(UUID id) {
    return repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hero not found"));
  }
  
}
