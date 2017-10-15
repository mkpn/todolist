package com.example.demo.mal

import org.hibernate.validator.constraints.NotBlank
import javax.validation.constraints.Size

class MangaSearchForm {
    @NotBlank
    @Size(max = 20)
    var title: String? = null
}