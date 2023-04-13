package br.com.gubee.interview.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import br.com.gubee.interview.enumerator.Race;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Hero implements Serializable {
  @Id
  @Column(name="id", updatable = false)
  private UUID id;
  @Column
  private String name;
  @Column
  @Enumerated(EnumType.STRING)
  private Race race;

  @Column(name="power_stats_id")
  private UUID powerStatsId;

  @OneToOne
  @JoinColumn(name="power_stats_id", insertable = false, updatable = false)
  private PowerStats powerStats;

  @Column
  private Instant createdAt;
  @Column
  private Instant updatedAt;
  @Column
  private boolean enabled;

  public void prepareEntityToSave () {
    Instant now = Instant.now();
    UUID randomUUID = UUID.randomUUID();
    this.id = randomUUID;
    this.updatedAt = now;
    this.createdAt = now;
    this.powerStatsId = randomUUID;
    this.powerStats.setId(randomUUID);
    this.powerStats.setUpdatedAt(now);
    this.powerStats.setCreatedAt(now);
  }

  public void updateWith(Hero newHero) {
    Instant now = Instant.now();
    this.name = newHero.name;
    this.race = newHero.race;
    this.enabled = newHero.isEnabled();
    this.updatedAt = now;
    this.powerStats.updateWith(newHero.powerStats, now);
  }

  @Override
  public String toString() {
    return "Id: " + id + "Name:" + name;
  }

}
