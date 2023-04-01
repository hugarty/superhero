package br.com.gubee.interview.core.adapter.hero.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import br.com.gubee.interview.model.Hero;

public interface HeroCrudRepository extends CrudRepository<Hero, UUID> {

}
