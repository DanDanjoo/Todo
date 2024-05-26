package com.teamsparta.todo.domain.task.repository

import com.teamsparta.todo.domain.task.model.Task
import org.springframework.data.jpa.repository.JpaRepository

interface TaskRepository: JpaRepository<Task, Long> {

    fun findAllByOrderByCreatedAtDesc() : List<Task>

    fun findAllByOrderByCreatedAtAsc() : List<Task>

    fun findAllByUsername(userName: String) : List<Task>
}