package network.request

import kotlinx.serialization.Serializable

@Serializable
data class GoalRequest(
    val title: String = "",
    val description: String = "",
    val icon: String = ""
)
