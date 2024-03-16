package network.dto

import kotlinx.serialization.Serializable

@Serializable
data class TaskDto(
    val id: Int,
    val title: String,
    val description: String,
    val progress: Int,
    val step: Int,
    val goal: GoalShortDto,
    val is_the_last_step: Boolean,
)