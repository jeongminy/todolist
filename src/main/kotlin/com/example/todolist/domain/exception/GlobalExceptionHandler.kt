package com.example.todolist.domain.exception

import com.example.todolist.domain.exception.dto.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    //todocardId가 없을 경우에 대한 예외처리
    @ExceptionHandler(ModelNotFoundException::class)
    fun handleModelNotFoundException(e: ModelNotFoundException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse("해당 todocard가 없습니다."))
    }

    //email 중복에 대한 예외처리
    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(e: IllegalStateException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.CONFLICT)
            .body(ErrorResponse("중복된 email이 있습니다."))
    }

    //할 일을 작성하거나 수정할 때, 할일 제목이 1자 이상, 200자 이내인지 검사하고 아닐시 예외 처리 (step3-3미션)
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val errors = e.bindingResult.allErrors.map { it.defaultMessage ?: "" }
        val response = ErrorResponse("할일 제목은 1자 이상, 200자 이내로 작성해야 합니다.")
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response)
    }

}