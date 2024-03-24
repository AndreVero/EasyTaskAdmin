package network

import network.model.Goal

data class AppState(
    val goals: List<Goal> = emptyList(),
    val isButtonEnabled: Boolean = false,
)
