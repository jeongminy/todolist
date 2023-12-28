package com.example.todolist.domain.todocard.dto

import java.time.LocalDateTime

data class TodocardResponse(
    val id: Long, //할일 번호
    val title: String, //할일 제목
    val description: String?, //할일 내용
    val status: Boolean, //할일 완료 여부
    val createdTime: LocalDateTime = LocalDateTime.now(), //작성일
    val author: String //작성자
)
