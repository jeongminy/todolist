package com.example.todolist.domain.comment.controller

import com.example.todolist.domain.comment.dto.CommentResponse
import com.example.todolist.domain.comment.dto.CreatCommentRequest
import com.example.todolist.domain.comment.dto.UpdateCommentRequest
import com.example.todolist.domain.comment.service.CommentService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todocards/{todocardId}/comments")
@RestController
class CommentController(
    private val commentService: CommentService
) {

    @GetMapping
    fun getCommentList(@PathVariable todocardId: Long):ResponseEntity<List<CommentResponse>>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getCommentList(todocardId))
    }

    @GetMapping("/{commentId}")
    fun getComment(
        @PathVariable todocardId: Long,
        @PathVariable commentId: Long
    ):ResponseEntity<CommentResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.getComment(todocardId, commentId))
    }

    @PostMapping
    fun createComment(
        @PathVariable todocardId: Long,
        @RequestBody createCommentRequest: CreatCommentRequest
    ): ResponseEntity<CommentResponse>{
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(commentService.addComment(todocardId, createCommentRequest))
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable todocardId: Long,
        @PathVariable commentId: Long,
        @RequestBody updateCommentRequest: UpdateCommentRequest
    ): ResponseEntity<CommentResponse>{
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(commentService.updateComment(todocardId, commentId, updateCommentRequest))
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(
        @PathVariable todocardId: Long,
        @PathVariable commentId: Long,
        enteredCommentPassword: String
    ): ResponseEntity<Unit>{
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .body(commentService.removeComment(todocardId, commentId, enteredCommentPassword))
    }
}