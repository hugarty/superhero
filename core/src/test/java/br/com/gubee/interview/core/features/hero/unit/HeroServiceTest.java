package br.com.gubee.interview.core.features.hero.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.gubee.interview.core.app.hero.repository.HeroRepository;
import br.com.gubee.interview.core.app.hero.service.HeroService;
import br.com.gubee.interview.core.app.hero.service.impl.HeroServiceImpl;
import br.com.gubee.interview.model.Hero;

@ExtendWith(MockitoExtension.class)
public class HeroServiceTest {

  @Mock
  private HeroRepository repository;
  private HeroService service;
  private UUID heroUUID = UUID.randomUUID();

  @BeforeEach
  public void init () {
    service = new HeroServiceImpl(repository);
    Hero hero = Hero.builder().id(heroUUID).name("test").build();
    when(repository.findById(heroUUID)).thenReturn(hero);
  }

  @Test
  public void givenUUID_whenFindById_thenReturnHero () {
    Hero hero = service.find(heroUUID);
    assertEquals("test", hero.getName());
    verify(repository, times(1)).findById(heroUUID);
  }
}
