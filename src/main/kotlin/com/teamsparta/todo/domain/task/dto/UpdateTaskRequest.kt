package com.teamsparta.todo.domain.task.dto



data class UpdateTaskRequest (

    val username : String,
    val title : String,
    val description : String?,
    val dueDate : String,
    val completed : Boolean,

)