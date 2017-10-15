package com.example.demo.mal

import com.example.demo.Constants
import com.example.demo.getForEntity
import org.springframework.boot.web.client.RestTemplateBuilder
import java.io.StringReader
import javax.xml.bind.JAXB

class SearchMangaService {
    val restTemplate = RestTemplateBuilder().basicAuthorization(Constants.USER_NAME, Constants.PASSWORD).build()
    val searchMangaUrl = "https://myanimelist.net/api/manga/search.xml?q={searchQuery}"

    fun search(searchQuery: String): List<Entry> {
        val responseEntity = restTemplate.getForEntity<String>(searchMangaUrl, searchQuery)

        val mangaList = JAXB.unmarshal(StringReader(responseEntity?.body), AnimeList::class.java)
//        val stringReader = StringReader(responseEntity?.body)
//        val jaxbContext = JAXBContext.newInstance(AnimeList::class.java)
//        val xif = XMLInputFactory.newInstance()
//        val xsr = xif.createXMLStreamReader(stringReader)
//        val unmarshaller = jaxbContext.createUnmarshaller()
//        val animeList = unmarshaller.unmarshal(xsr) as AnimeList

        return mangaList.entryList
    }
}