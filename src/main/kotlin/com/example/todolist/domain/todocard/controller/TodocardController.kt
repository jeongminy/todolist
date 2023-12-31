package com.example.todolist.domain.todocard.controller

import com.example.todolist.domain.exception.ModelNotFoundException
import com.example.todolist.domain.todocard.dto.CreateTodocardRequest
import com.example.todolist.domain.todocard.dto.TodocardResponse
import com.example.todolist.domain.todocard.dto.UpdateTodocardRequest
import com.example.todolist.domain.todocard.service.TodocardService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todocards")
@RestController
class TodocardController(
    private val todocardService: TodocardService
) {

    //할 일 목록 api에 작성일을 기준으로 오름차순, 내림차순 정렬하는 기능을 추가하기 (step3-1미션)
    @GetMapping
    fun getTodocardList(
        @RequestParam(required = false, defaultValue = "ASC") order: String
    ): ResponseEntity<List<TodocardResponse>>{
        return ResponseEntity
            .status(HttpStatus.OK) //조회기능 성공시 200반환 (step3-4미션)
            .body(todocardService.getAllTodocardList(order))
    }

    //할 일 목록 api에 작성자를 기준으로 필터하는 기능을 추가하기 (step3-2미션)
    @GetMapping("/author/{author}")
    fun getTodocardListByAuthor(
        @PathVariable author: String
    ): ResponseEntity<List<TodocardResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK) //조회기능 성공시 200반환 (step3-4미션)
            .body(todocardService.getAllTodocardListByAuthor(author))
    }


    @GetMapping("/{todocardId}")
    fun getTodocard(
        @PathVariable todocardId: Long
    ): ResponseEntity<TodocardResponse>{
        return ResponseEntity
            .status(HttpStatus.OK) //조회기능 성공시 200반환 (step3-4미션)
            .body(todocardService.getTodocardById(todocardId))
    }

    @PostMapping
    fun creatTodocard(
        @Valid @RequestBody createTodocardRequest: CreateTodocardRequest //생성 실패시 400 반환 = GlobalExceptionHandler에서 예외 확인 (step3-4미션)
    ): ResponseEntity<TodocardResponse>{
        return ResponseEntity
            .status(HttpStatus.CREATED) //작성기능 성공시 201반환 (step3-4미션)
            .body(todocardService.createTodocard(createTodocardRequest))
    }

    @PutMapping("/{todocardId}")
    fun updateTodocard(
        @PathVariable todocardId: Long,
        @Valid @RequestBody updateTodocardRequest: UpdateTodocardRequest //수정 실패시 400 반환 = GlobalExceptionHandler에서 예외 확인 (step3-4미션)
    ): ResponseEntity<TodocardResponse>{
        return ResponseEntity
            .status(HttpStatus.OK) //수정기능 성공시 200반환 (step3-4미션)
            .body(todocardService.updateTodocard(todocardId, updateTodocardRequest))
    }

    @DeleteMapping("/{todocardId}")
    fun deleteTodocard(
        @PathVariable todocardId: Long
    ): ResponseEntity<Unit>{
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT) //삭제기능 성공시 204반환 (step3-4미션)
            .build()
    }
}