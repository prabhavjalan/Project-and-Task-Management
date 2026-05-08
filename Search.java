import java.util.ArrayList;
import java.util.List;

public class Search {

    private ProjectManager manager;

    public Search(ProjectManager manager) {
        this.manager = manager;
    }

    public List<Object> searchByName(String name) {
        List<Object> results = new ArrayList<>();
        if (name == null) return results;
        for (Project p : manager.getAllProjects()) {
            if (p.getName() != null && p.getName().equalsIgnoreCase(name)) {
                results.add(p);
            }
            for (Task t : p.getTasks()) {
                if (t.getName() != null && t.getName().equalsIgnoreCase(name)) {
                    results.add(t);
                }
            }
        }
        return results;
    }

    public List<Object> searchByKeyword(String keyword) {
        List<Object> results = new ArrayList<>();
        if (keyword == null) return results;
        String lower = keyword.toLowerCase();

        for (Project p : manager.getAllProjects()) {
            if (projectMatches(p, lower)) {
                results.add(p);
            }
            for (Task t : p.getTasks()) {
                if (taskMatches(t, lower)) {
                    results.add(t);
                }
            }
        }
        return results;
    }

    private boolean projectMatches(Project p, String lower) {
        if (p.getName() != null && p.getName().toLowerCase().contains(lower)) return true;
        for (Tag tag : p.getTags()) {
            if (tag.getName() != null && tag.getName().toLowerCase().contains(lower)) return true;
        }
        return false;
    }

    private boolean taskMatches(Task t, String lower) {
        if (t.getName() != null && t.getName().toLowerCase().contains(lower)) return true;
        for (Tag tag : t.getTags()) {
            if (tag.getName() != null && tag.getName().toLowerCase().contains(lower)) return true;
        }
        return false;
    }
}
