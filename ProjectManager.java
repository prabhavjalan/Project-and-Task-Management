import java.time.LocalDateTime;
import java.time.format.*;
import java.util.*;

public class ProjectManager {

    private static List<Project> projects = new ArrayList<>();

    public void createProject(String title, String deadline) {
        deadline += " 00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(deadline, formatter);
        Project project = new Project(title, dateTime);
        projects.add(project);
    }

    public void editProject(Project project, String title, String deadline, String description) {
        project.setTitle(title);
        deadline += " 00:00";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(deadline, formatter);
        project.setDeadline(dateTime);
        project.setDescription(description);
    }

    public void deleteProject(Project project) {
        projects.remove(project);
    }

    public List<Project> getProjects() {
        return ProjectManager.projects;
    }

    public List<Object> search(String keyword) {
        return null;
    }

    public List<Project> sortProjects(SortCriteria criteria) {
        return null;
    }

    public List<Project> filterProjects(FilterCriteria criteria) {
        return null;
    }

    public List<Task> getOverdueTasks(Project project) {
        List<Task> overdue = new ArrayList<>();
        List<Task> all = project.getTasks();
        for (int i = 0; i < all.size() ; i++) {
            Task cur = all.get(i);
            if (cur.isOverdue()) {
                overdue.add(cur);
            }            
        }
        return overdue;
    }

    public List<Task> getUpcomingTasks(Project project) {
        List<Task> upcoming = new ArrayList<>();
        List<Task> all = project.getTasks();
        for (int i = 0; i < all.size() ; i++) {
            Task cur = all.get(i);
            if (cur.isUrgent()) {
                upcoming.add(cur);
            }            
        }
        return upcoming;
    }
}

