package com.example.todolist.domain.todocard.service

import com.example.todolist.domain.todocard.dto.CreateTodocardRequest
import com.example.todolist.domain.todocard.dto.TodocardResponse
import com.example.todolist.domain.todocard.dto.UpdateTodocardRequest
import org.springframework.stereotype.Service

@Service
class TodocardImpl: TodocardService {
    override fun getAllTodocardList(): List<TodocardResponse> {
        //Todo: DB에서 모든 Todocard의 목록을 조회(Entity)하여 TodocardResponse(DTO) 목록으로 변환 후 반환
        //
        TODO("Not yet implemented")
    }

    override fun getTodocardById(todocardId: Long): TodocardResponse {
        //Todo: DB에서 Id기반으로 Todocard를 가져와서 TodocardResponse 목록으로 변환 후 반환
        TODO("Not yet implemented")
    }

    override fun createTodocard(request: CreateTodocardRequest): TodocardResponse {
        //Todo: request를 Todocard(엔티티)로 변환 후 DB에 저장
        TODO("Not yet implemented")
    }

    override fun updateTodocard(todocardId: Long, request: UpdateTodocardRequest): TodocardResponse {
        //TODO: DB에서 todocardId에 해당하는 Todocard(Entity)를 가져와서 request기반으로 업데이트 후 DB에 저장, 결과를 TodocardResponse(DTO)로 변환 후 반환
        TODO("Not yet implemented")
    }

    override fun deleteTodocard(todocardId: Long): TodocardResponse {
        //TODO: DB에서 todocardId에 해당하는 Todocard를 삭제 (한 row를 삭제), 연관된 comment도 모두 삭제
        TODO("Not yet implemented")
    }
}