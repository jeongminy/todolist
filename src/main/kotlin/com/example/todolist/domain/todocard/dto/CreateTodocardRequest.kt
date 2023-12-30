package com.example.todolist.domain.todocard.dto

data class CreateTodocardRequest(
    val title: String,
    val description: String?,
    val author: String,
)
