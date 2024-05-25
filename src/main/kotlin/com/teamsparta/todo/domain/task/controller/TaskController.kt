package com.teamsparta.todo.domain.task.controller


import com.teamsparta.todo.domain.task.dto.CreateTaskRequest
import com.teamsparta.todo.domain.task.dto.TaskResponse
import com.teamsparta.todo.domain.task.dto.UpdateTaskRequest
import com.teamsparta.todo.domain.task.service.TaskService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*



@RestController
@RequestMapping("/api/v1/task")
@Tag(name = "Task", description = "Task management APIs")
class TaskController(
    private val taskService: TaskService

) {

    @PostMapping
    @Operation(summary = "Task 만들기", description = "새로운 Task 항목을 생성합니다.")
    fun createdTask(@RequestBody createTaskRequest: CreateTaskRequest): ResponseEntity<TaskResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(taskService.createTask(createTaskRequest))
    }

    @GetMapping
    @Operation(summary = "Task 모두 조회하기", description = "모든 Task 항목을 조회합니다.")
    fun getAlltaskList(): ResponseEntity<List<TaskResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(taskService.getAllTaskList())
    }

    @GetMapping("/{taskId}")
    @Operation(summary = "Task Id로 조회하기", description = "Id로 Task 항목을 조회합니다.")
    fun getTaskById(@PathVariable taskId: Long): ResponseEntity<TaskResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(taskService.getTaskById(taskId))
    }


    @PatchMapping("/{taskId}/complete")
    @Operation(summary = "Task 완료 처리하기", description = "Id로 Task 완료를 체크합니다.")
    fun completeTask(@PathVariable taskId: Long): ResponseEntity<Unit> {

        taskService.completeTask(taskId)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(null)
    }


    @PutMapping("/{taskId}")
    @Operation(summary = "Task Id로 수정하기", description = "Id로 Task 항목을 수정합니다.")
    fun updateTask(
        @PathVariable taskId: Long,
        @RequestBody updateTaskRequest: UpdateTaskRequest
    ): ResponseEntity<TaskResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(taskService.updateTask(taskId, updateTaskRequest))
    }
    @DeleteMapping("/{taskId}")
    @Operation(summary = "Task Id로 삭제하기", description = "Id로 Task 항목을 삭제합니다.")
    fun deleteTask(@PathVariable taskId: Long): ResponseEntity<Unit> {
        taskService.deleteTask(taskId)
        return ResponseEntity
            .status(HttpStatus.NO_CONTENT)
            .build()
    }
}