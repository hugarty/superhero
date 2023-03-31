package br.com.gubee.interview.model;

import java.time.Instant;
import java.util.UUID;

import br.com.gubee.interview.enumerator.Race;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Hero {

  private UUID id;
  private String name;
  private Race race;
  private UUID powerStatsId;
  private Instant createdAt;
  private Instant updatedAt;
  private boolean enabled;
}
