package com.example.demo

import org.springframework.http.ResponseEntity
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.web.client.RestTemplate

inline fun <reified T> JdbcTemplate.queryForObject(sql: String): T =
        queryForObject(sql, T::class.java)

inline fun <reified T> RestTemplate.getForEntity(url: String, param: String): ResponseEntity<T>? = getForEntity(url, T::class.java, param)