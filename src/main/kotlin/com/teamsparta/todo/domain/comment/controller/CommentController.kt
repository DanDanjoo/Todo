package com.teamsparta.todo.domain.comment.controller

import com.teamsparta.todo.domain.comment.dto.AddCommentRequest
import com.teamsparta.todo.domain.comment.dto.CommentResponse
import com.teamsparta.todo.domain.comment.dto.UpdateCommentRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/task/{taskId}/comments")
@RestController
class CommentController {

    @GetMapping
    fun getCommentList(@PathVariable TaskId: Long, commentId, Long) : ResponseEntity<List<CommentResponse>> {
        //TODO
    }

    @GetMapping("/{commentid}")
    fun getComment(@PathVariable TaskId: Long, @PathVariable commentId, Long): ResponseEntity<CommentResponse> {
        //TODO
    }

    @PostMapping
    fun addComment(@PathVariable TaskId: Long, addCommentRequest: AddCommentRequest): ResponseEntity<CommentResponse> {
        //TODO
    }

    @PutMapping("/{commentid}")
    fun updateComment(
        @PathVariable TaskId: Long,
        @PathVariable CommentId: Long,
        @RequestBody updateCommentRequest: UpdateCommentRequest
    ): ResponseEntity<CommentResponse> {
        //TODO
    }

    @DeleteMapping("/{commentid}")
    fun removeComment(@PathVariable TaskId: Long, @PathVariable CommentId: Long): ResponseEntity<Unit> {
        //TODO
    }

}