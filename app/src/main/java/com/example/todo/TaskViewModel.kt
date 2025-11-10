package com.example.todo

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class TaskViewModel : ViewModel() {

    // MutableStateFlow para a lista de tarefas (privado)
    private val _tasks = MutableStateFlow<List<Task>>(emptyList())

    // StateFlow exposto para a UI (somente leitura)
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()

    // Contador para gerar IDs únicos (simula um auto-increment)
    private var nextId = 1

    init {
        // Carga inicial de dados estáticos (como solicitado)
        _tasks.value = listOf(
            Task(nextId++, "Estudar Jetpack Compose", false),
            Task(nextId++, "Comprar pão na padaria", true),
            Task(nextId++, "Levar o lixo para fora", false)
        )
    }

    /**
     * Adiciona uma nova tarefa à lista.
     */
    fun addTask(title: String) {
        if (title.isBlank()) return // Não adiciona tarefas vazias

        val newTask = Task(
            id = nextId++,
            title = title,
            isCompleted = false
        )
        // Adiciona a nova tarefa à lista existente
        _tasks.update { currentList -> currentList + newTask }
    }

    /**
     * Remove uma tarefa da lista.
     */
    fun removeTask(task: Task) {
        _tasks.update { currentList ->
            currentList.filterNot { it.id == task.id }
        }
    }

    /**
     * Alterna o estado (concluída/pendente) de uma tarefa.
     */
    fun toggleTaskCompleted(task: Task) {
        _tasks.update { currentList ->
            currentList.map {
                if (it.id == task.id) {
                    it.copy(isCompleted = !it.isCompleted)
                } else {
                    it
                }
            }
        }
    }

    /**
     * Busca uma tarefa específica pelo seu ID.
     * Usado pela tela de detalhes.
     */
    fun getTaskById(taskId: Int): Task? {
        return _tasks.value.find { it.id == taskId }
    }
}