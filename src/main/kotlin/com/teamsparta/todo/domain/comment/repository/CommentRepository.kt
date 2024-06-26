package com.teamsparta.todo.domain.comment.repository

import com.teamsparta.todo.domain.comment.model.Comment
import org.springframework.data.jpa.repository.JpaRepository

interface CommentRepository: JpaRepository<Comment, Long> {

    fun findAllByTaskId(taskId: Long): List<Comment>
    fun findByTaskIdAndId(taskId: Long, id: Long): Comment?

}