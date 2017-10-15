package com.example.demo.mal

import javassist.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("manga")
class MangaController(private val mangaRepository: MangaRepository) {
    @GetMapping("search")
    fun search(form: MangaSearchForm): String {
        return "manga/search"
    }

    @GetMapping("all.json")
    @ResponseBody
    fun all(form: MangaSearchForm): List<Manga> {
        val mangaList = mangaRepository.findAll()
        return mangaList
    }

    //htmlからのpost形式submitを受け取る部分も書くんだねー
    @PostMapping("") // postリクエストに反応する
    fun create(@Validated form: AnimeSearchForm,
               bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) return "manga/search"

        //　バリデーションを突破していてnullはありえないので、requireNotNullで強制変換変換しているらしい
        // "!!演算子"を使ってもいい
        val content = requireNotNull(form.title)
        mangaRepository.create(content)
        return "redirect:/manga/search"
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(): String = "anime/not_found"
}