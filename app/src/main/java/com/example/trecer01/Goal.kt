package com.example.trecer01

data class Goal(
    val id: Long,
    val title: String,
    val description: String,
    val deadline: String,
    val isCompleted: Boolean = false
)