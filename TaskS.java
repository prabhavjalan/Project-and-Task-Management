import java.time.LocalDate;

public class Task {
    private String name;
    private Status status;
    private LocalDate deadline;

    public Task(String name) {
        this.name = name;
        this.status = Status.NOT_STARTED;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }
}