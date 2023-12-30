package com.example.todolist.domain.user.service

import com.example.todolist.domain.exception.ModelNotFoundException
import com.example.todolist.domain.user.dto.SignUpRequest
import com.example.todolist.domain.user.dto.UpdateUserProfileRequest
import com.example.todolist.domain.user.dto.UserResponse
import com.example.todolist.domain.user.model.Profile
import com.example.todolist.domain.user.model.User
import com.example.todolist.domain.user.model.toResponse
import com.example.todolist.domain.user.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {
    @Transactional
    override fun signup(request: SignUpRequest): UserResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalStateException("Email is already in use")
        }

        return userRepository.save(
            User(
                email = request.email,
                password = request.password,
                profile = Profile(
                    nickname = request.nickname),
                )
        ).toResponse()
    }

    @Transactional
    override fun updateUserProfile(userId: Long, request: UpdateUserProfileRequest): UserResponse {
        val user = userRepository.findByIdOrNull(userId) ?: throw ModelNotFoundException("User", userId)

        user.profile = Profile(
            nickname = request.nickname
        )

        return userRepository.save(user).toResponse()
    }
}