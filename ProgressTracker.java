public class ProgressTracker {

    public void updateTaskStatus(Task task, Status status) {
        task.setStatus(status);
    }

    public double calculateProjectProgress(Project project) {
        return project.calculateProgress();
    }
}