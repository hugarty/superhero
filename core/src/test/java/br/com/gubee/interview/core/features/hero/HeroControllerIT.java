package br.com.gubee.interview.core.features.hero;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ActiveProfiles("it")
@AutoConfigureMockMvc
@DataJpaTest
public class HeroControllerIT {

  @Autowired
  private MockMvc mvc;

  @Test
  public void verify_findById () throws Exception{
    String heroId = "e2f066ca-5973-4581-8b2b-a062d7d76f71";
    mvc.perform(get("/"+heroId))
      .andExpect(status().isOk())
      .andExpect(content().string("Jose"));
  }
  
}
