package com.example.todolist.domain.comment.model

import com.example.todolist.domain.todocard.model.Todocard
import jakarta.persistence.*

@Entity
@Table(name = "comment")
class Comment(

    @Column(name="comment")
    var comment: String,

    @Column(name="author")
    val author: String,

    @ManyToOne
    @JoinColumn(name="todocard_id")
    var todocard: Todocard

){
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}