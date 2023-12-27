package com.example.todolist.domain.todocard.controller

import com.example.todolist.domain.todocard.dto.CreateTodocardRequest
import com.example.todolist.domain.todocard.dto.TodocardResponse
import com.example.todolist.domain.todocard.dto.UpdateTodocardRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todocards")
@RestController
class TodocardController {

    @GetMapping
    fun getTodocardList(): ResponseEntity<List<TodocardResponse>>{
        TODO()
    }

    @GetMapping("/{todocardId}")
    fun getTodocard(@PathVariable todocardId: Long): ResponseEntity<TodocardResponse>{
        TODO()
    }

    @PostMapping
    fun creatTodocard(@RequestBody createTodocardRequest: CreateTodocardRequest
    ): ResponseEntity<TodocardResponse>{
        TODO()
    }

    @PutMapping("/{todocardId}")
    fun updateTodocard(@PathVariable todocardId: Long, @RequestBody updateTodocardRequest: UpdateTodocardRequest): ResponseEntity<TodocardResponse>{
        TODO()
    }

    @DeleteMapping("/{todocardId}")
    fun deleteTodocard(@PathVariable todocardId: Long){

    }

}