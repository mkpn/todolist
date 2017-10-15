package com.example.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.jdbc.core.JdbcTemplate

@SpringBootApplication
class DemoApplication {

    @Bean
    fun commandLineRunner(jdbcTemplate: JdbcTemplate) = CommandLineRunner {
        jdbcTemplate.execute("""CREATE TABLE IF NOT EXISTS anime (
          id         BIGINT   PRIMARY KEY AUTO_INCREMENT,
          content_id  BIGINT   UNIQUE,
          title      NVARCHAR NOT NULL,
          english    VARCHAR  NOT NULL,
          synonyms   NVARCHAR NOT NULL,
          episodes   INT      NOT NULL,
          score      FLOAT    NOT NULL,
          start_date VARCHAR  NOT NULL,
          end_date   VARCHAR  NOT NULL,
          synopsis   VARCHAR  NOT NULL,
          image      VARCHAR  NOT NULL)""")
    }

    @Bean
    fun commandLineRunner2(jdbcTemplate: JdbcTemplate) = CommandLineRunner {
        jdbcTemplate.execute("""CREATE TABLE IF NOT EXISTS manga (
          id         BIGINT   PRIMARY KEY AUTO_INCREMENT,
          content_id  BIGINT   UNIQUE,
          title      NVARCHAR NOT NULL,
          english    VARCHAR  NOT NULL,
          synonyms   NVARCHAR NOT NULL,
          chapters   INT      NOT NULL,
          volumes   INT      NOT NULL,
          score      FLOAT    NOT NULL,
          `type`     VARCHAR   NOT NULL,
          start_date VARCHAR  NOT NULL,
          end_date   VARCHAR  NOT NULL,
          synopsis   VARCHAR  NOT NULL,
          image      VARCHAR  NOT NULL)""")
    }
}

fun main(args: Array<String>) {
    SpringApplication.run(DemoApplication::class.java, *args)
}
