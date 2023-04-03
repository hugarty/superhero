package br.com.gubee.interview.core.app.hero.service;

import java.util.List;
import java.util.UUID;

import br.com.gubee.interview.model.Hero;

public interface HeroService {

  public Hero find(UUID id);

  public List<Hero> findAllByName(String name);
  
}
