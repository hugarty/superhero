package br.com.gubee.interview.core.features.hero.it;

import org.springframework.context.annotation.Import;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@Import(IntegrationTestConfiguration.class)
abstract public class TestContainersPostgreInstantiation {

  @Container
  public static final PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres")
      .withDatabaseName("postgres")
      .withUsername("gubee")
      .withPassword("gubee");


  @DynamicPropertySource
  static void postgresProperties(DynamicPropertyRegistry registry) {
      registry.add("jdbc.url", postgreSQLContainer::getJdbcUrl);
      registry.add("spring.datasource.driver-class-name", org.testcontainers.jdbc.ContainerDatabaseDriver.class::getName);
      registry.add("spring.flyway.url", postgreSQLContainer::getJdbcUrl);
      registry.add("spring.flyway.user", postgreSQLContainer::getUsername);
      registry.add("spring.flyway.password", postgreSQLContainer::getPassword);
  }

}
