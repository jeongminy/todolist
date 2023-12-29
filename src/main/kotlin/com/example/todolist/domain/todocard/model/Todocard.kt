package com.example.todolist.domain.todocard.model

import com.example.todolist.domain.comment.model.Comment
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

    @OneToMany(
        mappedBy = "comment",
        fetch = FetchType.LAZY,
        cascade = [CascadeType.ALL],
        orphanRemoval = true)
    var comment: MutableList<Comment> = mutableListOf()

){

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

}