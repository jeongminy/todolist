package com.example.todolist.domain.comment.dto

data class CreatCommentRequest(
    val comment: String?,
    val author: String,
    val commentPassword: String
)
