package br.com.gubee.interview.core.features.hero.it;

import static br.com.gubee.interview.core.features.hero.it.HeroUtils.heroUrl;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;

public class HeroCreateIT extends TestContainersPostgreInstantiation {
  

  @Test
	public void givenValidDTO_shouldBeCreated() throws Exception {
    
    StringBuilder content = new StringBuilder();
    content.append("{\"race\":\"DIVINE\",")
      .append("\"name\":\"Wesley\",")
      .append("\"strength\": 10,")
      .append("\"agility\":10,")
      .append("\"dexterity\":10,")
      .append("\"intelligence\" :10 }");
    
		this.mockMvc.perform(post(heroUrl("")).contentType(APPLICATION_JSON).content(content.toString()))
      .andDo(print())
      .andExpect(status().isCreated())
			.andExpect(jsonPath("$.name").value("Wesley"))
			.andExpect(jsonPath("$.race").value("DIVINE"));
	}


  @Test
	public void given_NOT_ValidDTO_shouldBeBadRequest() throws Exception {
    
    StringBuilder content = new StringBuilder();
    content.append("{\"race\":\"DIVINE\",")
      .append("\"name\":\"Wesley\",")
      .append("\"strength\": 10,")
      .append("\"agility\":10,")
      .append("\"intelligence\" :10 }");
    
		this.mockMvc.perform(post(heroUrl("")).contentType(APPLICATION_JSON).content(content.toString()))
      .andDo(print())
      .andExpect(status().isBadRequest());
	}

}
