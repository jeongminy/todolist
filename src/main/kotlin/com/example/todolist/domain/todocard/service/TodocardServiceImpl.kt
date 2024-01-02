package com.example.todolist.domain.todocard.service

import com.example.todolist.domain.exception.ModelNotFoundException
import com.example.todolist.domain.todocard.dto.CreateTodocardRequest
import com.example.todolist.domain.todocard.dto.TodocardResponse
import com.example.todolist.domain.todocard.dto.UpdateTodocardRequest
import com.example.todolist.domain.todocard.model.Todocard
import com.example.todolist.domain.todocard.model.TodocardStatus
import com.example.todolist.domain.todocard.model.toResponse
import com.example.todolist.domain.todocard.repository.TodocardRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class TodocardServiceImpl(
    private val todocardRepository: TodocardRepository
): TodocardService {

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
        val todocard =
            todocardRepository.findByIdOrNull(todocardId) ?: throw ModelNotFoundException("todocard", todocardId)
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
        val todocard =
            todocardRepository.findByIdOrNull(todocardId) ?: throw ModelNotFoundException("todocard", todocardId)

        val (title, description, status) = request

        todocard.title = title
        todocard.description = description
        todocard.status = status

        return todocardRepository.save(todocard).toResponse()
    }

    @Transactional
    override fun deleteTodocard(todocardId: Long) {
        val todocard =
            todocardRepository.findByIdOrNull(todocardId) ?: throw ModelNotFoundException("todocard", todocardId)
        todocardRepository.delete(todocard)
    }

}




