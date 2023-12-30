package com.example.todolist.domain.todocard.model

import com.example.todolist.domain.comment.model.Comment
import com.example.todolist.domain.todocard.dto.TodocardResponse
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name="todocard")
class Todocard(

    @Column(name="title")
    var title: String,

    @Column(name="description")
    var description: String?,

    @Enumerated(EnumType.STRING)
    @Column(name="status")
    var status: TodocardStatus,

    @Column(name="created_time")
    val createdTime: LocalDateTime = LocalDateTime.now(),

    @Column(name="author")
    val author: String,

){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null


    @OneToMany(
        mappedBy = "comment",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        orphanRemoval = true)
    var comments: MutableList<Comment> = mutableListOf()



    fun removeComment(comment: Comment) {
        comments.remove(comment)
    }

}

fun Todocard.toResponse(): TodocardResponse {
    return TodocardResponse(
        id = id!!,
        title = title,
        description = description,
        status = status == TodocardStatus.COMPLETE,
        author = author
    )
}

