package br.com.gubee.interview.core.features.hero.it;

import static br.com.gubee.interview.core.features.hero.it.HeroUtils.heroUrl;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import org.junit.jupiter.api.Test;

public class HeroUpdateIT extends TestContainersPostgreInstantiation {

  String heroId = "e2f066ca-5973-4581-8b2b-a062d7d76f71";

  @Test
	public void givenValidDTO_shouldBeUpdated() throws Exception {
    
    StringBuilder content = new StringBuilder();
    content.append("{\"race\":\"DIVINE\",")
      .append("\"name\":\"Wesley\",")
      .append("\"strength\": 10,")
      .append("\"agility\":10,")
      .append("\"dexterity\":10,")
      .append("\"intelligence\" :10 }");
    
		this.mockMvc.perform(put(heroUrl("id/"+heroId)).contentType(APPLICATION_JSON).content(content.toString()))
      .andDo(print())
      .andExpect(status().isOk())
			.andExpect(jsonPath("$.name").value("Wesley"))
			.andExpect(jsonPath("$.race").value("DIVINE"));
	}


  @Test
	public void given_NOT_ValidDTO_shouldBeBadRequest() throws Exception {
    
    StringBuilder content = new StringBuilder();
    content.append("{\"race\":\"INVALID_RACE\",")
      .append("\"name\":\"Wesley\",")
      .append("\"strength\": 10,")
      .append("\"agility\":10,")
      .append("\"intelligence\" :10 }");
    
		this.mockMvc.perform(
        put(heroUrl("id/"+heroId)).contentType(APPLICATION_JSON).content(content.toString())
      )
      .andDo(print())
      .andExpect(status().isBadRequest());
	}

  @Test
	public void given_NOT_ValidID_shouldBeNotFound() throws Exception {
    String heroId = "e2f066ca-0000-0000-0000-a062d7d76f71";

    StringBuilder content = new StringBuilder();
    content.append("{\"race\":\"DIVINE\",")
      .append("\"name\":\"Wesley\",")
      .append("\"strength\": 10,")
      .append("\"agility\":10,")
      .append("\"dexterity\":10,")
      .append("\"intelligence\" :10 }");
    
		this.mockMvc.perform(put(heroUrl("id/"+heroId)).contentType(APPLICATION_JSON).content(content.toString()))
      .andDo(print())
      .andExpect(status().isNotFound());
	}

}
