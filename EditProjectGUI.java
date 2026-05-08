import java.awt.event.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.border.*;

public class EditProjectGUI extends JFrame {
    
    private JPanel contentPane;
    private JTextArea taskArea;
    private Project project;

    public EditProjectGUI(Project project) {
        this.project = project;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 580);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
        contentPane.setBorder(BorderFactory.createTitledBorder("Edit Project"));

        final String currentName = project.getName();

        JLabel name = new JLabel("Name");
        name.setBounds(30, 35, 100, 20);
        contentPane.add(name);

        JTextField t1 = new JTextField();
        t1.setBounds(280, 32, 200, 26);
        contentPane.add(t1);
        t1.setColumns(10);
        if (project.getName() != null) t1.setText(project.getName());
        
        JLabel deadline = new JLabel("Deadline (yyyy-mm-dd)");
        deadline.setBounds(30, 75, 220, 20);
        contentPane.add(deadline);

        JTextField t2 = new JTextField();
        t2.setBounds(280, 72, 200, 26);
        contentPane.add(t2);
        t2.setColumns(10);
        if (project.getDeadline() != null) {
            t2.setText(project.getDeadline().toString());
        }
        
        JButton b1 = new JButton("Save");
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                edit(currentName, t1.getText(), t2.getText());
            }
        });
        b1.setBounds(80, 120, 120, 29);
        contentPane.add(b1);

        JButton b2 = new JButton("Cancel");
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        b2.setBounds(280, 120, 120, 29);
        contentPane.add(b2);

        // ========== Tasks section ==========
        JLabel tasksLabel = new JLabel("Tasks");
        tasksLabel.setBounds(30, 175, 100, 20);
        contentPane.add(tasksLabel);

        JButton bAdd = new JButton("Add Task");
        bAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskGUI c1 = new TaskGUI(project, 1);
                c1.show();
            }
        });
        bAdd.setBounds(30, 200, 130, 29);
        contentPane.add(bAdd);

        JButton bEdit = new JButton("Edit Task");
        bEdit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskGUI c1 = new TaskGUI(project, 2);
                c1.show();
            }
        });
        bEdit.setBounds(180, 200, 130, 29);
        contentPane.add(bEdit);

        JButton bDelete = new JButton("Delete Task");
        bDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TaskGUI c1 = new TaskGUI(project, 3);
                c1.show();
            }
        });
        bDelete.setBounds(330, 200, 150, 29);
        contentPane.add(bDelete);

        // Task list display
        taskArea = new JTextArea();
        taskArea.setEditable(false);
        JScrollPane sp = new JScrollPane(taskArea);
        sp.setBounds(30, 245, 450, 280);
        contentPane.add(sp);

        refreshTaskList();

        addWindowFocusListener(new WindowFocusListener() {
            public void windowGainedFocus(WindowEvent e) {
                refreshTaskList();
            }
            public void windowLostFocus(WindowEvent e) {}
        });
    }

    void refreshTaskList() {
        if (project.getTasks().isEmpty()) {
            taskArea.setText("(No tasks yet — click \"Add Task\" to create one.)");
            return;
        }
        StringBuilder sb = new StringBuilder();
        for (Task t : project.getTasks()) {
            sb.append("• ").append(t.getName());
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
        taskArea.setText(sb.toString());
        taskArea.setCaretPosition(0);
    }

    void edit(String currentName, String newName, String newDeadline) {
        Project data = new Project();
        if (newName != null && !newName.isBlank()) {
            data.setName(newName);
        }
        if (newDeadline != null && !newDeadline.isBlank()) {
            try {
                data.setDeadline(LocalDate.parse(newDeadline));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid date. Please use yyyy-mm-dd format.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        }
        ProjectManager p = new ProjectManager();
        p.editProject(currentName, data);
        dispose();
    }
}
