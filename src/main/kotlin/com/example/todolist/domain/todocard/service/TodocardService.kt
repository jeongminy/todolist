package com.example.todolist.domain.todocard.service

import com.example.todolist.domain.todocard.dto.CreateTodocardRequest
import com.example.todolist.domain.todocard.dto.TodocardResponse
import com.example.todolist.domain.todocard.dto.UpdateTodocardRequest

interface TodocardService {

    fun getAllTodocardList(): List<TodocardResponse>

    fun getTodocardById(todocardId: Long): TodocardResponse

    fun createTodocard(request: CreateTodocardRequest): TodocardResponse

    fun updateTodocard(todocardId: Long, request: UpdateTodocardRequest): TodocardResponse

    fun deleteTodocard(todocardId: Long): TodocardResponse

}