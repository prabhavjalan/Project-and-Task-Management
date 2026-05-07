import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Task {
    
    private String title;
    private String description;
    private LocalDateTime deadline;
    private LocalDateTime creationDate;
    private Status status;
    private List<String> tags = new ArrayList<>();

    public Task(String title, LocalDateTime deadline) {
        this.title = title;
        this.deadline = deadline;
        this.creationDate = LocalDateTime.now();
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDeadline() {
        return this.deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getCreationDate() {
        return this.creationDate;
    }

    public List<String> getTags() {
        return this.tags;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public void removeTag(String tag) {
        this.tags.remove(tag);
    }

    public boolean isOverdue() {
        LocalDateTime current = LocalDateTime.now();
        if (current.isAfter(this.deadline)) {
            return true;
        }
        else {
            return false;
        }
    }

    public boolean isUrgent() {
        LocalDateTime current = LocalDateTime.now();
        long hours = ChronoUnit.HOURS.between(current, this.deadline);
        if (hours <= 48) {
            return true;
        }
        else {
            return false;
        }
    }
}