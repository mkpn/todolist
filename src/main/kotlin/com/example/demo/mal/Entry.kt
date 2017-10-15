package com.example.demo.mal

import javax.xml.bind.annotation.*

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "entry")
class Entry {
    @XmlTransient
    var id: Long = 0
    @XmlElement(name = "id")
    var contentId: Long = 0
    lateinit var title: String
    lateinit var english: String
    lateinit var synonyms: String
    var episodes: Int = 0
    var score: Float = 0f
    lateinit var type: String
    @XmlElement(name = "start_date")
    lateinit var startDate: String
    @XmlElement(name = "end_date")
    lateinit var endDate: String
    lateinit var synopsis: String
    lateinit var image: String
    var chapters: Int = 0
    var volumes: Int = 0
}