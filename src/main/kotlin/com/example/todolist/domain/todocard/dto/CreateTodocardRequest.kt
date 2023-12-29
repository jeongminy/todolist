package com.example.todolist.domain.todocard.dto

import java.time.LocalDateTime

data class CreateTodocardRequest(
    val title: String,
    val description: String?,
    val author: String,
)
