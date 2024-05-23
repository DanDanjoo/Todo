package com.teamsparta.todo.domain.task.service

import com.teamsparta.todo.domain.task.dto.CreateTaskRequest
import com.teamsparta.todo.domain.task.dto.TaskResponse
import com.teamsparta.todo.domain.task.dto.UpdateTaskRequest
import com.teamsparta.todo.domain.task.model.Task


interface TaskService {

    fun getAllTaskList(): List<TaskResponse>

    fun getTaskById(taskId: Long): TaskResponse

    fun createTask(request: CreateTaskRequest): TaskResponse

    fun updateTask(taskId: Long, request: UpdateTaskRequest): TaskResponse

    fun deleteTask(taskId: Long)
}