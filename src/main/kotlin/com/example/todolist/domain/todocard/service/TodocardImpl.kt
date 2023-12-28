package com.example.todolist.domain.todocard.service

import com.example.todolist.domain.comment.dto.CommentResponse
import com.example.todolist.domain.comment.dto.CreatCommentRequest
import com.example.todolist.domain.comment.dto.UpdateCommentRequest
import com.example.todolist.domain.exception.ModelNotFoundException
import com.example.todolist.domain.todocard.dto.CreateTodocardRequest
import com.example.todolist.domain.todocard.dto.TodocardResponse
import com.example.todolist.domain.todocard.dto.UpdateTodocardRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TodocardImpl: TodocardService {
    override fun getAllTodocardList(): List<TodocardResponse> {
        //TODO: DB에서 모든 Todocard의 목록을 조회(Entity)하여 TodocardResponse(DTO) 목록으로 변환 후 반환
        TODO("Not yet implemented")
    }

    override fun getTodocardById(todocardId: Long): TodocardResponse {
        //TODO: 만약 todocardId에 해당하는 Course가 없다면 throw ModelNotFoundException
        //TODO: DB에서 Id기반으로 Todocard를 가져와서 TodocardResponse 목록으로 변환 후 반환
        TODO("Not yet implemented")
        //throw ModelNotFoundException(modelName="Todocard", id = 1L)
    }

    @Transactional
    override fun createTodocard(request: CreateTodocardRequest): TodocardResponse {
        //TODO: request를 Todocard(엔티티)로 변환 후 DB에 저장
        TODO("Not yet implemented")
    }

    @Transactional
    override fun updateTodocard(todocardId: Long, request: UpdateTodocardRequest): TodocardResponse {
        //TODO: 만약 todocardId에 해당하는 Course가 없다면 throw ModelNotFoundException
        //TODO: DB에서 todocardId에 해당하는 Todocard(Entity)를 가져와서 request기반으로 업데이트 후 DB에 저장, 결과를 TodocardResponse(DTO)로 변환 후 반환
        TODO("Not yet implemented")
    }

    @Transactional
    override fun deleteTodocard(todocardId: Long): TodocardResponse {
        //TODO: 만약 todocardId에 해당하는 Course가 없다면 throw ModelNotFoundException
        //TODO: DB에서 todocardId에 해당하는 Todocard를 삭제 (한 row를 삭제), 연관된 comment도 모두 삭제
        TODO("Not yet implemented")
    }






    override fun getCommentList(todocardId: Long): List<CommentResponse> {
        // TODO: 만약 todocardId 해당하는 todocard가 없다면 throw ModelNotFoundException
        // TODO: DB에서 todocardId에 해당하는 todocard목록을 가져오고, 하위 comment들을 가져온 다음, CommentResopnse로 변환해서 반환

        TODO("Not yet implemented")
    }

    override fun getComment(todocardId: Long, commentId: Long): CommentResponse {
        // TODO: 만약 todocardId, commentId에 해당하는 Comment가 없다면 throw ModelNotFoundException
        // TODO: DB에서 todocardId, commentId에 해당하는 Comment를 가져와서 CommentResponse로 변환 후 반환

        TODO("Not yet implemented")
    }

    @Transactional
    override fun addComment(todocardId: Long, request: CreatCommentRequest): CommentResponse {
        // TODO: 만약 todocardId에 해당하는 todocard가 없다면 throw ModelNotFoundException
        // TODO: DB에서 todocardId에 해당하는 todocard를 가져와서 Comment를 추가 후 DB에 저장, 결과를을 CommentResponse로 변환 후 반환

        TODO("Not yet implemented")
    }

    @Transactional
    override fun updateComment(todocardId: Long, commentId: Long, request: UpdateCommentRequest): CommentResponse {
        // TODO: 만약 todocardId, commentId에 해당하는 Comment가 없다면 throw ModelNotFoundException
        /* TODO: DB에서 todocardId, commentId에 해당하는 Comment를 가져와서
            request로 업데이트 후 DB에 저장, 결과를을 CommentResponse로 변환 후 반환 */

        TODO("Not yet implemented")
    }

    @Transactional
    override fun removeComment(todocardId: Long, commentId: Long) {
        // TODO: 만약 todocardId에 해당하는 todocard가 없다면 throw ModelNotFoundException
        // TODO: DB에서 todocardId, commentId에 해당하는 comment를 가져오고, 삭제

        TODO("Not yet implemented")
    }


//    3. 할 일 작성, 수정 api에 validation을 추가하기
//    ㄴ할 일을 작성하거나 수정할 때, 할일 제목이 1자 이상, 200자 이내인지 검사하기
//    ㄴ할 일 본문이 1자 이상 1000자 이하인지 검사하기
//    ㄴ조건을 충족하지 않는다면 기능 실패 응답하기




//    @Transactional
//    override fun applyCourse(courseId: Long, request: ApplyCourseRequest): CourseApplicationResponse {
//        //만약 courseId에 해당하는 Course가 없다면 throw ModelNotFoundException
//        //만약 course가 이미 마감됐다면, throw IllegalStateException
//        //이미 신청했다면, throw IllegalStateException
//
//    }
}