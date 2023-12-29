package com.example.todolist.domain.todocard.repository

import com.example.todolist.domain.todocard.model.Todocard
import org.springframework.data.jpa.repository.JpaRepository

interface TodocardRepository: JpaRepository<Todocard, Long> {
}