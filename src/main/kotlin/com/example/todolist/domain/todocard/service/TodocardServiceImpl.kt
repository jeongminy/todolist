package com.example.todolist.domain.todocard.service

import com.example.todolist.domain.comment.dto.CommentResponse
import com.example.todolist.domain.comment.dto.CreatCommentRequest
import com.example.todolist.domain.comment.dto.DeleteCommentRequest
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
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class TodocardServiceImpl(
    private val todocardRepository: TodocardRepository,
    private val commentRepository: CommentRepository,
    private val encoder: PasswordEncoder
): TodocardService{


    // 할 일 목록 api에 작성일을 기준으로 오름차순, 내림차순 정렬하는 기능을 추가하기 (step3-1미션)
    override fun getAllTodocardList(order: String): List<TodocardResponse> {
        return if (order.toUpperCase() == "ASC") {
            todocardRepository.findAllByOrderByCreatedTimeAsc().map { it.toResponse() }
        } else {
            todocardRepository.findAllByOrderByCreatedTimeDesc().map { it.toResponse() }
        }
    }

    //할 일 목록 api에 작성자(이름을 포함하는)를 기준으로 필터하는 기능을 추가하기 (step3-2미션)
    override fun getAllTodocardListByAuthor(author: String): List<TodocardResponse> {
        return todocardRepository.findByAuthor("%author%").map { it.toResponse() }
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
                status = TodocardStatus.UNCOMPLETE, //댓글의 완료여부를 만들고, 기본값을 FALSE로 설정함 (step2-1미션)
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

        val encodedPassword = encoder.encode(request.commentPassword)

        val comment = Comment(
            comment = request.comment.toString(),
            author = request.author,
            commentPassword = encodedPassword //댓글을 작성할 때 '작성자 이름'과 '비밀번호'를 함께 받기 (step2-2미션)
        )
        return commentRepository.save(comment).toResponse()
    }

    @Transactional
    override fun updateComment(todocardId: Long, commentId: Long, request: UpdateCommentRequest): CommentResponse {
        val todocard = todocardRepository.findByIdOrNull(todocardId) ?: throw ModelNotFoundException("todocard", todocardId)
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)

        //작성자 이름과 비밀번호를 함께 받아 저장한 값과 일치하면 수정 가능 (step2-3미션)
        if (!encoder.matches(request.commentPassword, comment.commentPassword)) {
            throw IllegalArgumentException("댓글의 비밀번호가 일치하지 않습니다.")
        } else {comment.comment = request.comment}


        return commentRepository.save(comment).toResponse()
    }

    @Transactional
    override fun removeComment(todocardId: Long, commentId: Long, request: DeleteCommentRequest) {
        val todocard = todocardRepository.findByIdOrNull(todocardId) ?: throw ModelNotFoundException("todocard", todocardId)
        val comment = commentRepository.findByIdOrNull(commentId) ?: throw ModelNotFoundException("Comment", commentId)

        //작성자 이름과 비밀번호를 함께 받아 저장한 값과 일치하면 삭제 가능 (step2-4미션)
        if (!encoder.matches(request.commentPassword, comment.commentPassword)) {
            throw IllegalArgumentException("댓글의 비밀번호가 일치하지 않습니다.")
        } else {commentRepository.delete(comment)}



    }


//    3. 할 일 작성, 수정 api에 validation을 추가하기
//    ㄴ할 일을 작성하거나 수정할 때, 할일 제목이 1자 이상, 200자 이내인지 검사하기
//    ㄴ할 일 본문이 1자 이상 1000자 이하인지 검사하기
//    ㄴ조건을 충족하지 않는다면 기능 실패 응답하기


}