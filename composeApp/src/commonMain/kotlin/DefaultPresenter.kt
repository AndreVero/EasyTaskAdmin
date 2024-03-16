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
import network.dto.TaskDto
import network.request.GoalRequest
import network.request.TaskRequest

class DefaultPresenter {

    private val scope = CoroutineScope(Dispatchers.Main)
    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }
    }

    init {
        Logger.i("Presenter created")
        getGoals()
        getTasks()
    }
    var state by mutableStateOf(AppState())
        private set

    var goalState by mutableStateOf(GoalRequest())
        private set

    var taskState by mutableStateOf(TaskRequest())
        private set


    fun updateText() {
        scope.launch {
            val response = client.get("https://jsonplaceholder.typicode.com/todos/1")
            val dto: TaskDto = response.body()
        }
    }

    fun onTaskEvent(event: TaskEvent) {
        when (event) {
            is UpdateTaskTitle -> taskState = taskState.copy(title = event.title)
            is UpdateTaskDescription -> taskState = taskState.copy(description = event.description)
            is UpdateTaskProgress -> taskState = taskState.copy(progress = event.progress)
            is UpdateTaskStep -> taskState = taskState.copy(step = event.step)
            is UpdateTaskGoalId -> taskState = taskState.copy(goal_id = event.goalId)
            is UpdateTaskIsLastStep -> taskState = taskState.copy(is_last_step = event.isLastStep)
        }
    }

    fun onGoalEvent(event: GoalEvent) {
        when (event) {
            is UpdateGoalTitle -> goalState = goalState.copy(title = event.title)
            is UpdateGoalDescription -> goalState = goalState.copy(description = event.description)
            is UpdateGoalIcon -> goalState = goalState.copy(icon = event.icon)
        }
    }

    fun addTask() {
        scope.launch {
            client.post("https://jsonplaceholder.typicode.com/todos/1") {
                contentType(ContentType.Application.Json)
                setBody(TaskRequest())
            }
            getTasks()
        }
    }

    fun addGoal() {
        scope.launch {
            client.post("https://jsonplaceholder.typicode.com/todos/1") {
                contentType(ContentType.Application.Json)
                setBody(GoalRequest())
            }
            getGoals()
        }
    }

    private fun getGoals() {
        scope.launch {
            val response = client.get("https://jsonplaceholder.typicode.com/todos/1")
            val goals : List<GoalDto> = response.body()
            state = state.copy(goals = goals)
        }
    }

    private fun getTasks() {
        scope.launch {
            val response = client.get("https://jsonplaceholder.typicode.com/todos/1")
            val tasks : List<TaskDto> = response.body()
            state = state.copy(tasks = tasks)
        }
    }

    fun onDispose() {
        scope.cancel()
    }
}