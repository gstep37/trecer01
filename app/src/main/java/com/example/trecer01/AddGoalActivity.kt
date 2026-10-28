package com.example.trecer01

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddGoalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_goal)

        val etTitle = findViewById<EditText>(R.id.etTitle)
        val etDescription = findViewById<EditText>(R.id.etDescription)
        val etDeadline = findViewById<EditText>(R.id.etDeadline)
        val btnSave = findViewById<Button>(R.id.btnSave)
        val btnBack = findViewById<Button>(R.id.btnBack)

        btnBack.setOnClickListener { finish() }

        btnSave.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val description = etDescription.text.toString().trim()
            val deadline = etDeadline.text.toString().trim()

            if (title.isEmpty()) {
                etTitle.error = "Введите название цели"
                return@setOnClickListener
            }
            if (title.length > 50) {
                etTitle.error = "Не более 50 символов"
                return@setOnClickListener
            }
            if (description.length > 200) {
                etDescription.error = "Не более 200 символов"
                return@setOnClickListener
            }
            if (deadline.isEmpty()) {
                etDeadline.error = "Укажите дедлайн"
                return@setOnClickListener
            }
            if (deadline.length > 20) {
                etDeadline.error = "Слишком длинная дата (макс. 20 символов)"
                return@setOnClickListener
            }

            try {
                GoalRepository.add(title, description, deadline)
                Toast.makeText(this, "Цель добавлена ", Toast.LENGTH_SHORT).show()
                finish()
            } catch (e: Exception) {
                Toast.makeText(this, "Ошибка: ${e.message}", Toast.LENGTH_LONG).show()
            }
        }
    }
}