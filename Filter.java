import java.util.Comparator;
import java.util.List;

public class Filter {

    public <T extends Sortable> List<T> sortByDeadlineAsc(List<T> items) {
        items.sort(Comparator.comparing(
            Sortable::getDeadline,
            Comparator.nullsLast(Comparator.naturalOrder())
        ));
        return items;
    }

    public <T extends Sortable> List<T> sortByDeadlineDesc(List<T> items) {
        items.sort(Comparator.comparing(
            Sortable::getDeadline,
            Comparator.nullsLast(Comparator.reverseOrder())
        ));
        return items;
    }

    public <T extends Sortable> List<T> sortByCreationDate(List<T> items) {
        items.sort(Comparator.comparing(
            Sortable::getCreationDate,
            Comparator.nullsLast(Comparator.naturalOrder())
        ));
        return items;
    }

    public <T extends Sortable> List<T> sortByAlphabet(List<T> items) {
        items.sort(Comparator.comparing(
            Sortable::getName,
            Comparator.nullsLast(Comparator.naturalOrder())
        ));
        return items;
    }
}
