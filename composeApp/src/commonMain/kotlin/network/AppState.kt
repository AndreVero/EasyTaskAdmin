package network

import network.dto.GoalDto
import network.dto.TaskDto

data class AppState(
    val goals: List<GoalDto> = emptyList(),
    val tasks: List<TaskDto> = emptyList(),
)
