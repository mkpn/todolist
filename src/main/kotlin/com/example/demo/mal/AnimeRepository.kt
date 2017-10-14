package com.example.demo.task

import com.example.demo.mal.Anime

interface AnimeRepository {
    fun create(searchQuery: String)

//    fun update(anime: Anime)

//    fun findAll(): List<Anime>
//
//    fun findById(id: Long): Anime?
}