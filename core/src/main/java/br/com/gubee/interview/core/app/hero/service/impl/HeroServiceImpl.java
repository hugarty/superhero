package br.com.gubee.interview.core.app.hero.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.gubee.interview.core.app.hero.repository.HeroRepository;
import br.com.gubee.interview.core.app.hero.repository.PowerStatsRepository;
import br.com.gubee.interview.core.app.hero.service.HeroService;
import br.com.gubee.interview.model.Hero;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HeroServiceImpl implements HeroService{

  private final HeroRepository repository;
  private final PowerStatsRepository powerStatsRepository;

  @Override
  public Hero find(UUID id) {    
    return repository.findById(id);
  }

  @Override
  public List<Hero> findAllByName(String name) {
    return repository.findAllByName(name);
  }

  @Override
  public void delete(UUID id) {
    repository.deleteById(id);
  }

  @Override
  public Hero create(Hero hero) {
    hero.prepareEntityToSave();
    var powerStats = powerStatsRepository.save(hero.getPowerStats());
    hero.setPowerStats(powerStats);
    return repository.save(hero);
  }

  @Override
  public Hero update(UUID id, Hero newHero) {
    Hero hero = repository.findById(id);
    hero.updateWith(newHero);
    powerStatsRepository.save(hero.getPowerStats());
    return repository.save(hero);
  }
  
}
