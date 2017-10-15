package com.example.demo.mal

import com.example.demo.Constants
import com.example.demo.getForEntity
import org.springframework.boot.web.client.RestTemplateBuilder
import java.io.StringReader
import javax.xml.bind.JAXB

class SearchMangaService {
    val restTemplate = RestTemplateBuilder().basicAuthorization("ne-san", "M4LRecSample").build()
    val searchMangaUrl = "https://myanimelist.net/api/manga/search.xml?q={searchQuery}"

    fun search(searchQuery: String): List<Entry> {
        val responseEntity = restTemplate.getForEntity<String>(searchMangaUrl, searchQuery)

        val mangaList = JAXB.unmarshal(StringReader(responseEntity?.body), AnimeList::class.java)

        return mangaList.entryList
    }
}