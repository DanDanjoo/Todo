package com.teamsparta.todo.domain.task.service

import com.teamsparta.todo.domain.exception.dto.ModelNotFoundException
import com.teamsparta.todo.domain.task.dto.CreateTaskRequest
import com.teamsparta.todo.domain.task.dto.TaskResponse
import com.teamsparta.todo.domain.task.dto.UpdateTaskRequest
import com.teamsparta.todo.domain.task.model.Task
import com.teamsparta.todo.domain.task.model.toResponse
import com.teamsparta.todo.domain.task.repository.TaskRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service


@Service
class TaskServiceImpl(
     val taskRepository: TaskRepository,

    ): TaskService {

    override fun getAllTaskList(): List<TaskResponse> {
        return taskRepository.findAll().map { it.toResponse() }

    }

    override fun getTaskById(taskId: Long): TaskResponse {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw ModelNotFoundException(taskId)

        return task.toResponse()
    }

    override fun createTask(request: CreateTaskRequest): TaskResponse {
        val task = Task(
            title = request.title,
            username = request.username,
            description = request.description,
            dueDate = request.dueDate,
            completed = request.completed,
            )

        return taskRepository.save(task).toResponse()
    }

    override fun updateTask(taskId: Long, request: UpdateTaskRequest): TaskResponse {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw ModelNotFoundException(taskId)

        task.title = request.title
        task.username = request.username
        task.description = request.description
        task.dueDate = request.dueDate
        task.completed = request.completed

// 슈퍼 더티 채킹맨
        return task.toResponse()
    }

    override fun deleteTask(taskId: Long) {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw ModelNotFoundException(taskId)
        taskRepository.delete(task)
    }
}