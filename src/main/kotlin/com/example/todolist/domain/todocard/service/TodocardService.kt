package com.example.todolist.domain.todocard.service

import com.example.todolist.domain.comment.dto.CommentResponse
import com.example.todolist.domain.comment.dto.CreatCommentRequest
import com.example.todolist.domain.comment.dto.UpdateCommentRequest
import com.example.todolist.domain.todocard.dto.CreateTodocardRequest
import com.example.todolist.domain.todocard.dto.TodocardResponse
import com.example.todolist.domain.todocard.dto.UpdateTodocardRequest

interface TodocardService {

    fun getAllTodocardList(): List<TodocardResponse>

    fun getTodocardById(todocardId: Long): TodocardResponse

    fun createTodocard(request: CreateTodocardRequest): TodocardResponse

    fun updateTodocard(todocardId: Long, request: UpdateTodocardRequest): TodocardResponse

    fun deleteTodocard(todocardId: Long)



    fun getCommentList(todocardId: Long): List<CommentResponse>

    fun getComment(todocardId: Long, commentId: Long): CommentResponse

    fun addComment(todocardId: Long, request: CreatCommentRequest): CommentResponse

    fun updateComment(todocardId: Long, commentId: Long, request: UpdateCommentRequest): CommentResponse

    fun removeComment(todocardId: Long, commentId: Long)

}