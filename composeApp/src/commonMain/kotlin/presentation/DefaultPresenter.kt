package presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import co.touchlab.kermit.Logger
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import network.AppState
import network.dto.GoalDto
import network.dto.toGoal
import network.request.GoalRequest

class DefaultPresenter {

    companion object {
        const val BASE_URL = "http://127.0.0.1:5353"
    }

    private val scope = CoroutineScope(Dispatchers.Main)
    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    init {
        Logger.i("Presenter created")
        getGoals()
    }
    var state by mutableStateOf(AppState())
        private set

    var goalState by mutableStateOf(GoalRequest())
        private set

    fun onGoalEvent(event: GoalEvent) {
        when (event) {
            is UpdateGoalTitle -> updateTitle(event.title)
            is UpdateGoalDescription -> updateDescription(event.description)
            is UpdateGoalIcon -> updateIcon(event.icon)
        }
    }

    private fun updateTitle(title: String) {
        goalState = goalState.copy(title = title)
        updateButton()
    }

    private fun updateDescription(description: String) {
        goalState = goalState.copy(description = description)
        updateButton()
    }

    private fun updateIcon(icon: String) {
        goalState = goalState.copy(icon = icon)
        updateButton()
    }

    private fun updateButton() {
        state = state.copy(
            isButtonEnabled = goalState.icon.isNotBlank()
                    && goalState.title.isNotBlank()
                    && goalState.description.isNotBlank()
        )
    }

    fun addGoal() {
        scope.launch {
            client.post("$BASE_URL/goals") {
                contentType(ContentType.Application.Json)
                setBody(goalState)
            }
            getGoals()
            goalState = goalState.copy(title = "", description = "", icon = "")
        }
    }

    private fun getGoals() {
        scope.launch {
            val response = client.get("$BASE_URL/goals")
            val goals : List<GoalDto> = response.body()
            state = state.copy(goals = goals.map { it.toGoal() })
        }
    }

    fun onDispose() {
        scope.cancel()
    }
}