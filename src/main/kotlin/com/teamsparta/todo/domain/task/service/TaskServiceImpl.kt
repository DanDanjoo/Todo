package com.teamsparta.todo.domain.task.service

import com.teamsparta.todo.domain.task.dto.CreateTaskRequest
import com.teamsparta.todo.domain.task.dto.TaskResponse
import com.teamsparta.todo.domain.task.dto.UpdateTaskRequest
import com.teamsparta.todo.domain.task.model.Task
import com.teamsparta.todo.domain.task.model.toResponse
import com.teamsparta.todo.domain.task.repository.TaskRepository
import org.springframework.stereotype.Service


@Service
class TaskServiceImpl(
     val taskRepository: TaskRepository,

    ): TaskService {
    override fun getAllTaskList(): List<TaskResponse> {
        return taskRepository.findAll().map { it.toResponse() }

    }

    override fun getTaskById(taskId: Long): TaskResponse {
        val task = taskRepository.findById(taskId) ?:
    }

    override fun createTask(request: CreateTaskRequest): TaskResponse {
        TODO("Not yet implemented")
    }

    override fun updateTask(taskId: Long, request: UpdateTaskRequest): TaskResponse {
        TODO("Not yet implemented")
    }

    override fun deleteTask(taskId: Long): Task {
        TODO("Not yet implemented")
    }
}