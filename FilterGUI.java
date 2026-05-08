import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class FilterGUI extends JFrame {

    private JPanel contentPane;

    public FilterGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 560, 500);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBorder(BorderFactory.createTitledBorder("Filter / Sort"));

        JLabel sortLabel = new JLabel("Sort By");
        sortLabel.setBounds(30, 30, 80, 20);
        contentPane.add(sortLabel);

        String[] options = {
            "Deadline (Earliest to Latest)",
            "Deadline (Latest to Earliest)",
            "Alphabetical",
            "Creation Date"
        };
        JComboBox<String> dropdown = new JComboBox<>(options);
        dropdown.setBounds(110, 27, 270, 26);
        contentPane.add(dropdown);

        JLabel viewLabel = new JLabel("Show");
        viewLabel.setBounds(30, 70, 80, 20);
        contentPane.add(viewLabel);

        JRadioButton rBoth = new JRadioButton("Projects & Tasks");
        rBoth.setBounds(110, 67, 160, 25);
        rBoth.setSelected(true);
        contentPane.add(rBoth);

        JRadioButton rProjects = new JRadioButton("Projects only");
        rProjects.setBounds(280, 67, 130, 25);
        contentPane.add(rProjects);

        JRadioButton rTasks = new JRadioButton("Tasks only");
        rTasks.setBounds(420, 67, 120, 25);
        contentPane.add(rTasks);

        ButtonGroup bg = new ButtonGroup();
        bg.add(rBoth);
        bg.add(rProjects);
        bg.add(rTasks);

        JButton bApply = new JButton("Apply");
        bApply.setBounds(30, 105, 100, 29);
        contentPane.add(bApply);

        JTextArea results = new JTextArea();
        results.setEditable(false);
        JScrollPane sp = new JScrollPane(results);
        sp.setBounds(30, 145, 510, 270);
        contentPane.add(sp);

        bApply.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ProjectManager pm = new ProjectManager();
                Filter f = new Filter();

                StringBuilder sb = new StringBuilder();

                if (rBoth.isSelected() || rProjects.isSelected()) {
                    List<Project> projects = new ArrayList<>(pm.getAllProjects());
                    sortItems(f, projects, dropdown.getSelectedIndex());
                    sb.append("=== Projects ===\n");
                    if (projects.isEmpty()) {
                        sb.append("(none)\n");
                    } else {
                        for (Project p : projects) {
                            appendProject(sb, p);
                        }
                    }
                    sb.append("\n");
                }

                if (rBoth.isSelected() || rTasks.isSelected()) {
                    List<Task> tasks = new ArrayList<>();
                    Map<Task, String> taskParent = new HashMap<>();
                    for (Project p : pm.getAllProjects()) {
                        for (Task t : p.getTasks()) {
                            tasks.add(t);
                            taskParent.put(t, p.getName());
                        }
                    }
                    sortItems(f, tasks, dropdown.getSelectedIndex());
                    sb.append("=== Tasks ===\n");
                    if (tasks.isEmpty()) {
                        sb.append("(none)\n");
                    } else {
                        for (Task t : tasks) {
                            appendTask(sb, t, taskParent.get(t));
                        }
                    }
                }

                results.setText(sb.toString());
                results.setCaretPosition(0);
            }
        });

        JButton bClose = new JButton("Close");
        bClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        bClose.setBounds(440, 425, 100, 29);
        contentPane.add(bClose);
    }

    private <T extends Sortable> void sortItems(Filter f, List<T> items, int criterionIndex) {
        switch (criterionIndex) {
            case 0 -> f.sortByDeadlineAsc(items);
            case 1 -> f.sortByDeadlineDesc(items);
            case 2 -> f.sortByAlphabet(items);
            default -> f.sortByCreationDate(items);
        }
    }

    private void appendProject(StringBuilder sb, Project p) {
        sb.append("• ").append(p.getName());
        if (p.getDeadline() != null) {
            sb.append("  Due: ").append(p.getDeadline().toString());
        }
        if (!p.getTags().isEmpty()) {
            sb.append("  Tags: ");
            for (int i = 0; i < p.getTags().size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(p.getTags().get(i).getName());
            }
        }
        if (p.isOverdue()) {
            sb.append("  [OVERDUE]");
        } else if (p.isUrgent()) {
            sb.append("  [URGENT]");
        }
        sb.append("\n");
    }

    private void appendTask(StringBuilder sb, Task t, String parentProject) {
        sb.append("• ").append(t.getName());
        if (parentProject != null) {
            sb.append("  (in ").append(parentProject).append(")");
        }
        if (t.getStatus() != null) {
            sb.append("  [").append(t.getStatus()).append("]");
        }
        if (t.getDeadline() != null) {
            sb.append("  Due: ").append(t.getDeadline().toString());
        }
        if (!t.getTags().isEmpty()) {
            sb.append("  Tags: ");
            for (int i = 0; i < t.getTags().size(); i++) {
                if (i > 0) sb.append(", ");
                sb.append(t.getTags().get(i).getName());
            }
        }
        if (t.isOverdue()) {
            sb.append("  [OVERDUE]");
        } else if (t.isUrgent()) {
            sb.append("  [URGENT]");
        }
        sb.append("\n");
    }
}
