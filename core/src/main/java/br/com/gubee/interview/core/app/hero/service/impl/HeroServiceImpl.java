package br.com.gubee.interview.core.app.hero.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.gubee.interview.core.app.hero.repository.HeroRepository;
import br.com.gubee.interview.core.app.hero.service.HeroService;
import br.com.gubee.interview.model.Hero;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class HeroServiceImpl implements HeroService{

  private final HeroRepository repository;

  @Override
  public Hero find(UUID id) {    
    return repository.findById(id);
  }
  
}
