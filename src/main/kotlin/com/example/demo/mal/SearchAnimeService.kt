package com.example.demo.mal

import com.example.demo.Constants
import com.example.demo.getForEntity
import org.slf4j.LoggerFactory
import org.springframework.boot.web.client.RestTemplateBuilder
import java.io.StringReader
import javax.xml.bind.JAXB

class SearchAnimeService {
    val restTemplate = RestTemplateBuilder().basicAuthorization(Constants.USER_NAME, Constants.PASSWORD).build()
    val searchAnimeUrl = "https://myanimelist.net/api/anime/search.xml?q={searchQuery}"
    val logger = LoggerFactory.getLogger(SearchAnimeService::class.java)

    fun search(searchQuery: String): List<Entry> {
        val responseEntity = restTemplate.getForEntity<String>(searchAnimeUrl, searchQuery)

        val animeList = JAXB.unmarshal(StringReader(responseEntity?.body), AnimeList::class.java)
//        val stringReader = StringReader(responseEntity?.body)
//        val jaxbContext = JAXBContext.newInstance(AnimeList::class.java)
//        val xif = XMLInputFactory.newInstance()
//        val xsr = xif.createXMLStreamReader(stringReader)
//        val unmarshaller = jaxbContext.createUnmarshaller()
//        val animeList = unmarshaller.unmarshal(xsr) as AnimeList

        animeList.entryList.forEach {
            logger.info("start date is ${it.startDate}")
            logger.info("title is ${it.title}")
        }
        return animeList.entryList
    }
}