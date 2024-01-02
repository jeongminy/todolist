package com.example.todolist.domain.comment.service

import com.example.todolist.domain.comment.dto.CommentResponse
import com.example.todolist.domain.comment.dto.CreatCommentRequest
import com.example.todolist.domain.comment.dto.UpdateCommentRequest
import com.example.todolist.domain.comment.model.Comment
import com.example.todolist.domain.comment.model.toResponse
import com.example.todolist.domain.comment.repository.CommentRepository
import com.example.todolist.domain.exception.InvalidPasswordException
import com.example.todolist.domain.exception.ModelNotFoundException
import com.example.todolist.domain.todocard.repository.TodocardRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CommentServiceImpl(
    private val todocardRepository: TodocardRepository,
    private val commentRepository: CommentRepository
): CommentService {

    override fun getCommentList(todocardId: Long): List<CommentResponse> {
        val todocard = todocardRepository.findByIdOrNull(todocardId) ?: throw ModelNotFoundException("todocard", todocardId)
        return todocard.comments.map {it.toResponse()}
    }

    override fun getComment(todocardId: Long, commentId: Long): CommentResponse { val todocard = todocardRepository.findByIdOrNull(todocardId) ?: throw ModelNotFoundException("todocard", todocardId)
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)

        return comment.toResponse()
    }

    @Transactional
    override fun addComment(todocardId: Long, request: CreatCommentRequest): CommentResponse {
        val todocard = todocardRepository.findByIdOrNull(todocardId) ?: throw ModelNotFoundException("todocard", todocardId)

        val comment = Comment(
            comment = request.comment.toString(),
            author = request.author,
            commentPassword = request.commentPassword,
            todocardId = todocardId
        )

        return commentRepository.save(comment).toResponse()
    }

    @Transactional
    override fun updateComment(todocardId: Long, commentId: Long, request: UpdateCommentRequest): CommentResponse {
        val todocard = todocardRepository.findByIdOrNull(todocardId) ?: throw ModelNotFoundException("todocard", todocardId)
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)


        if (comment.commentPassword != request.enteredCommentPassword) {
            throw InvalidPasswordException("비밀번호가 일치하지 않습니다.")
        } else {comment.comment = request.comment}

        return commentRepository.save(comment).toResponse()
    }

    @Transactional
    override fun removeComment(todocardId: Long, commentId: Long, enteredCommentPassword:String) {
        val todocard = todocardRepository.findByIdOrNull(todocardId) ?: throw ModelNotFoundException("todocard", todocardId)
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)

        if (comment.commentPassword != enteredCommentPassword) {
            throw InvalidPasswordException("비밀번호가 일치하지 않습니다.")
        } else {
            return commentRepository.delete(comment)
        }
    }
}