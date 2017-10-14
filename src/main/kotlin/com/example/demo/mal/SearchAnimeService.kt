package com.example.demo.mal

import com.example.demo.Constants
import com.example.demo.getForEntity
import org.slf4j.LoggerFactory
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.web.client.RestTemplate

class SearchAnimeService {
    val restTemplate = RestTemplateBuilder().basicAuthorization(Constants.USER_NAME, Constants.PASSWORD).build()
    val searchAnimeUrl = "https://myanimelist.net/api/anime/search.xml?q={searchQuery}"
    val logger = LoggerFactory.getLogger(SearchAnimeService::class.java)

    fun search(searchQuery: String) {
        var responseEntity = restTemplate.getForEntity(searchAnimeUrl, String::class.java, searchQuery)
        logger.info(responseEntity.toString())
    }
}