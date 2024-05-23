package com.teamsparta.todo.domain.task.model

import com.teamsparta.todo.domain.task.dto.TaskResponse
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.LocalDateTime


@Entity (name = "task")
class Task(

    @Column
    var username: String,

    @Column
    var title: String,

    @Column
    var description: String,

    @Column
    var dueDate: String,

    @Column
    var completed: Boolean,

    @Column (name = "date")
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column (name = "date")
    var updatedAt: LocalDateTime = LocalDateTime.now()

    ) {

    @Id @GeneratedValue
    var id: Long? = null
}

fun Task.toResponse(): TaskResponse {
    return TaskResponse(
        id = id!!,
        username = username,
        title = title,
        description = description,
        dueDate = dueDate,
        completed = completed,
        createdAt = createdAt,
        updateAt  = updatedAt
    )
}