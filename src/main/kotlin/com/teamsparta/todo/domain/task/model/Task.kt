package com.teamsparta.todo.domain.task.model

import com.teamsparta.todo.domain.comment.model.Comment
import com.teamsparta.todo.domain.task.dto.TaskResponse
import jakarta.persistence.*
import java.time.LocalDateTime


@Entity (name = "task")
class Task(

    @Column
    var username: String,

    @Column
    var title: String,

    @Column
    var description: String?,

    @Column
    var dueDate: String,

    @Column
    var createdAt: LocalDateTime = LocalDateTime.now(),

    @Column
    var updatedAt: LocalDateTime = LocalDateTime.now(),

    @OneToMany(mappedBy = "task", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var comment : MutableList<Comment> = mutableListOf(),



    ) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column
    private var completed: Boolean = false


    fun complete(){
        completed = true

    }


}

fun Task.toResponse(): TaskResponse {
    return TaskResponse(
        id = id!!,
        username = username,
        title = title,
        description = description,
        dueDate = dueDate,
        createdAt = createdAt,
        updateAt  = updatedAt
    )
}