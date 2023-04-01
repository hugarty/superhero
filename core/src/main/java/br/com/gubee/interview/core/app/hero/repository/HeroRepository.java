package br.com.gubee.interview.core.app.hero.repository;

import java.util.UUID;

import br.com.gubee.interview.model.Hero;

public interface HeroRepository {

  Hero findById(UUID id);
}
