package com.example.todolist.domain.todocard.controller

import com.example.todolist.domain.exception.ModelNotFoundException
import com.example.todolist.domain.todocard.dto.CreateTodocardRequest
import com.example.todolist.domain.todocard.dto.TodocardResponse
import com.example.todolist.domain.todocard.dto.UpdateTodocardRequest
import com.example.todolist.domain.todocard.service.TodocardService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RequestMapping("/todocards")//해당 메서드가 지정된 URL 경로로 들어오는 HTTP 요청을 처리하고, 그에 따른 응답을 생성한다는 것을 의미함.
@RestController //해당 클래스가 RESTful 웹 서비스에서 컨트롤러 역할을 한다는 의미함. (RESTful 서비스는 HTTP를 통해 자원을 관리하는 웹 서비스 디자인 패턴 중 하나)
class TodocardController( //웹 애플리케이션에서 할 일 카드 관련 요청을 처리하기 위한 컨트롤러 역할을 하며, 이를 위해 TodocardService라는 서비스를 활용
    private val todocardService: TodocardService
    //의존성 주입을 통해 서비스 객체를 받아와서 해당 서비스를 활용하여 비즈니스 로직을 수행하게 하고 컴포넌트 간의 관계를 느슨하게 만들
) {

    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    @GetMapping //특정 URL 경로로 들어오는 GET 요청을 해당 메서드에서 처리하고 그에 대한 응답을 생성한다는 것
    fun getTodocardList(
        @RequestParam(
            required = false,
            defaultValue = "ASC"
        ) order: String //할 일 목록 api에 작성일을 기준으로 오름차순, 내림차순 정렬하는 기능을 추가하기
        //@RequestParam은 HTTP 요청의 파라미터를 메서드의 매개변수에 바인딩하는 역할을 하고, URL의 쿼리 매개변수나 form 데이터를 받아와서 처리할 때 사용된다. 간단히 말하면, 조건을 나타내는 검색어나 필터를 입력받는 상자이다.
        //쿼리매개변수는 URL 뒤에 ?를 붙이고 key=value 형태로 추가된다.
        //클라이언트의 요청에 따라 정렬 순서를 결정하게 되는데, 만약 클라이언트가 정렬 순서를 제공하지 않으면 기본값 "ASC"를 사용한다. 클라이언트가 요청에 /todocards?order=DESC 와 같이, "DESC" 값을 포함하면 해당 값으로 정렬을 수행하게 됩니다.
        //required = false는 해당 매개변수가 필수가 아니라는 것을 나타내는 옵션이며, 클라이언트가 매개변수를 제공하지 않아도 메서드는 호출될 수 있다. 만약 명시하지 않으면, 해당 매개변수는 필수로 간주되어 요청에서 생략되면 스프링이 예외를 발생시킨다.

    ): ResponseEntity<List<TodocardResponse>> { //이 메서드는 할일 카드 목록을 반환한다. //반환 타입은 ResponseEntity<List<TodocardResponse>> 이다.

        return ResponseEntity //ResponseEntity를 사용하여 HTTP 응답을 생성한다.
            .status(HttpStatus.OK) //조회기능 성공시 200반환
            .body(todocardService.getAllTodocardList(order))
    }

    //할 일 목록 api에 작성자를 기준으로 필터하는 기능을 추가하기
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    @GetMapping("/author/{author}")
    fun getTodocardListByAuthor(
        @PathVariable author: String
    ): ResponseEntity<List<TodocardResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK) //조회기능 성공시 200반환
            .body(todocardService.getAllTodocardListByAuthor(author))
    }


    @GetMapping("/{todocardId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    fun getTodocard(
        @PathVariable todocardId: Long
    ): ResponseEntity<TodocardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK) //조회기능 성공시 200반환
            .body(todocardService.getTodocardById(todocardId))
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    fun creatTodocard(
        @Valid @RequestBody createTodocardRequest: CreateTodocardRequest //생성 실패시 400 반환 = GlobalExceptionHandler에서 예외 확인
    ): ResponseEntity<TodocardResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED) //작성기능 성공시 201반환
            .body(todocardService.createTodocard(createTodocardRequest))
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    @PutMapping("/{todocardId}")
    fun updateTodocard(
        @PathVariable todocardId: Long,
        @Valid @RequestBody updateTodocardRequest: UpdateTodocardRequest //수정 실패시 400 반환 = GlobalExceptionHandler에서 예외 확인
    ): ResponseEntity<TodocardResponse> {
        return ResponseEntity
            .status(HttpStatus.OK) //수정기능 성공시 200반환
            .body(todocardService.updateTodocard(todocardId, updateTodocardRequest))
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('MEMBER')")
    @DeleteMapping("/{todocardId}")
    fun deleteTodocard(
        @PathVariable todocardId: Long
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT) //삭제기능 성공시 204반환
            .build()
    }
}