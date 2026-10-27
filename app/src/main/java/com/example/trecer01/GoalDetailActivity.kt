package com.example.trecer01

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class GoalDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_goal_detail)

        val goalId = intent.getLongExtra("goalId", -1L)
        val goal = GoalRepository.getById(goalId)

        if (goal == null) {
            Toast.makeText(this, "Цель не найдена", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        findViewById<TextView>(R.id.tvTitle).text = goal.title
        findViewById<TextView>(R.id.tvDescription).text =
            goal.description.ifEmpty { "Без описания" }
        findViewById<TextView>(R.id.tvDeadline).text = "Дедлайн: ${goal.deadline}"
        findViewById<TextView>(R.id.tvStatus).text =
            if (goal.isCompleted) "✅ Выполнено" else "⏳ В процессе"

        findViewById<Button>(R.id.btnBack).setOnClickListener { finish() }

        findViewById<Button>(R.id.btnToggle).setOnClickListener {
            GoalRepository.toggleCompleted(goal.id)
            finish()
        }
    }
}