package com.example.todolist.domain.user.service

import com.example.todolist.domain.user.dto.SignUpRequest
import com.example.todolist.domain.user.dto.UpdateUserProfileRequest
import com.example.todolist.domain.user.dto.UserResponse

interface UserService {

    fun signup(request:SignUpRequest): UserResponse

    fun updateUserProfile(userId: Long, request: UpdateUserProfileRequest): UserResponse

}