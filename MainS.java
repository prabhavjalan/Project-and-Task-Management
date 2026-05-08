import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Create project
        Project project = new Project("CS Project");

        // Create tasks
        Task t1 = new Task("UML Diagrams");
        Task t2 = new Task("Code Implementation");

        t1.setDeadline(LocalDate.now().plusDays(1));
        t2.setDeadline(LocalDate.now().plusDays(3));

        project.addTask(t1);
        project.addTask(t2);

        // Progress Tracking
        ProgressTracker tracker = new ProgressTracker();
        tracker.updateTaskStatus(t1, Status.COMPLETED);

        double progress = tracker.calculateProjectProgress(project);
        System.out.println("Project Progress: " + progress + "%");

        // Filtering
        List<Project> projects = new ArrayList<>();
        projects.add(project);

        Filter filter = new Filter();
        filter.sortAlphabetically(projects);

        System.out.println("Sorted Projects:");
        for (Project p : projects) {
            System.out.println(p.getName());
        }
    }
}