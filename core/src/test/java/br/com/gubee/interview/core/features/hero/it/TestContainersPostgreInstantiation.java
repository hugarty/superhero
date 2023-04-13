package br.com.gubee.interview.core.features.hero.it;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.utility.DockerImageName;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import br.com.gubee.interview.core.Application;

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
  public static GenericContainer<?> redis = new GenericContainer<>(DockerImageName.parse("redis"))
      .withExposedPorts(6379);


  @Container
  public static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres")
      .withDatabaseName("postgres")
      .withUsername("gubee")
      .withPassword("gubee");


  static {
    postgreSQLContainer.start();
    redis.start();
  }

  @DynamicPropertySource
  static void postgresProperties(DynamicPropertyRegistry registry) {
    registry.add("spring.redis.host", redis::getHost);
    registry.add("spring.redis.port", () -> redis.getMappedPort(6379).toString());
    registry.add("jdbc.url", postgreSQLContainer::getJdbcUrl);
    registry.add("spring.datasource.driver-class-name", org.testcontainers.jdbc.ContainerDatabaseDriver.class::getName);
    registry.add("spring.flyway.url", postgreSQLContainer::getJdbcUrl);
    registry.add("spring.flyway.user", postgreSQLContainer::getUsername);
    registry.add("spring.flyway.password", postgreSQLContainer::getPassword);
  }
}
