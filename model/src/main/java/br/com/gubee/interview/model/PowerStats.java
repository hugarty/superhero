package br.com.gubee.interview.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
public class PowerStats implements Serializable {
  @Id
  @Column(name="id", updatable = false)
  private UUID id;
  @Column
  private Short strength;
  @Column
  private Short agility;
  @Column
  private Short dexterity;
  @Column
  private Short intelligence;
  @Column
  private Instant createdAt;
  @Column
  private Instant updatedAt;

  public void updateWith(PowerStats newStats, Instant now) {
    this.agility = newStats.agility;
    this.strength = newStats.strength;
    this.dexterity = newStats.dexterity;
    this.intelligence = newStats.intelligence;
    this.updatedAt = now;
  }

}
