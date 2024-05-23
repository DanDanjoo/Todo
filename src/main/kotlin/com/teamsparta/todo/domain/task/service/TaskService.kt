package com.teamsparta.todo.domain.task.service

import com.teamsparta.todo.domain.comment.dto.AddCommentRequest
import com.teamsparta.todo.domain.comment.dto.CommentResponse
import com.teamsparta.todo.domain.comment.dto.UpdateCommentRequest
import com.teamsparta.todo.domain.task.dto.CreateTaskRequest
import com.teamsparta.todo.domain.task.dto.TaskResponse
import com.teamsparta.todo.domain.task.dto.UpdateTaskRequest



interface TaskService {

    fun getAllTaskList(): List<TaskResponse>

    fun getTaskById(taskId: Long): TaskResponse

    fun createTask(request: CreateTaskRequest): TaskResponse

    fun updateTask(taskId: Long, request: UpdateTaskRequest): TaskResponse

    fun deleteTask(taskId: Long)

    fun getCommentList(taskId: Long): List<CommentResponse>

    fun getComment(taskId: Long, commentId: Long): CommentResponse

    fun addComment(taskId: Long, request : AddCommentRequest): CommentResponse

    fun updateComment(taskId: Long, commentId: Long, request : UpdateCommentRequest): CommentResponse

    fun removeComment(taskId: Long, commentId: Long)

}