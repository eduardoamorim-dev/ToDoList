package com.example.todo

/**
 * Modelo de dados simples para uma tarefa.
 *
 * @param id Identificador único da tarefa.
 * @param title Descrição da tarefa.
 * @param isCompleted Status da tarefa (true = concluída, false = pendente).
 */
data class Task(
    val id: Int,
    val title: String,
    var isCompleted: Boolean = false
)