package com.example.demo.task

import com.example.demo.mal.Anime
import com.example.demo.mal.SearchAnimeService
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class JdbcAnimeRepository(private val jdbcTemplate: JdbcTemplate) : com.example.demo.task.Repository {
    private val rowMapper = RowMapper<Anime> { rs, rowNum ->
        Anime(rs.getLong("id"), rs.getLong("content_id"), rs.getString("title"),
                rs.getString("english"), rs.getString("synonyms"), rs.getInt("episodes"),
                rs.getFloat("score"), rs.getString("start_date"), rs.getString("end_date"),
                rs.getString("synopsis"), rs.getString("image"))
    }

    override fun findAll(): List<Anime> = jdbcTemplate.query("SELECT * FROM ANIME", rowMapper)

    override fun create(searchQuery: String) {
        val animeList = SearchAnimeService().search(searchQuery)

        animeList.forEach { anime ->
            jdbcTemplate.update("""INSERT INTO anime(content_id, title,
            |                                    english, synonyms, episodes, score,
            |                                    start_date, end_date, synopsis, image)
            |                                    VALUES(?,?,?,?,?,?,?,?,?,?)""".trimMargin(),
                    anime.contentId, anime.title,
                    anime.english, anime.synonyms, anime.episodes, anime.score,
                    anime.startDate, anime.endDate, anime.synopsis, anime.image)
        }
    }
}
