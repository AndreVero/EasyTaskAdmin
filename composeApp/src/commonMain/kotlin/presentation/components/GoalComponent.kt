package presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import network.model.Goal

@Composable
fun GoalComponent(goal: Goal) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .width(240.dp)
            .background(Color.Green.copy(alpha = 0.7f), RoundedCornerShape(20.dp))
            .clip(RoundedCornerShape(20.dp))
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = goal.title,
            fontSize = 24.sp,
            color = Color.White
        )
        Text(
            text = goal.description,
            fontSize = 16.sp,
            color = Color.White
        )
        Canvas(modifier = Modifier.size(24.dp)){
            drawPath(
                goal.icon,
                Color.White
            )
        }
    }
}