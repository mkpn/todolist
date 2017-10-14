package com.example.demo.task

import com.example.demo.mal.SearchAnimeService
import com.example.demo.queryForObject
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Repository
import sun.rmi.runtime.Log

@Repository
class JdbcAnimeRepository(private val jdbcTemplate: JdbcTemplate) : AnimeRepository {
    override fun create(searchQuery: String) {
        SearchAnimeService().search(searchQuery)
//        jdbcTemplate.update("""INSERT INTO anime(content_id, title,
//            |                                    english, synonyms, episodes, score,
//            |                                    start_date, end_date, synopsis, image)
//            |                                    VALUES(?,?,?,?,?,?,?,?,?,?)""".trimMargin(), anime)
//        val id: Long = jdbcTemplate.queryForObject("SELECT last_insert_id()")
    }
}
