package com.example.demo

import javassist.NotFoundException
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

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

    @GetMapping("{id}/edit")
    fun edit(@PathVariable("id") id: Long,
             form: TaskUpdateForm): String {
        val task = taskRepository.findById(id) ?: throw NotFoundException()
        form.content = task.content
        form.done = task.done
        return "tasks/edit"
    }

    //htmlからのpost形式submitを受け取る部分も書くんだねー
    @PostMapping("") // postリクエストに反応する
    fun create(@Validated form: TaskCreateForm,
               bindingResult: BindingResult): String {
        // エラー時はまたtasks/newページに飛ぶ。エラーメッセージが表示される。
        if (bindingResult.hasErrors()) return "tasks/new"

        //　バリデーションを突破していてnullはありえないので、requireNotNullで強制変換変換しているらしい
        // "!!演算子"を使ってもいい
        val content = requireNotNull(form.content)
        taskRepository.create(content)
        // taskRepository.create(form.content!!)　←これでもok
        return "redirect:/tasks"
    }

    @PatchMapping("{id}") //patchリクエストに反応する
    fun update(@PathVariable("id") id: Long,
               @Validated form: TaskUpdateForm,
               bindingResult: BindingResult): String {
        if (bindingResult.hasErrors()) return "tasks/edit"

        val task = taskRepository.findById(id) ?: throw NotFoundException()
        val newTask = task.copy(content = requireNotNull(form.content), done = form.done)

        taskRepository.update(newTask)
        return "redirect:/tasks"
    }

    @ExceptionHandler(NotFoundException::class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleNotFoundException(): String = "tasks/not_found"

}