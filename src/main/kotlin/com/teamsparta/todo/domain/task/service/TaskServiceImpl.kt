package com.teamsparta.todo.domain.task.service

import com.teamsparta.todo.domain.comment.dto.AddCommentRequest
import com.teamsparta.todo.domain.comment.dto.CommentResponse
import com.teamsparta.todo.domain.comment.dto.RemoveCommentRequest
import com.teamsparta.todo.domain.comment.dto.UpdateCommentRequest
import com.teamsparta.todo.domain.comment.model.Comment
import com.teamsparta.todo.domain.comment.model.toResponse
import com.teamsparta.todo.domain.comment.repository.CommentRepository
import com.teamsparta.todo.domain.exception.dto.ModelNotFoundException
import com.teamsparta.todo.domain.task.dto.CreateTaskRequest
import com.teamsparta.todo.domain.task.dto.TaskResponse
import com.teamsparta.todo.domain.task.dto.UpdateTaskRequest
import com.teamsparta.todo.domain.task.model.Task
import com.teamsparta.todo.domain.task.model.toResponse
import com.teamsparta.todo.domain.task.repository.TaskRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class TaskServiceImpl(
    private val taskRepository: TaskRepository,
    private val commentRepository: CommentRepository,

    ): TaskService {

    override fun getAllTaskList(): List<TaskResponse> {
        return taskRepository.findAll().map { it.toResponse() }

    }

    override fun getTaskById(taskId: Long): TaskResponse {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw ModelNotFoundException(taskId)

        return task.toResponse()
    }
    @Transactional
    override fun createTask(request: CreateTaskRequest): TaskResponse {
        val task = Task(
            title = request.title,
            username = request.username,
            description = request.description,
            dueDate = request.dueDate,

            )

        return taskRepository.save(task).toResponse()
    }
    @Transactional
    override fun updateTask(taskId: Long, request: UpdateTaskRequest): TaskResponse {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw ModelNotFoundException(taskId)

        task.title = request.title
        task.username = request.username
        task.description = request.description
        task.dueDate = request.dueDate


    // 슈퍼 더티 채킹맨
        return task.toResponse()
    }
    @Transactional
    override fun deleteTask(taskId: Long) {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw ModelNotFoundException(taskId)
        taskRepository.delete(task)
    }

    @Transactional
    override fun completeTask(taskId: Long) {
        val task : Task =  taskRepository.findByIdOrNull(taskId) ?: throw ModelNotFoundException(taskId)

        task.complete()


    }

    override fun getCommentList(taskId: Long): List<CommentResponse> {
        taskRepository.findByIdOrNull(taskId) ?: throw ModelNotFoundException(taskId)

        return commentRepository.findAllByTaskId(taskId).sortedBy { it.createdAt }.map { it.toResponse() }

    }

    override fun getComment(taskId: Long, commentId: Long): CommentResponse {
        taskRepository.findByIdOrNull(taskId) ?: throw ModelNotFoundException(taskId)

        val comment = commentRepository.findByTaskIdAndId(taskId, commentId) ?: throw ModelNotFoundException(taskId)

        return  comment.toResponse()
    }

    @Transactional
    override fun addComment(taskId: Long, request: AddCommentRequest): CommentResponse {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw ModelNotFoundException(taskId)

        val comment = Comment(
            username = request.username,
            password = request.password,
            content = request.content,
            task = task
        )
        return commentRepository.save(comment).toResponse()
    }
    @Transactional
    override fun updateComment(taskId: Long, commentId: Long, request: UpdateCommentRequest): CommentResponse {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw ModelNotFoundException(taskId)
        val comment = commentRepository.findByTaskIdAndId(taskId, commentId) ?: throw ModelNotFoundException(commentId)


        comment.checkAuthentication(request.username, request.password)
        comment.changeContent(request.content)

        return commentRepository.save(comment).toResponse()

    }
    @Transactional
    override fun removeComment(taskId: Long, commentId: Long, request: RemoveCommentRequest) {
        val task = taskRepository.findByIdOrNull(taskId) ?: throw ModelNotFoundException(taskId)
        val comment = commentRepository.findByTaskIdAndId(taskId, commentId) ?: throw ModelNotFoundException(commentId)

        comment.checkAuthentication(request.username, request.password)

        commentRepository.deleteById(commentId)
    }
}