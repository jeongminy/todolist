package com.example.todolist.domain.todocard.controller

import com.example.todolist.domain.exception.ModelNotFoundException
import com.example.todolist.domain.todocard.dto.CreateTodocardRequest
import com.example.todolist.domain.todocard.dto.TodocardResponse
import com.example.todolist.domain.todocard.dto.UpdateTodocardRequest
import com.example.todolist.domain.todocard.service.TodocardService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todocards")
@RestController
class TodocardController(
    private val todocardService: TodocardService
) {

    @GetMapping
    fun getTodocardList(): ResponseEntity<List<TodocardResponse>>{
        return ResponseEntity //이부분
            .status(HttpStatus.OK)
            .body(todocardService.getAllTodocardList())
    }

    @GetMapping("/{todocardId}")
    fun getTodocard(@PathVariable todocardId: Long): ResponseEntity<TodocardResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(todocardService.getTodocardById(todocardId))
    }

    @PostMapping
    fun creatTodocard(@RequestBody createTodocardRequest: CreateTodocardRequest
    ): ResponseEntity<TodocardResponse>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(todocardService.createTodocard(createTodocardRequest))
    }

    @PutMapping("/{todocardId}")
    fun updateTodocard(
        @PathVariable todocardId: Long,
        @RequestBody updateTodocardRequest: UpdateTodocardRequest
    ): ResponseEntity<TodocardResponse>{
        return ResponseEntity.status(HttpStatus.OK).body(todocardService.updateTodocard(todocardId, updateTodocardRequest))
    }

    @DeleteMapping("/{todocardId}")
    fun deleteTodocard(
        @PathVariable todocardId: Long
    ): ResponseEntity<Unit>{
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e: ModelNotFoundException): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .build()
    }

}