import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Task {

    private String name;
    private LocalDate deadline;
    private LocalDate creationDate;
    private Status status;
    private List<Tag> tags = new ArrayList<>();

    public Task() {
        this.creationDate = LocalDate.now();
        this.status = Status.NOT_STARTED;
    }

    public Task(String name, LocalDate deadline) {
        this.name = name;
        this.deadline = deadline;
        this.creationDate = LocalDate.now();
        this.status = Status.NOT_STARTED;
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

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
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

    public boolean isUrgent() {
        if (deadline == null) return false;
        long days = ChronoUnit.DAYS.between(LocalDate.now(), this.deadline);
        return days >= 0 && days <= 2;
    }
}
