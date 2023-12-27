package com.example.todolist.domain.user.controller

import com.example.todolist.domain.user.dto.SignUpRequest
import com.example.todolist.domain.user.dto.UpdateUserProfileRequest
import com.example.todolist.domain.user.dto.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UserController {
    @PostMapping("/signup")
    fun signUp(@RequestBody signUpRequest: SignUpRequest): ResponseEntity<UserResponse> {
        TODO()
    }
    @PutMapping("/users/{userId}/profile")
    fun updateUserProfile(@PathVariable userId: Long,
                          @RequestBody updateUserProfileRequest: UpdateUserProfileRequest
    ): ResponseEntity<UserResponse> {
        TODO()
    }
}