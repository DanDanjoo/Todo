package com.teamsparta.todo.domain.comment.dto

import java.time.LocalDateTime

data class CommentResponse (

    val username : String,
    val password : String,
    val content : String?,
    val createdAt : LocalDateTime,
    val updatedAt : LocalDateTime,
)