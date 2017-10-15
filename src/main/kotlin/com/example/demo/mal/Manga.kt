package com.example.demo.mal

data class Manga(val id: Long = 0, val contentId: Long, val title: String,
                 val english: String, val synonyms: String,
                 val chapters: Int, val volumes: Int, val score: Float,
                 val startDate: String, val endDate: String,
                 val synopsis: String, val image: String)