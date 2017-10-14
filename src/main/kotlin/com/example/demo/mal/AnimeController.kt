package com.example.demo.task

import com.example.demo.mal.AnimeSearchForm
import com.example.demo.mal.SearchAnimeService
import javassist.NotFoundException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("anime")
class AnimeController(private val animeRepository: AnimeRepository) {
    @GetMapping("search")
    fun search(form: AnimeSearchForm): String {
        return "anime/search"
    }

    //htmlからのpost形式submitを受け取る部分も書くんだねー
    @PostMapping("") // postリクエストに反応する
    fun create(@Validated form: AnimeSearchForm,
               bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) return "anime/search"

        //　バリデーションを突破していてnullはありえないので、requireNotNullで強制変換変換しているらしい
        // "!!演算子"を使ってもいい
        val content = requireNotNull(form.title)
        animeRepository.create(content)
        return "redirect:/anime/search"
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(): String = "anime/not_found"
}