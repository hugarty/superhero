package br.com.gubee.interview.core.features.hero.it;

import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;



import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import br.com.gubee.interview.core.Application;
import org.springframework.beans.factory.annotation.Autowired;



@SpringBootTest(classes = {Application.class}, webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("it")
@AutoConfigureMockMvc
@Testcontainers
@Import(IntegrationTestConfiguration.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS) // Needed to Preserve TestContainers for each IT.class
abstract public class TestContainersPostgreInstantiation {

	@Autowired
	protected MockMvc mockMvc;

  @Container
  public static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres")
      .withDatabaseName("postgres")
      .withUsername("gubee")
      .withPassword("gubee");


  static {
    postgreSQLContainer.start();
  }

  @DynamicPropertySource
  static void postgresProperties(DynamicPropertyRegistry registry) {
      registry.add("jdbc.url", postgreSQLContainer::getJdbcUrl);
      registry.add("spring.datasource.driver-class-name", org.testcontainers.jdbc.ContainerDatabaseDriver.class::getName);
      registry.add("spring.flyway.url", postgreSQLContainer::getJdbcUrl);
      registry.add("spring.flyway.user", postgreSQLContainer::getUsername);
      registry.add("spring.flyway.password", postgreSQLContainer::getPassword);
  }
}
