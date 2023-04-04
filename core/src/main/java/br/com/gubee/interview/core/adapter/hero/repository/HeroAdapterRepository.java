package br.com.gubee.interview.core.adapter.hero.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import br.com.gubee.interview.core.app.hero.repository.HeroRepository;
import br.com.gubee.interview.model.Hero;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class HeroAdapterRepository implements HeroRepository {

  private final HeroCrudRepository repository;

  @Override
  @Cacheable(value = "heroCache")
  public Hero findById(UUID id) {
    return repository.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Hero not found"));
  }

  @Override
  public List<Hero> findAllByName(String name) {
    return repository.findAllByNameContaining(name);
  }
  
  @Override
  public void deleteById(UUID id) {
    Hero hero = findById(id);
    repository.delete(hero);
  }

  @Override
  public Hero save(Hero hero) {
    return repository.save(hero);
  }
}
