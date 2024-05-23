package com.teamsparta.todo.domain.exception.dto

class ModelNotFoundException( val id: Long): RuntimeException(
     "Task Not Found With Given Id : $id"
)