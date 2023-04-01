package br.com.gubee.interview.core.app.hero.service;

import java.util.UUID;

import br.com.gubee.interview.model.Hero;

public interface HeroService {

  public Hero find(UUID id);
  
}
