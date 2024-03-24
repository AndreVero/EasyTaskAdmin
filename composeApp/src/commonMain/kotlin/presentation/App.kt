@file:OptIn(ExperimentalLayoutApi::class)

package presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.components.DefaultTextField
import presentation.components.GoalComponent

@Composable
@Preview
fun App() {
    MaterialTheme {
        val presenter = remember { DefaultPresenter() }

        DisposableEffect(true) {
            onDispose { presenter.onDispose() }
        }

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimatedVisibility(presenter.state.goals.isNotEmpty()) {
                FlowRow(
                    horizontalArrangement = Arrangement.Start,
                    verticalArrangement = Arrangement.Top,
                    maxItemsInEachRow = 10,
                ) {
                    presenter.state.goals.forEach {
                        GoalComponent(it)
                    }
                }
            }
            DefaultTextField(
                hint = "Title",
                value = presenter.goalState.title,
                onValueChange = { value -> presenter.onGoalEvent(UpdateGoalTitle(value)) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            DefaultTextField(
                hint = "Description",
                value = presenter.goalState.description,
                onValueChange = { value -> presenter.onGoalEvent(UpdateGoalDescription(value)) }
            )
            Spacer(modifier = Modifier.height(8.dp))
            DefaultTextField(
                hint = "Icon",
                value = presenter.goalState.icon,
                onValueChange = { value -> presenter.onGoalEvent(UpdateGoalIcon(value)) }
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                shape = RoundedCornerShape(20.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Green.copy(alpha = 0.7f),
                    disabledBackgroundColor = Color.Green.copy(alpha = 0.3f),
                    contentColor = Color.White,
                    disabledContentColor = Color.White.copy(alpha = 0.5f)
                ),
                onClick = { presenter.addGoal() },
                enabled = presenter.state.isButtonEnabled,
                modifier = Modifier.width(200.dp)
            ) {
                Text(text = "Create goal")
            }
        }
    }
}