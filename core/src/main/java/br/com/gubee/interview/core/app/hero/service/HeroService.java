package br.com.gubee.interview.core.app.hero.service;

import java.util.List;
import java.util.UUID;

import br.com.gubee.interview.model.Hero;

public interface HeroService {

  public Hero find(UUID id);

  public List<Hero> findAllByName(String name);

  public void delete(UUID id);

  public Hero create(Hero hero);

  public Hero update(UUID id, Hero hero);
  
}
