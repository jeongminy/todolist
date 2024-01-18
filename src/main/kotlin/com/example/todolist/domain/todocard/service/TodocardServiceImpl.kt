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

@Service //@Service 어노테이션이 적용된 서비스 클래스를 나타냅니다. 주로 비즈니스 로직을 처리하는 역할을 수행합니다.
class TodocardServiceImpl(
    private val todocardRepository: TodocardRepository //인터페이스를 사용하는 의존성 주입입니다. 이를 통해 데이터베이스에서 할 일 카드와 관련된 작업을 수행할 수 있습니다.
): TodocardService { //TodocardService 인터페이스를 구현합니다.

    // 할 일 목록 api에 작성일을 기준으로 오름차순, 내림차순 정렬하는 기능을 추가하기 (step3-1미션)
    override fun getAllTodocardList(order: String): List<TodocardResponse> { //TodocardService 인터페이스의 getAllTodocardList 메서드를 구현한 것
        return if (order.toUpperCase() == "ASC") {
            //컨트롤러를 통해 order 파라미터를 전달 받아와서 주어진 정렬 방식이 대문자로 "ASC"인지 확인하는 조건문입니다
            //대소문자를 무시하기 위해 toUpperCase()를 사용
            todocardRepository.findAllByOrderByCreatedTimeAsc().map { it.toResponse() }
            //TodocardRepository에서 정의한 쿼리 메서드 findAllByOrderByCreatedTimeAsc()를 사용하여 생성 시간을 기준으로 오름차순으로 모든 할 일 카드를 조회
        } else {
            todocardRepository.findAllByOrderByCreatedTimeDesc().map { it.toResponse() } //ASC가 아니라면 DESC로 내림차순으로 조회
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

        return todocard.toResponse()
    }

    @Transactional
    override fun deleteTodocard(todocardId: Long) {
        val todocard =
            todocardRepository.findByIdOrNull(todocardId) ?: throw ModelNotFoundException("todocard", todocardId)
        todocardRepository.delete(todocard)
    }

}




