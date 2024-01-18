package com.example.todolist.domain.user.repository

import com.example.todolist.domain.user.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {

    fun existsByEmail(email: String): Boolean //이메일 중복을 확인하기 위해

    fun findByEmail(email: String) : User?
}