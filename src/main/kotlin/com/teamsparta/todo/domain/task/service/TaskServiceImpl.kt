package com.teamsparta.todo.domain.task.service

import com.teamsparta.todo.domain.task.dto.CreateTaskRequest
import com.teamsparta.todo.domain.task.dto.TaskResponse
import com.teamsparta.todo.domain.task.dto.UpdateTaskRequest
import com.teamsparta.todo.domain.task.model.Task
import org.springframework.stereotype.Service


@Service
class TaskServiceImpl: TaskService {
    override fun getAllTaskList(): List<TaskResponse> {
        TODO("Not yet implemented")
    }

    override fun getTaskById(taskId: Long): TaskResponse {
        TODO("Not yet implemented")
    }

    override fun createTask(request: CreateTaskRequest): TaskResponse {
        TODO("Not yet implemented")
    }

    override fun updateTask(taskId: Long, request: UpdateTaskRequest): TaskResponse {
        TODO("Not yet implemented")
    }

    override fun deleteTaskById(taskId: Long): Task {
        TODO("Not yet implemented")
    }
}