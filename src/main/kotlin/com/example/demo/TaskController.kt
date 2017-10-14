package com.example.demo

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("tasks")
class TaskController(private val taskRepository: TaskRepository) {
    @GetMapping("")
    fun index(model: Model): String {
//        val tasks = listOf(
//                Task(1, "障子を貼り替える", false),
//                Task(2, "定期検診に行く", true)
//        )

        val tasks = taskRepository.findAll()
        model.addAttribute("tasks", tasks)
        return "tasks/index"
    }

    @GetMapping("new")
    fun new(form: TaskCreateForm): String {
        return "tasks/new"
    }

    //htmlからのpost形式submitを受け取る部分も書くんだねー
    @PostMapping("")
    fun create(@Validated form: TaskCreateForm,
               bindingResult: BindingResult): String {
        // エラー時にtasks/newを描画する処理になる
        if(bindingResult.hasErrors()) return "tasks/new"

        //　バリデーションを突破していてnullはありえないので、requireNotNullで強制変換変換しているらしい
        // "!!演算子"を使ってもいい
        val content = requireNotNull(form.content)
        taskRepository.create(content)
        // taskRepository.create(form.content!!)　←これでもok
        return "redirect:/tasks"
    }

}