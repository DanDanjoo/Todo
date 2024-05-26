package com.teamsparta.todo.domain.task.dto

import jakarta.validation.constraints.Size


data class CreateTaskRequest (

    val username : String,

    @field:Size(min = 1, max = 200, message = "크기는 1부터 200까지입니다~")
    val title : String,

    @field:Size(min = 1, max = 1000, message = "크기는 1부터 1000까지입니다~")
    val description : String?,

    val dueDate : String,

)


