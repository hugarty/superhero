package br.com.gubee.interview.core.features.hero.it;

import static br.com.gubee.interview.core.features.hero.it.HeroUtils.heroUrl;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import br.com.gubee.interview.core.Application;

public class HeroFindByNameIT extends TestContainersPostgreInstantiation {
	
  private final String URL = "name/";

  @Test
	public void givenValidID_shouldBeOk() throws Exception {
    String heroName = "jose";
		this.mockMvc.perform(get(heroUrl(URL+heroName)))
      .andDo(print())
      .andExpect(status().isOk())
			.andExpect(jsonPath("$[0].name").value("jose"))
      .andExpect(jsonPath("$[1].name").value("jose carlos"));
	}

  @Test
	public void given_NOT_ValidID_shouldBe404() throws Exception {
    String heroName = "pedro";
		this.mockMvc.perform(get(heroUrl(URL+heroName)))
      .andDo(print())
      .andExpect(status().isOk())
			.andExpect(jsonPath("$").isEmpty());
	}

}

