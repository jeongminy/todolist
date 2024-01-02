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

    @Enumerated(EnumType.STRING) //데이터베이스에 0, 1로 담기지 말고 "문자열" 형태로 저장 되도록!
    @Column(name="status")
    var status: TodocardStatus ,

    @Column(name="created_time")
    val createdTime: LocalDateTime = LocalDateTime.now(),

    @Column(name="author")
    val author: String,

    @OneToMany(
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        orphanRemoval = true)
    @JoinColumn(name = "todocard_id")
    var comments: MutableList<Comment> = mutableListOf()

){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Id를 자동생성 해주도록 JPA에 위임함. 자동적으로 id가 1씩증가하며 생성됨.
    var id: Long? = null



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

