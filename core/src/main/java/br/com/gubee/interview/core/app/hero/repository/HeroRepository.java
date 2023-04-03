package br.com.gubee.interview.core.app.hero.repository;

import java.util.List;
import java.util.UUID;

import br.com.gubee.interview.model.Hero;

public interface HeroRepository {

  Hero findById(UUID id);

  List<Hero> findAllByName(String name);
}
