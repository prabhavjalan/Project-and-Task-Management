import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class StatusGUI extends JFrame {

    private JPanel contentPane;

    public StatusGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 320);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBorder(BorderFactory.createTitledBorder("Update Task Status"));

        JLabel l1 = new JLabel("Project Name");
        l1.setBounds(40, 30, 120, 16);
        contentPane.add(l1);
        JTextField t1 = new JTextField();
        t1.setBounds(180, 25, 260, 26);
        contentPane.add(t1);

        JLabel l2 = new JLabel("Task Name");
        l2.setBounds(40, 70, 120, 16);
        contentPane.add(l2);
        JTextField t2 = new JTextField();
        t2.setBounds(180, 65, 260, 26);
        contentPane.add(t2);

        JLabel l3 = new JLabel("New Status");
        l3.setBounds(40, 110, 120, 16);
        contentPane.add(l3);
        JComboBox<Status> dropdown = new JComboBox<>(Status.values());
        dropdown.setBounds(180, 105, 260, 26);
        contentPane.add(dropdown);

        JLabel msg = new JLabel(" ");
        msg.setBounds(40, 215, 420, 25);
        contentPane.add(msg);

        JButton bUpdate = new JButton("Update");
        bUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                msg.setText(update(t1.getText(), t2.getText(), (Status) dropdown.getSelectedItem()));
            }
        });
        bUpdate.setBounds(120, 160, 120, 29);
        contentPane.add(bUpdate);

        JButton bClose = new JButton("Close");
        bClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        bClose.setBounds(260, 160, 120, 29);
        contentPane.add(bClose);
    }

    String update(String projectName, String taskName, Status status) {
        ProjectManager pm = new ProjectManager();
        ProgressTracker tracker = new ProgressTracker();
        Project p = pm.getProject(projectName);
        if (p == null) return "Project not found.";
        for (Task t : p.getTasks()) {
            if (t.getName() != null && t.getName().equals(taskName)) {
                tracker.updateTaskStatus(t, status);
                double progress = tracker.calculateProgress(p);
                return "Status updated. Project progress: " + progress + "%";
            }
        }
        return "Task not found.";
    }
}
