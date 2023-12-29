package com.example.todolist.domain.user.model

import jakarta.persistence.*

@Entity
@Table(name="app_user")
class User(

    @Column(name="email")
    val email: String,

    @Column(name="password")
    val password: String,

    @Embedded
    var profile: Profile

){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}
