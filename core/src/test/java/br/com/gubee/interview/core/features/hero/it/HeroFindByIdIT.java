package br.com.gubee.interview.core.features.hero.it;

import static br.com.gubee.interview.core.features.hero.it.HeroUtils.heroUrl;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;


public class HeroFindByIdIT extends TestContainersPostgreInstantiation {
	
  @Test
	public void givenValidID_shouldBeOk() throws Exception {
    String heroId = "e2f066ca-5973-4581-8b2b-a062d7d76f71";
		this.mockMvc.perform(get(heroUrl("id/"+heroId)))
      .andDo(print())
      .andExpect(status().isOk())
			.andExpect(jsonPath("$.name").value("jose")).andReturn();
	}

  @Test
	public void given_NOT_ValidID_shouldBe404() throws Exception {
    String heroId = "e2f066ca-0000-0000-0000-a062d7d76f71";
		this.mockMvc.perform(get(heroUrl("id/"+heroId)))
      .andDo(print())
      .andExpect(status().isNotFound());
	}

}
