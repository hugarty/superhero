package br.com.gubee.interview.model;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import br.com.gubee.interview.enumerator.Race;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Hero {
  @Id
  private UUID id;
  @Column
  private String name;
  @Column
  @Enumerated(EnumType.STRING)
  private Race race;
  @Column
  private UUID powerStatsId;
  @Column
  private Instant createdAt;
  @Column
  private Instant updatedAt;
  @Column
  private boolean enabled;
}
