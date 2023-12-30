package com.example.todolist.domain.todocard.dto

import com.example.todolist.domain.todocard.model.TodocardStatus

data class UpdateTodocardRequest(
    val title: String,
    val description: String?,
    val status: TodocardStatus
)
