package br.com.gubee.interview.model;

import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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

  @Override
  public String toString() {
    return "Id: " + id + "Name:" + name;
  }
}
