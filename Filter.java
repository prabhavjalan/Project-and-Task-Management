import java.util.Comparator;
import java.util.List;

public class Filter {

    public void sortAlphabetically(List<Project> projects) {
        projects.sort(Comparator.comparing(Project::getName));
    }

    public void sortByCreationDate(List<Project> projects) {
        projects.sort(Comparator.comparing(Project::getCreationDate));
    }

    public void sortByDeadline(List<Project> projects) {
        projects.sort(Comparator.comparing(
            p -> p.getTasks().isEmpty() ? null : p.getTasks().get(0).getDeadline(),
            Comparator.nullsLast(Comparator.naturalOrder())
        ));
    }
}