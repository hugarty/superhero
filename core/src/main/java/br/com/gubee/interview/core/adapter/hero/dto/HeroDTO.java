package br.com.gubee.interview.core.adapter.hero.dto;

import java.time.Instant;
import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import br.com.gubee.interview.enumerator.Race;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class HeroDTO {
  private UUID id;
  @NotBlank
  @Length(min = 1, max = 255)
  private String name;
  @NotNull
  @Pattern(regexp="DIVINE|HUMAN|CYBORG|ALIEN")
  private String race;
  @NotNull
  private Short strength;
  @NotNull
  private Short agility;
  @NotNull
  private Short dexterity;
  @NotNull
  private Short intelligence;
  private Instant createdAt;
  private Instant updatedAt;

  @Override
  public String toString() {
    return "Id: " + id + "Name:" + name;
  }
}
