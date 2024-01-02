package com.example.todolist.domain.comment.model

import com.example.todolist.domain.comment.dto.CommentResponse
import jakarta.persistence.*

@Entity
@Table(name = "comment")
class Comment(

    @Column(name = "comment")
    var comment: String,

    @Column(name = "author")
    val author: String,

    @Column(name = "commentPassword")
    val commentPassword: String,

    @Column(name = "todocard_id")
    val todocardId: Long

){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = id!!,
        comment = comment,
        author=author
    )
}