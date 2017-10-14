package com.example.demo.mal

import javax.xml.bind.annotation.XmlAccessType
import javax.xml.bind.annotation.XmlAccessorType
import javax.xml.bind.annotation.XmlElement

@XmlAccessorType(XmlAccessType.FIELD)
class AnimeList {
    @XmlElement(name = "entry") lateinit var entryList: List<Entry>
}