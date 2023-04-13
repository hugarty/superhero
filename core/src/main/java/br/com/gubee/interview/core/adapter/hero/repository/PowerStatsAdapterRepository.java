package br.com.gubee.interview.core.adapter.hero.repository;

import org.springframework.stereotype.Component;

import br.com.gubee.interview.core.app.hero.repository.PowerStatsRepository;
import br.com.gubee.interview.model.PowerStats;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class PowerStatsAdapterRepository implements PowerStatsRepository {
  
  private final PowerStatsCrudRepository repository;

  @Override
  public PowerStats save(PowerStats powerStats) {
    return repository.save(powerStats);
  }
  
}
