package com.example.todolist.domain.todocard.dto

data class UpdateTodocardRequest(
    val title: String,
    val description: String?,
    val status: Boolean
)
