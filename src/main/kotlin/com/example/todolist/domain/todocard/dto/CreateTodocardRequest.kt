package com.example.todolist.domain.todocard.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class CreateTodocardRequest(

    //할 일을 작성하거나 수정할 때, 할일 제목이 1자 이상, 200자 이내인지 검사하고 아닐시 예외 처리 (step3-3미션)
    @field:NotBlank
    @field:Size(min = 1, max = 200)
    val title: String,

    val description: String?,
    val author: String,
)
