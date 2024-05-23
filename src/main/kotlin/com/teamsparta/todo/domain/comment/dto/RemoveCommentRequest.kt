package com.teamsparta.todo.domain.comment.dto

data class RemoveCommentRequest(

    val content: String?,
    val username: String,
    val password: String,
)
