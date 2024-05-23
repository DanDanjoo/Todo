package com.teamsparta.todo.domain.comment.model

import com.teamsparta.todo.domain.comment.dto.CommentResponse
import com.teamsparta.todo.domain.task.model.Task
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
@Table(name = "comment")
class Comment(

    @Column
    var username : String,

    @Column
    var password : String,

    @Column
    var content: String?,

    @Column
    var createdAt : LocalDateTime = LocalDateTime.now(),

    @Column
    var updatedAt : LocalDateTime = LocalDateTime.now(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    var task: Task

) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
}

fun Comment.toResponse(): CommentResponse {
    return CommentResponse (
        id = id!!,
        username = username,
        password = password,
        content = content,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}