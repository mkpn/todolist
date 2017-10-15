package com.example.demo.task

import com.example.demo.mal.Anime
import com.example.demo.mal.AnimeSearchForm
import javassist.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("anime")
class AnimeController(private val animeRepository: Repository) {
    @GetMapping("search")
    fun search(form: AnimeSearchForm): String {
        return "anime/search"
    }

    @GetMapping("all.json")
    @ResponseBody
    fun all(form: AnimeSearchForm): List<Anime> {
        val animeList = animeRepository.findAll()
        return animeList
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