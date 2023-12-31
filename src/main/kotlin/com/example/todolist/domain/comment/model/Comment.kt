package com.example.todolist.domain.comment.model

import com.example.todolist.domain.comment.dto.CommentResponse
import com.example.todolist.domain.todocard.model.Todocard
import jakarta.persistence.*

@Entity
@Table(name = "comment")
class Comment(

    @Column(name="comment")
    var comment: String,

    @Column(name="author")
    val author: String,

    @Column(name="comment_password")
    val commentPassword: String


){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @ManyToOne
    @JoinColumn(name="todocard_id")
    lateinit var todocard: Todocard

}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse(
        id = id!!,
        comment = comment,
        author=author
    )
}