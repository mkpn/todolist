package com.example.demo.mal

interface MangaRepository {
    fun create(searchQuery: String)

//    fun update(anime: Entry)

    fun findAll(): List<Manga>
//
//    fun findById(id: Long): Entry?
}