interface TaskEvent

class UpdateTaskTitle(val title: String) : TaskEvent
class UpdateTaskDescription(val description: String) : TaskEvent
class UpdateTaskProgress(val progress: Int) : TaskEvent
class UpdateTaskStep(val step: Int) : TaskEvent
class UpdateTaskGoalId(val goalId: Int) : TaskEvent
class UpdateTaskIsLastStep(val isLastStep: Boolean) : TaskEvent
