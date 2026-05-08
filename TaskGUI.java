import java.awt.event.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.border.*;

public class TaskGUI extends JFrame {
    
    private JPanel contentPane;

    public TaskGUI(Project project, int i) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

        switch (i) {
            case 1 ->                 {
                    contentPane.setBorder(BorderFactory.createTitledBorder("Add Task"));
                    JLabel name = new JLabel("Name");
                    name.setBounds(30, 45, 100, 20);
                    contentPane.add(name);

                    JTextField t1 = new JTextField();
                    t1.setBounds(260, 42, 190, 26);
                    contentPane.add(t1);
                    t1.setColumns(10);

                    JLabel deadline = new JLabel("Deadline (yyyy-mm-dd)");
                    deadline.setBounds(30, 90, 220, 20);
                    contentPane.add(deadline);

                    JTextField t2 = new JTextField();
                    t2.setBounds(260, 87, 190, 26);
                    contentPane.add(t2);
                    t2.setColumns(10);

                    JButton b1 = new JButton("Save");
                    b1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            save(project, t1.getText(), t2.getText());
                        }
                    });
                    b1.setBounds(80, 170, 120, 29);
                    contentPane.add(b1);

                    JButton b2 = new JButton("Cancel");
                    b2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                        }
                    });
                    b2.setBounds(260, 170, 120, 29);
                    contentPane.add(b2);
                }
            case 2 ->                 {
                    contentPane.setBorder(BorderFactory.createTitledBorder("Edit Task"));
                    JLabel taskName = new JLabel("Task Name");
                    taskName.setBounds(30, 60, 180, 20);
                    contentPane.add(taskName);

                    JTextField t1 = new JTextField();
                    t1.setBounds(180, 57, 270, 26);
                    contentPane.add(t1);
                    t1.setColumns(10);

                    JButton b1 = new JButton("Edit Task");
                    b1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            editTask(project, t1.getText());
                        }
                    });
                    b1.setBounds(80, 140, 140, 29);
                    contentPane.add(b1);

                    JButton b2 = new JButton("Cancel");
                    b2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                        }
                    });
                    b2.setBounds(260, 140, 120, 29);
                    contentPane.add(b2);
                }
            case 3 ->                 {
                    contentPane.setBorder(BorderFactory.createTitledBorder("Delete Task"));
                    JLabel taskName = new JLabel("Task Name");
                    taskName.setBounds(30, 60, 180, 20);
                    contentPane.add(taskName);

                    JTextField t1 = new JTextField();
                    t1.setBounds(180, 57, 270, 26);
                    contentPane.add(t1);
                    t1.setColumns(10);

                    JButton b1 = new JButton("Delete");
                    b1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            delete(project, t1.getText());
                        }
                    });
                    b1.setBounds(80, 140, 120, 29);
                    contentPane.add(b1);

                    JButton b2 = new JButton("Cancel");
                    b2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                        }
                    });
                    b2.setBounds(260, 140, 120, 29);
                    contentPane.add(b2);
                }
            default -> {
            }
        }
    }
    
    void save(Project project, String name, String deadlineStr) {
        if (name == null || name.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please enter a task name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        LocalDate deadline;
        try {
            deadline = LocalDate.parse(deadlineStr);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid date. Please use yyyy-mm-dd format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Task task = new Task(name, deadline);
        ProjectManager pm = new ProjectManager();
        pm.addTask(project, task);
		dispose();
    }

	void editTask(Project project, String name) {
        if (name == null || name.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please enter a task name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        for (Task t : project.getTasks()) {
            if (t.getName() != null && t.getName().equals(name)) {
                EditTaskGUI c1 = new EditTaskGUI(project, t);
                c1.show();
                dispose();
                return;
            }
        }
        JOptionPane.showMessageDialog(this, "Task '" + name + "' not found in this project.", "Error", JOptionPane.ERROR_MESSAGE);
	}

	void delete(Project project, String name) {
        if (name == null || name.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please enter a task name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean exists = false;
        for (Task t : project.getTasks()) {
            if (t.getName() != null && t.getName().equals(name)) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            JOptionPane.showMessageDialog(this, "Task '" + name + "' not found in this project.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
		ProjectManager pm = new ProjectManager();
		pm.deleteTask(project, name);
		JOptionPane.showMessageDialog(this, "Task '" + name + "' has been deleted.", "Deleted", JOptionPane.INFORMATION_MESSAGE);
		dispose();
	}
}
