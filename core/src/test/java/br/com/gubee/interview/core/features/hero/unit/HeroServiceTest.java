package br.com.gubee.interview.core.features.hero.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.gubee.interview.core.app.hero.repository.HeroRepository;
import br.com.gubee.interview.core.app.hero.repository.PowerStatsRepository;
import br.com.gubee.interview.core.app.hero.service.HeroService;
import br.com.gubee.interview.core.app.hero.service.impl.HeroServiceImpl;
import br.com.gubee.interview.model.Hero;

@ExtendWith(MockitoExtension.class)
public class HeroServiceTest {

  @Mock
  private HeroRepository repository;
  @Mock
  private PowerStatsRepository powerStatsRepository;

  @Mock
  private Hero mockHero;

  private HeroService service;
  private UUID heroUUID = UUID.randomUUID();

  @BeforeEach
  public void init () {
    service = new HeroServiceImpl(repository, powerStatsRepository);
  }

  @Test
  public void givenUUID_whenFindById_thenReturnHero () {
    Hero hero = Hero.builder().id(heroUUID).name("test").build();
    when(repository.findById(heroUUID)).thenReturn(hero);

    service.find(heroUUID);
    assertEquals("test", hero.getName());
    verify(repository, times(1)).findById(heroUUID);
  }

  @Test
  public void givenName_whenFindAll_thenSave () {
    String name = "jose";
    List<Hero> emptyList = Collections.emptyList();
    when(repository.findAllByName(name)).thenReturn(emptyList);

    List<Hero> all = service.findAllByName(name);
    assertEquals(0, all.size());
    verify(repository, times(1)).findAllByName(name);
  }

  @Test
  public void givenMockHero_whenCreate_thenSave () {
    service.create(mockHero);
    verify(repository, times(1)).save(mockHero);
    verify(powerStatsRepository, times(1)).save(Mockito.any());
    verify(mockHero, times(1)).prepareEntityToSave();
  }

  @Test
  public void givenUUID_whenDelete_thenDelete () {
    service.delete(heroUUID);
    verify(repository, times(1)).deleteById(heroUUID);
  }

  @Test
  public void givenUUIDandMockHero_whenUpdate_thenUpdate () {
    when(repository.findById(heroUUID)).thenReturn(mockHero);
    service.update(heroUUID, mockHero);
    verify(repository, times(1)).findById(heroUUID);
    verify(mockHero, times(1)).updateWith(Mockito.any());
    verify(powerStatsRepository, times(1)).save(Mockito.any());
    verify(repository, times(1)).save(Mockito.any());
  }
}
