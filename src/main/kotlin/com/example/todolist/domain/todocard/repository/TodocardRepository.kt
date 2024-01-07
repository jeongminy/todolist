package com.example.todolist.domain.todocard.repository

import com.example.todolist.domain.todocard.model.Todocard
import org.springframework.data.jpa.repository.JpaRepository

interface TodocardRepository: JpaRepository<Todocard, Long> { //TodocardRepository는 JpaRepository<Todocard, Long>를 상속하고 있습니다.
    //JpaRepository는 Spring Data JPA에서 제공하는 인터페이스로, Todocard 엔티티 클래스에 대한 JPA 리포지토리를 정의하는 데 사용
    //<Todocard, Long>은 제네릭 타입 매개변수로, 첫 번째 매개변수인 Todocard는 엔티티의 타입을, 두 번째 매개변수인 Long은 엔티티의 기본 키(PK) 타입을 나타냅니다.

    fun findAllByOrderByCreatedTimeAsc(): List<Todocard>
    fun findAllByOrderByCreatedTimeDesc(): List<Todocard>
    fun findByAuthor(author: String): List<Todocard>
}