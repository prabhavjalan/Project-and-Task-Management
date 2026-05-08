import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Project implements Sortable {

    private String name;
    private LocalDate deadline;
    private LocalDate creationDate;
    private List<Task> tasks = new ArrayList<>();
    private List<Tag> tags = new ArrayList<>();

    public Project() {
        this.creationDate = LocalDate.now();
    }

    public Project(String name, LocalDate deadline) {
        this.name = name;
        this.deadline = deadline;
        this.creationDate = LocalDate.now();
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDeadline() {
        return this.deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public LocalDate getCreationDate() {
        return this.creationDate;
    }

    public List<Task> getTasks() {
        return this.tasks;
    }

    public void addTask(Task task) {
        this.tasks.add(task);
    }

    public void editTask(String name, Task data) {
        for (Task t : tasks) {
            if (t.getName() != null && t.getName().equals(name)) {
                if (data.getName() != null) t.setName(data.getName());
                if (data.getDeadline() != null) t.setDeadline(data.getDeadline());
                return;
            }
        }
    }

    public void removeTask(String name) {
        tasks.removeIf(t -> t.getName() != null && t.getName().equals(name));
    }

    public List<Tag> getTags() {
        return this.tags;
    }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }

    public double calculateProgress() {
        if (tasks.isEmpty()) return 0.0;
        int completed = 0;
        for (Task t : tasks) {
            if (t.getStatus() == Status.COMPLETED) {
                completed++;
            }
        }
        return ((double) completed / tasks.size()) * 100.0;
    }

    public boolean isUrgent() {
        if (deadline == null) return false;
        long days = ChronoUnit.DAYS.between(LocalDate.now(), this.deadline);
        return days >= 0 && days <= 2;
    }

    public boolean isOverdue() {
        if (deadline == null) return false;
        return LocalDate.now().isAfter(this.deadline);
    }
}
