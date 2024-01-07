package com.example.todolist.domain.todocard.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateTodocardRequest(

    val title: String,
    val description: String?,
    val author: String,
)
