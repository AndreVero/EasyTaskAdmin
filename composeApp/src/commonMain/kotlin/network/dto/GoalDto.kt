package network.dto

import androidx.compose.ui.graphics.vector.PathParser
import kotlinx.serialization.Serializable
import network.model.Goal

@Serializable
data class GoalDto(
    val id: Int,
    val title: String,
    val description: String,
    val icon: String,
    val tasks: List<String>,
    val isActive: Boolean
)

fun GoalDto.toGoal() : Goal {
    return Goal(
        id = this.id,
        title = this.title,
        description = this.description,
        icon = PathParser().parsePathString(icon).toPath()
    )
}
