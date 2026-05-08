# Project and Task Management System

A Java Swing application for organizing projects, tasks, deadlines, and progress.

## Features

- Create, edit, and delete projects
- Add, edit, and delete tasks within a project
- Assign deadlines to projects and tasks (yyyy-mm-dd)
- Update task status: NOT_STARTED, IN_PROGRESS, COMPLETED
- Project progress shown as a percentage of completed tasks
- Tag projects or tasks with custom labels
- Urgent (due within 2 days) and overdue tasks are flagged automatically
- Search projects and tasks by name or keyword
- Sort projects and tasks by deadline, alphabetically, or by creation date

## How to Run

From the project folder:

```
javac *.java
java Main
```

This launches the main window. From there, all features are accessible via buttons.

## Project Structure

- **Domain classes** — `Project`, `Task`, `Tag`, `Status`, `Sortable`
- **Controllers** — `ProjectManager`, `Search`, `Filter`, `ProgressTracker`
- **GUI** — `MainGUI`, `ProjectGUI`, `EditProjectGUI`, `TaskGUI`, `EditTaskGUI`, `SearchGUI`, `FilterGUI`, `TagGUI`, `StatusGUI`
- **Entry point** — `Main`

## Team Members

- [Prabhav Jalan](https://github.com/prabhavjalan) 
- [Sally Hegab](https://github.com/sallyhegab)
- [Alyssa Chen](https://github.com/ac10164-norm)