# ToDo List App - Jetpack Compose

Este é um aplicativo de lista de tarefas (ToDo List) simples, desenvolvido em Kotlin e utilizando Jetpack Compose, como parte de um exercício de desenvolvimento de aplicativos móveis.

O aplicativo permite ao usuário gerenciar suas atividades diárias, implementando as funcionalidades básicas de um CRUD (Create, Read, Update, Delete) em memória.

## Demo

<p align="center">
  <img src="https://github.com/user-attachments/assets/9bc27783-7c97-4635-8142-81a840c06d34" alt="Imagem 1" width="30%">
  <img src="https://github.com/user-attachments/assets/115595eb-32d7-4fbd-9406-2a5056f3656c" alt="Imagem 2" width="30%">
  <img src="https://github.com/user-attachments/assets/531a4180-768d-4307-9ed9-05df05b4c0a2" alt="Imagem 3" width="30%">
</p>

## Funcionalidades

O aplicativo implementa as seguintes funcionalidades:

* **Listagem de Tarefas:** Exibe todas as tarefas em uma lista rolável (`LazyColumn`).
* **Adição de Tarefas:** Um campo de texto (`TextField`) e um botão (`Button`) permitem adicionar novas tarefas à lista.
* **Marcar como Concluída:** Cada item da lista possui um `Checkbox` que permite ao usuário marcar a tarefa como concluída (ou pendente). Tarefas concluídas são exibidas com um estilo de texto riscado.
* **Exclusão de Tarefas:** Cada item possui um ícone de lixeira para remoção individual da lista.
* **Navegação:** O aplicativo utiliza o `NavHost` do Jetpack Navigation para navegar entre duas telas:
    1.  **Tela Principal (Lista):** Onde todas as tarefas são gerenciadas.
    2.  **Tela de Detalhes:** Acessível ao clicar em um `Card` de tarefa, exibe o título e o status da tarefa selecionada.
* **Gerenciamento de Estado:** O estado da aplicação (a lista de tarefas) é gerenciado por um `ViewModel`, garantindo que os dados sobrevivam a mudanças de configuração e que a lógica de negócios seja separada da UI.

## Tecnologias Utilizadas

* **Linguagem:** Kotlin
* **UI Toolkit:** Jetpack Compose
* **Componentes de UI:** `Scaffold`, `LazyColumn`, `Card`, `TextField`, `Button`, `Checkbox`, `IconButton`.
* **Arquitetura/Estado:** `ViewModel` com `MutableStateFlow` e `collectAsStateWithLifecycle`.
* **Navegação:** `Navigation-Compose` (`NavHost`, `navController`).

## Como Executar

1.  Clone este repositório:
    ```bash
    git clone [https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git](https://github.com/SEU_USUARIO/SEU_REPOSITORIO.git)
    ```
2.  Abra o projeto no [Android Studio](https://developer.android.com/studio).
3.  Aguarde o Gradle sincronizar as dependências (listadas no `build.gradle.kts`).
4.  Execute o aplicativo em um emulador ou dispositivo Android físico.

