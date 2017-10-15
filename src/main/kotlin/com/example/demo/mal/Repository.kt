package com.example.demo.task

import com.example.demo.mal.Anime
import com.example.demo.mal.Entry

interface Repository {
    fun create(searchQuery: String)

//    fun update(anime: Entry)

    fun findAll(): List<Anime>
//
//    fun findById(id: Long): Entry?
}