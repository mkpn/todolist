package com.example.demo.mal

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository

@Repository
class JdbcMangaRepository(private val jdbcTemplate: JdbcTemplate) : com.example.demo.mal.MangaRepository {
    private val rowMapper = RowMapper<Manga> { rs, rowNum ->
        Manga(rs.getLong("id"), rs.getLong("content_id"), rs.getString("title"),
                rs.getString("english"), rs.getString("synonyms"), rs.getInt("chapters"),
                rs.getInt("volumes"), rs.getFloat("score"), rs.getString("start_date"), rs.getString("end_date"),
                rs.getString("synopsis"), rs.getString("image"))
    }

    override fun findAll(): List<Manga> = jdbcTemplate.query("SELECT * FROM MANGA", rowMapper)

    override fun create(searchQuery: String) {
        val animeList = SearchAnimeService().search(searchQuery)

        animeList.forEach { manga ->
            jdbcTemplate.update("""INSERT INTO manga(content_id, title,
            |                                    english, synonyms, chapters, volumes, score,
            |                                    type, start_date, end_date, synopsis, image)
            |                                    VALUES(?,?,?,?,?,?,?,?,?,?,?,?)""".trimMargin(),
                    manga.contentId, manga.title,
                    manga.english, manga.synonyms, manga.chapters, manga.volumes, manga.score,
                    manga.type, manga.startDate, manga.endDate, manga.synopsis, manga.image)
        }
    }
}