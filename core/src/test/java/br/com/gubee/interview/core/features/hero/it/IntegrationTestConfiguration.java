package br.com.gubee.interview.core.features.hero.it;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
@EnableConfigurationProperties(FlywayProperties.class)
public class IntegrationTestConfiguration {

  Logger logger = LoggerFactory.getLogger(IntegrationTestConfiguration.class);

  @Bean(initMethod = "migrate")
  public Flyway flyway(FlywayProperties properties) {
    ClassPathResource init_schema = new ClassPathResource("db/schema/init_schema.sql");
    try (BufferedReader reader = new BufferedReader(
        new InputStreamReader(init_schema.getInputStream()))) {
      String initSql = reader.lines()
        .filter(a -> !a.startsWith("CREATE USER gubee"))
        .collect(Collectors.joining("\n"));

      String[] locations = new String[properties.getLocations().size()];
      properties.getLocations().toArray(locations);
      return new Flyway(Flyway.configure()
          .initSql(initSql)
          .baselineOnMigrate(true)
          .locations(locations)
          .defaultSchema(properties.getDefaultSchema())
          .dataSource(properties.getUrl(), properties.getUser(), properties.getPassword()));
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
  }

}
