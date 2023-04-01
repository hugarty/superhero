package br.com.gubee.interview.model;

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

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class PowerStats {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
