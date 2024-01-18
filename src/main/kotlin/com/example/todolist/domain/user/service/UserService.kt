package com.example.todolist.domain.user.service

import com.example.todolist.domain.user.dto.*

interface UserService {

    fun login(request: LoginRequest): LoginResponse

    fun signup(request:SignUpRequest): UserResponse

    fun updateUserProfile(userId: Long, request: UpdateUserProfileRequest): UserResponse

}