package br.com.gubee.interview.core.features.hero.it;

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

@SpringBootTest(classes = {Application.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("it")
@AutoConfigureMockMvc
public class HeroFindByIdIT extends TestContainersPostgreInstantiation {

	@Autowired
	private MockMvc mockMvc;

  @Test
	public void givenValidID_shouldBeOk() throws Exception {
    String heroId = "e2f066ca-5973-4581-8b2b-a062d7d76f71";
		this.mockMvc.perform(get("/"+heroId))
      .andDo(print())
      .andExpect(status().isOk())
			.andExpect(jsonPath("$.name").value("jose")).andReturn();
	}

  @Test
	public void given_NOT_ValidID_shouldBe404() throws Exception {
    String heroId = "e2f066ca-0000-0000-0000-a062d7d76f71";
		this.mockMvc.perform(get("/"+heroId))
      .andDo(print())
      .andExpect(status().isNotFound());
	}

}
