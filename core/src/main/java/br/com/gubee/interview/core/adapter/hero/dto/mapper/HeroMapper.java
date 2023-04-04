package br.com.gubee.interview.core.adapter.hero.dto.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import br.com.gubee.interview.core.adapter.hero.dto.HeroDTO;
import br.com.gubee.interview.model.Hero;

@Mapper( componentModel = "spring")
public interface HeroMapper {
  
  HeroMapper INSTANCE = Mappers.getMapper(HeroMapper.class);
  
  @Mapping(source = "powerStats.intelligence", target = "intelligence")
  @Mapping(source = "powerStats.dexterity", target = "dexterity")
  @Mapping(source = "powerStats.agility", target = "agility")
  @Mapping(source = "powerStats.strength", target = "strength")
  HeroDTO heroToHeroDTO (Hero hero);


  List<HeroDTO> heroToHeroDTO (List<Hero> hero);


  @Mapping(source = "intelligence", target = "powerStats.intelligence")
  @Mapping(source = "dexterity", target = "powerStats.dexterity")
  @Mapping(source = "agility", target = "powerStats.agility")
  @Mapping(source = "strength", target = "powerStats.strength")
  Hero heroDtoToHero (HeroDTO heroDto);

}
