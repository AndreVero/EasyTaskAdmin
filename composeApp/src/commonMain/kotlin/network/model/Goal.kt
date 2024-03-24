package network.model

import androidx.compose.ui.graphics.Path

data class Goal(
    val id: Int,
    val title: String,
    val description: String,
    val icon: Path,
)