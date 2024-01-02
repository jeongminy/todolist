package com.example.todolist.domain.comment.service

import com.example.todolist.domain.comment.dto.CommentResponse
import com.example.todolist.domain.comment.dto.CreatCommentRequest
import com.example.todolist.domain.comment.dto.UpdateCommentRequest

interface CommentService {

    fun getCommentList(todocardId: Long): List<CommentResponse>

    fun getComment(todocardId: Long, commentId: Long): CommentResponse

    fun addComment(todocardId: Long, request: CreatCommentRequest): CommentResponse

    fun updateComment(todocardId: Long, commentId: Long, request: UpdateCommentRequest): CommentResponse

    fun removeComment(todocardId: Long, commentId: Long, enteredCommentPassword:String)

}