package com.example.demo.mal

import com.example.demo.Constants
import com.example.demo.getForEntity
import org.slf4j.LoggerFactory
import org.springframework.boot.web.client.RestTemplateBuilder
import java.io.StringReader
import javax.xml.bind.JAXB

class SearchAnimeService {
    val restTemplate = RestTemplateBuilder().basicAuthorization("ne-san", "M4LRecSample").build()
    val searchAnimeUrl = "https://myanimelist.net/api/anime/search.xml?q={searchQuery}"
    val logger = LoggerFactory.getLogger(SearchAnimeService::class.java)

    fun search(searchQuery: String): List<Entry> {
        val responseEntity = restTemplate.getForEntity<String>(searchAnimeUrl, searchQuery)

        val animeList = JAXB.unmarshal(StringReader(responseEntity?.body), AnimeList::class.java)

        animeList.entryList.forEach {
            logger.info("start date is ${it.startDate}")
            logger.info("title is ${it.title}")
        }
        return animeList.entryList
    }
}