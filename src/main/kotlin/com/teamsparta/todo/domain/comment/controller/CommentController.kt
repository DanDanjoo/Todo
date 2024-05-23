package com.teamsparta.todo.domain.comment.controller

import com.teamsparta.todo.domain.comment.dto.AddCommentRequest
import com.teamsparta.todo.domain.comment.dto.CommentResponse
import com.teamsparta.todo.domain.comment.dto.RemoveCommentRequest
import com.teamsparta.todo.domain.comment.dto.UpdateCommentRequest
import com.teamsparta.todo.domain.task.service.TaskService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/task/{taskId}/comments")
@RestController
class CommentController(
    private val taskService: TaskService,
) {

    @PostMapping
    @Operation(summary = "Comment 작성하기", description = "새로운 Comment 를 작성합니다.")
    fun addComment(@PathVariable taskId: Long, addCommentRequest: AddCommentRequest): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(taskService.addComment(taskId, addCommentRequest))

    }

    @GetMapping
    @Operation(summary = "Comment 모두 조회하기", description = "모든 Comment 를 조회합니다.")
    fun getCommentList(@PathVariable taskId: Long) : ResponseEntity<List<CommentResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(taskService.getCommentList(taskId))
    }

    @GetMapping("/{commentId}")
    fun getComment(@PathVariable taskId: Long, @PathVariable commentId : Long): ResponseEntity<CommentResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(taskService.getComment(taskId, commentId))
    }

    @PutMapping("/{commentId}")
    fun updateComment(
        @PathVariable taskId: Long,
        @PathVariable commentId: Long,
        @RequestBody updateCommentRequest: UpdateCommentRequest
    ): ResponseEntity<CommentResponse> {
            return ResponseEntity
                .status(HttpStatus.OK)
                .body(taskService.updateComment(taskId, commentId, updateCommentRequest))

    }

    @DeleteMapping("/{commentId}")
    fun removeComment(
        @PathVariable taskId: Long,
        @PathVariable commentId: Long,
        @RequestBody removeCommentRequest: RemoveCommentRequest
    ): ResponseEntity<Unit> {
       taskService.removeComment(taskId, commentId, removeCommentRequest)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }

}