Field Research Checklist App

Application Overview

This is a simple two-screen Android application built using Jetpack Compose.  
It allows users to maintain a checklist of field research tasks. Users can view their tasks, add new tasks, and see updates immediately.

---
Features

- Task List Screen (Main Screen)
  - Uses `Scaffold` with a `TopAppBar` titled "Field Research Checklist"
  - Displays tasks in a `LazyColumn`
  - FloatingActionButton (FAB) with an "Add" icon to navigate to the create screen

- Create Task Screen (Second Screen)
  - Uses `Scaffold` with a `TopAppBar` titled "Create New Task" and a back arrow
  - TextField for entering a new task
  - "Save Task" button adds the task to the list and navigates back

- State Management
  - `mutableStateListOf<String>()` in `MainActivity` acts as a single source of truth
  - State is passed down to the screens as needed

- Navigation
  - Uses `NavHost` with two routes: `"task_list"` and `"create_task"`
  - FAB navigates to `"create_task"`
  - Save button and back arrow navigate back to `"task_list"`

---

How to Run

1. Clone the repository:

```bash
git clone [https://github.com/maneeshayapa/mobile.git](https://github.com/maneeshayapa/mobile)
