package com.example.todolist.domain.comment.dto

data class CommentResponse(
    val id: Long,
    val comment: String?,
    val author: String
)
