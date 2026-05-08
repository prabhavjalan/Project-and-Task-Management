import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Project {
    private String name;
    private LocalDate creationDate;
    private List<Task> tasks;

    public Project(String name) {
        this.name = name;
        this.creationDate = LocalDate.now();
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public String getName() {
        return name;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public double calculateProgress() {
        if (tasks.isEmpty()) return 0;

        int completed = 0;
        for (Task t : tasks) {
            if (t.getStatus() == Status.COMPLETED) {
                completed++;
            }
        }

        return (double) completed / tasks.size() * 100;
    }
}