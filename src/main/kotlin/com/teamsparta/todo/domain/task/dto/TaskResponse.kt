package com.teamsparta.todo.domain.task.dto

import java.time.LocalDateTime

data class TaskResponse (
    val id : Long,
    val username : String,
    val title : String,
    val description : String?,
    val dueDate : String,
    val createdAt : LocalDateTime,
    val updateAt  : LocalDateTime
)