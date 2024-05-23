package com.teamsparta.todo.domain.comment.dto

import java.time.LocalDateTime

data class CommentResponse (
    val id : Long,
    val content : String?,
    val username : String,
    val password : String,
    val createdAt : LocalDateTime,
    val updatedAt : LocalDateTime,
)