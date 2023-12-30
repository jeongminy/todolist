package com.example.todolist.domain.todocard.service

import com.example.todolist.domain.comment.dto.CommentResponse
import com.example.todolist.domain.comment.dto.CreatCommentRequest
import com.example.todolist.domain.comment.dto.UpdateCommentRequest
import com.example.todolist.domain.comment.model.Comment
import com.example.todolist.domain.comment.model.toResponse
import com.example.todolist.domain.comment.repository.CommentRepository
import com.example.todolist.domain.exception.ModelNotFoundException
import com.example.todolist.domain.todocard.dto.CreateTodocardRequest
import com.example.todolist.domain.todocard.dto.TodocardResponse
import com.example.todolist.domain.todocard.dto.UpdateTodocardRequest
import com.example.todolist.domain.todocard.model.Todocard
import com.example.todolist.domain.todocard.model.TodocardStatus
import com.example.todolist.domain.todocard.model.toResponse
import com.example.todolist.domain.todocard.repository.TodocardRepository
import com.example.todolist.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class TodocardImpl(
    private val todocardRepository: TodocardRepository,
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository
): TodocardService{

    override fun getAllTodocardList(): List<TodocardResponse> {
        return todocardRepository.findAll().map{it.toResponse()}
    }

    override fun getTodocardById(todocardId: Long): TodocardResponse {
        val todocard = todocardRepository.findByIdOrNull(todocardId) ?: throw ModelNotFoundException("todocard",todocardId)
        return todocard.toResponse()
        //throw ModelNotFoundException(modelName="Todocard", id = 1L)
    }

    @Transactional
    override fun createTodocard(request: CreateTodocardRequest): TodocardResponse {
        return todocardRepository.save(
            Todocard(
                title = request.title,
                description = request.description,
                status = TodocardStatus.UNCOMPLETE,
                createdTime = LocalDateTime.now(),
                author = request.author
            )
        ).toResponse()
    }

    @Transactional
    override fun updateTodocard(todocardId: Long, request: UpdateTodocardRequest): TodocardResponse {
        val todocard = todocardRepository.findByIdOrNull(todocardId) ?: throw ModelNotFoundException("todocard", todocardId)

        val (title, description, status) = request

        todocard.title = title
        todocard.description = description
        todocard.status = status

        return todocardRepository.save(todocard).toResponse()
    }

    @Transactional
    override fun deleteTodocard(todocardId: Long) {
        val todocard = todocardRepository.findByIdOrNull(todocardId) ?: throw ModelNotFoundException("todocard", todocardId)
        todocardRepository.delete(todocard)
    }






    override fun getCommentList(todocardId: Long): List<CommentResponse> {
        val todocard = todocardRepository.findByIdOrNull(todocardId) ?: throw ModelNotFoundException("todocard", todocardId)
        return todocard.comments.map {it.toResponse()}
    }

    override fun getComment(todocardId: Long, commentId: Long): CommentResponse {
        val todocard = todocardRepository.findByIdOrNull(todocardId) ?: throw ModelNotFoundException("todocard", todocardId)
        val comment = commentRepository.findByTodocardIdAndId(todocardId, commentId) ?: throw ModelNotFoundException("Comment", commentId)

        return comment.toResponse()
    }

    @Transactional
    override fun addComment(todocardId: Long, request: CreatCommentRequest): CommentResponse {
        val todocard = todocardRepository.findByIdOrNull(todocardId) ?: throw ModelNotFoundException("todocard", todocardId)

        val comment = Comment(
            comment = request.comment.toString(),
            author = request.author
        )
        return commentRepository.save(comment).toResponse()
    }

    @Transactional
    override fun updateComment(todocardId: Long, commentId: Long, request: UpdateCommentRequest): CommentResponse {
        val todocard = todocardRepository.findByIdOrNull(todocardId) ?: throw ModelNotFoundException("todocard", todocardId)
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)

        comment.comment = request.comment

        return commentRepository.save(comment).toResponse()
    }

    @Transactional
    override fun removeComment(todocardId: Long, commentId: Long) {
        val todocard = todocardRepository.findByIdOrNull(todocardId) ?: throw ModelNotFoundException("todocard", todocardId)
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)

        commentRepository.delete(comment)
    }


//    3. 할 일 작성, 수정 api에 validation을 추가하기
//    ㄴ할 일을 작성하거나 수정할 때, 할일 제목이 1자 이상, 200자 이내인지 검사하기
//    ㄴ할 일 본문이 1자 이상 1000자 이하인지 검사하기
//    ㄴ조건을 충족하지 않는다면 기능 실패 응답하기


}