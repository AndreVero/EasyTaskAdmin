package network.dto

import kotlinx.serialization.Serializable

@Serializable
data class GoalDto(
    val id: Int,
    val title: String,
    val description: String,
    val icon: String,
    val tasks: List<String>,
    val isActive: Boolean
)
