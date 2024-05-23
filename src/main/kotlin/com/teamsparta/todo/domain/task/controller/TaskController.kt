package com.teamsparta.todo.domain.task.controller


import com.teamsparta.todo.domain.task.dto.CreateTaskRequest
import com.teamsparta.todo.domain.task.dto.TaskResponse
import com.teamsparta.todo.domain.task.dto.UpdateTaskRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*



@RestController
@RequestMapping("/api/v1/task")
@Tag(name = "Task", description = "Task management APIs")
class TaskController {

    @PostMapping
    @Operation(summary = "Task 만들기", description = "새로운 Task 항목을 생성합니다.")
    fun createdTask(@RequestBody createTaskRequest: CreateTaskRequest): ResponseEntity<TaskResponse> {
        TODO()
    }

    @GetMapping
    @Operation(summary = "Task 모두 조회하기", description = "모든 Task 항목을 조회합니다.")
    fun getAlltaskList(): ResponseEntity<List<TaskResponse>> {
        TODO()
    }

    @GetMapping("/{id}")
    @Operation(summary = "Task Id로 조회하기", description = "Id로 Task 항목을 조회합니다.")
    fun getTaskById(@PathVariable id: Long): ResponseEntity<TaskResponse> {
        TODO()
    }
    @PutMapping("/{id}")
    @Operation(summary = "Task Id로 수정하기", description = "Id로 Task 항목을 수정합니다.")
    fun updateTask(
        @PathVariable id: Long,
        @RequestBody updateTaskRequest: UpdateTaskRequest
    ): ResponseEntity<TaskResponse> {
        TODO()
    }
    @DeleteMapping("/{id}")
    @Operation(summary = "Task Id로 삭제하기", description = "Id로 Task 항목을 삭제합니다.")
    fun deleteTask(@PathVariable id: Long): ResponseEntity<Unit> {
        TODO()
    }
}