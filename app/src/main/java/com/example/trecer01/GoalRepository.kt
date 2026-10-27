package com.example.trecer01

object GoalRepository {
    private val goals = mutableListOf<Goal>()
    private var nextId = 1L

    fun getAll(): List<Goal> = goals.toList()

    fun add(title: String, description: String, deadline: String): Goal {
        val goal = Goal(id = nextId++, title = title, description = description, deadline = deadline)
        goals.add(goal)
        return goal
    }

    fun getById(id: Long): Goal? = goals.find { it.id == id }

    fun toggleCompleted(id: Long) {
        val index = goals.indexOfFirst { it.id == id }
        if (index != -1) {
            goals[index] = goals[index].copy(isCompleted = !goals[index].isCompleted)
        }
    }
}