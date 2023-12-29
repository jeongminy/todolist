package com.example.todolist.domain.comment.repository

import com.example.todolist.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {
}