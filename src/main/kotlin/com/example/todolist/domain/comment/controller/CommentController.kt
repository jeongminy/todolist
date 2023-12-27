package com.example.todolist.domain.comment.controller

import com.example.todolist.domain.comment.dto.CommentResponse
import com.example.todolist.domain.comment.dto.CreatCommentRequest
import com.example.todolist.domain.comment.dto.UpdateCommentRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/todocards/{todocard}/comments")
@RestController
class CommentController {

    @GetMapping
    fun getCommentList():ResponseEntity<List<CommentResponse>>{
        TODO()
    }

    @GetMapping("/{commentId}")
    fun getComment(@PathVariable commentId: Long){
        TODO()
    }

    @PostMapping
    fun createComment(@RequestBody createCommentRequest: CreatCommentRequest): ResponseEntity<CommentResponse>{
        TODO()
    }

    @PutMapping("/{commentId}")
    fun updateComment(@PathVariable commentId: Long, @RequestBody updateCommentRequest: UpdateCommentRequest): ResponseEntity<CommentResponse>{
        TODO()
    }

    @DeleteMapping("/{commentId}")
    fun deleteComment(@PathVariable commentId: Long){
        TODO()
    }
}