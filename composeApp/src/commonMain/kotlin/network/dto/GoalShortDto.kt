package network.dto

import kotlinx.serialization.Serializable

@Serializable
data class GoalShortDto(
    val id: Int,
    val title: String,
    val description: String,
    val icon: String,
    val isActive: Boolean
)