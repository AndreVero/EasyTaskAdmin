import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import easytaskadmin.composeapp.generated.resources.Res
import easytaskadmin.composeapp.generated.resources.compose_multiplatform

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    MaterialTheme {
        var showContent by remember { mutableStateOf(false) }
        val presenter = remember { DefaultPresenter() }

        DisposableEffect(true) {
            onDispose { presenter.onDispose() }
        }

        Column(
            Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                presenter.updateText()
                showContent = !showContent
            }) {
                Text("Click")
            }
            AnimatedVisibility(showContent) {

                Column(
                    Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                }
                LazyRow {
                    item {
                        LazyColumn {
                            items(presenter.state.goals) { goal ->
                                Text(goal.title)
                            }
                        }
                    }
                    item {
                        LazyColumn {
                            items(presenter.state.tasks) { task ->
                                Text(task.title)
                            }
                        }
                    }
                }
            }
        }
    }
}