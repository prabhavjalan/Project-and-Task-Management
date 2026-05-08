import java.time.LocalDate;
import java.util.*;

public class ProjectManager {

    private static List<Project> projects = new ArrayList<>();

    public Project createProject(String name, LocalDate deadline) {
        Project project = new Project(name, deadline);
        projects.add(project);
        return project;
    }

    public void editProject(String name, Project data) {
        Project p = getProject(name);
        if (p == null) return;
        if (data.getName() != null) p.setName(data.getName());
        if (data.getDeadline() != null) p.setDeadline(data.getDeadline());
    }

    public void deleteProject(String name) {
        projects.removeIf(p -> p.getName() != null && p.getName().equals(name));
    }

    public Project getProject(String name) {
        for (Project p : projects) {
            if (p.getName() != null && p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public List<Project> getAllProjects() {
        return projects;
    }

    public Task addTask(Project project, Task data) {
        project.addTask(data);
        return data;
    }

    public void editTask(Project project, String name, Task data) {
        project.editTask(name, data);
    }

    public void deleteTask(Project project, String name) {
        project.removeTask(name);
    }

    public Task getTask(String name) {
        for (Project p : projects) {
            for (Task t : p.getTasks()) {
                if (t.getName() != null && t.getName().equals(name)) {
                    return t;
                }
            }
        }
        return null;
    }
}
