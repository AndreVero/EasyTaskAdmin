package network.request

import kotlinx.serialization.Serializable

@Serializable
data class TaskRequest(
    val title: String = "",
    val description: String = "",
    val progress: Int = -1,
    val step: Int = -1,
    val goal_id: Int = -1,
    val is_last_step: Boolean = false,
)