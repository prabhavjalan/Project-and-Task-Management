import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class TagGUI extends JFrame {

    private JPanel contentPane;
    private static final String TAG_PROJECT_OPTION = "(tag the whole project)";

    public TagGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 520, 360);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBorder(BorderFactory.createTitledBorder("Manage Tags"));

        JLabel l1 = new JLabel("Project");
        l1.setBounds(30, 35, 120, 20);
        contentPane.add(l1);
        JComboBox<String> projectDropdown = new JComboBox<>();
        projectDropdown.setBounds(160, 32, 320, 26);
        contentPane.add(projectDropdown);

        JLabel l2 = new JLabel("Tag target");
        l2.setBounds(30, 80, 120, 20);
        contentPane.add(l2);
        JComboBox<String> taskDropdown = new JComboBox<>();
        taskDropdown.setBounds(160, 77, 320, 26);
        contentPane.add(taskDropdown);

        JLabel l3 = new JLabel("Tag");
        l3.setBounds(30, 125, 100, 20);
        contentPane.add(l3);
        JTextField tagField = new JTextField();
        tagField.setBounds(160, 122, 320, 26);
        contentPane.add(tagField);

        JLabel msg = new JLabel(" ");
        msg.setBounds(30, 250, 460, 25);
        contentPane.add(msg);

        // Populate project dropdown initially
        ProjectManager pm = new ProjectManager();
        for (Project p : pm.getAllProjects()) {
            projectDropdown.addItem(p.getName());
        }

        // When the project changes, repopulate the task dropdown
        ActionListener updateTasks = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                taskDropdown.removeAllItems();
                taskDropdown.addItem(TAG_PROJECT_OPTION);
                String selected = (String) projectDropdown.getSelectedItem();
                if (selected != null) {
                    ProjectManager pm = new ProjectManager();
                    Project p = pm.getProject(selected);
                    if (p != null) {
                        for (Task t : p.getTasks()) {
                            taskDropdown.addItem(t.getName());
                        }
                    }
                }
            }
        };
        projectDropdown.addActionListener(updateTasks);
        // Trigger initial population
        updateTasks.actionPerformed(null);

        JButton bAdd = new JButton("Add Tag");
        bAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                msg.setText(addTag(
                    (String) projectDropdown.getSelectedItem(),
                    (String) taskDropdown.getSelectedItem(),
                    tagField.getText()
                ));
            }
        });
        bAdd.setBounds(30, 180, 130, 29);
        contentPane.add(bAdd);

        JButton bRemove = new JButton("Remove Tag");
        bRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                msg.setText(removeTag(
                    (String) projectDropdown.getSelectedItem(),
                    (String) taskDropdown.getSelectedItem(),
                    tagField.getText()
                ));
            }
        });
        bRemove.setBounds(180, 180, 150, 29);
        contentPane.add(bRemove);

        JButton bClose = new JButton("Close");
        bClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        bClose.setBounds(350, 180, 130, 29);
        contentPane.add(bClose);
    }

    String addTag(String projectName, String taskSelection, String tagName) {
        if (projectName == null || projectName.isBlank()) return "Please select a project.";
        if (tagName == null || tagName.isBlank()) return "Please enter a tag.";

        ProjectManager pm = new ProjectManager();
        Project p = pm.getProject(projectName);
        if (p == null) return "Project not found.";

        Tag tag = new Tag(tagName);
        if (taskSelection == null || taskSelection.equals(TAG_PROJECT_OPTION)) {
            p.addTag(tag);
            return "Tag '" + tagName + "' added to project '" + projectName + "'.";
        }
        for (Task t : p.getTasks()) {
            if (t.getName() != null && t.getName().equals(taskSelection)) {
                t.addTag(tag);
                return "Tag '" + tagName + "' added to task '" + taskSelection + "'.";
            }
        }
        return "Task not found.";
    }

    String removeTag(String projectName, String taskSelection, String tagName) {
        if (projectName == null || projectName.isBlank()) return "Please select a project.";
        if (tagName == null || tagName.isBlank()) return "Please enter a tag.";

        ProjectManager pm = new ProjectManager();
        Project p = pm.getProject(projectName);
        if (p == null) return "Project not found.";

        Tag tag = new Tag(tagName);
        if (taskSelection == null || taskSelection.equals(TAG_PROJECT_OPTION)) {
            p.removeTag(tag);
            return "Tag '" + tagName + "' removed from project '" + projectName + "'.";
        }
        for (Task t : p.getTasks()) {
            if (t.getName() != null && t.getName().equals(taskSelection)) {
                t.removeTag(tag);
                return "Tag '" + tagName + "' removed from task '" + taskSelection + "'.";
            }
        }
        return "Task not found.";
    }
}
