package br.com.gubee.interview.model;

import java.time.Instant;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PowerStats {
  
  private UUID id;
  private Short strength;
  private Short agility;
  private Short dexterity;
  private Short intelligence;
  private Instant createdAt;
  private Instant updatedAt;

}
