package com.example.todolist.domain.user.service

import com.example.todolist.domain.exception.ModelNotFoundException
import com.example.todolist.domain.user.dto.*
import com.example.todolist.domain.user.exception.InvalidCredentialException
import com.example.todolist.domain.user.model.Profile
import com.example.todolist.domain.user.model.User
import com.example.todolist.domain.user.model.UserRole
import com.example.todolist.domain.user.model.toResponse
import com.example.todolist.domain.user.repository.UserRepository
import com.example.todolist.infra.security.jwt.JwtPlugin
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtPlugin: JwtPlugin
): UserService {

    override fun login(request: LoginRequest): LoginResponse {
        val user = userRepository.findByEmail(request.email) ?: throw ModelNotFoundException("User", null) //이메일 체크

        if (user.role.name != request.role || !passwordEncoder.matches(request.password, user.password)) { //역할과 비밀번호 체크
            throw InvalidCredentialException()
        }

        return LoginResponse(
            accessToken = jwtPlugin.generateAccessToken(
                subject = user.id.toString(),
                email = user.email,
                role = user.role.name
            )
        )
    }

    @Transactional
    override fun signup(request: SignUpRequest): UserResponse {
        if (userRepository.existsByEmail(request.email)) {
            throw IllegalStateException("Email is already in use")
        }

        return userRepository.save(
            User(
                email = request.email,
                password = passwordEncoder.encode(request.password), // 암호화!
                profile = Profile(
                nickname = request.nickname),
                role = when (request.role) {
                    UserRole.ADMIN.name -> UserRole.ADMIN
                    UserRole.MEMBER.name -> UserRole.MEMBER
                    else -> throw IllegalArgumentException("Invalid role")
                }
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