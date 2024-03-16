interface GoalEvent

class UpdateGoalTitle(val title: String) : GoalEvent
class UpdateGoalDescription(val description: String) : GoalEvent
class UpdateGoalIcon(val icon: String) : GoalEvent