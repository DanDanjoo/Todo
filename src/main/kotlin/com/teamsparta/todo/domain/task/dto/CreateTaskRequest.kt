package com.teamsparta.todo.domain.task.dto



data class CreateTaskRequest (

    val username : String,
    val title : String,
    val description : String?,
    val dueDate : String,

)


