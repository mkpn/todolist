package com.example.demo.mal

data class Anime(val id: Long = 0, val contentId: Long, val title: String,
                 val english: String, val synonyms: String,
                 val episodes: Int, val score: Float,
                 val startDate: String, val endDate: String,
                 val synopsis: String, val image: String)