import java.awt.event.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.border.*;

public class ProjectGUI extends JFrame {
    
    private JPanel contentPane;

    public ProjectGUI(int i) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

        switch (i) {
            case 1 ->                 {
					contentPane.setBorder(BorderFactory.createTitledBorder("Add Project"));
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
                            save(t1.getText(), t2.getText());
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
					contentPane.setBorder(BorderFactory.createTitledBorder("Edit Project"));
                    JLabel projectName = new JLabel("Project Name");
                    projectName.setBounds(30, 60, 180, 20);
                    contentPane.add(projectName);

                    JTextField t1 = new JTextField();
                    t1.setBounds(180, 57, 270, 26);
                    contentPane.add(t1);
                    t1.setColumns(10);

                    JButton b1 = new JButton("Edit Project");
                    b1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            editProject(t1.getText());
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
					contentPane.setBorder(BorderFactory.createTitledBorder("Delete Project"));
                    JLabel projectName = new JLabel("Project Name");
                    projectName.setBounds(30, 60, 180, 20);
                    contentPane.add(projectName);

                    JTextField t1 = new JTextField();
                    t1.setBounds(180, 57, 270, 26);
                    contentPane.add(t1);
                    t1.setColumns(10);

                    JButton b1 = new JButton("Delete");
                    b1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            delete(t1.getText());
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
    
    void save(String name, String deadlineStr) {
        if (name == null || name.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please enter a project name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        LocalDate deadline;
        try {
            deadline = LocalDate.parse(deadlineStr);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Invalid date. Please use yyyy-mm-dd format.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
		ProjectManager p = new ProjectManager();
		p.createProject(name, deadline);
		dispose();
    }

	void editProject(String input) {
        if (input == null || input.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please enter a project name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
		ProjectManager p = new ProjectManager();
		Project found = p.getProject(input);
		if (found == null) {
            JOptionPane.showMessageDialog(this, "Project '" + input + "' not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        EditProjectGUI c1 = new EditProjectGUI(found);
        c1.show();
        dispose();
	}

	void delete(String name) {
        if (name == null || name.isBlank()) {
            JOptionPane.showMessageDialog(this, "Please enter a project name.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
		ProjectManager p = new ProjectManager();
		Project found = p.getProject(name);
		if (found == null) {
            JOptionPane.showMessageDialog(this, "Project '" + name + "' not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
		p.deleteProject(name);
		JOptionPane.showMessageDialog(this, "Project '" + name + "' has been deleted.", "Deleted", JOptionPane.INFORMATION_MESSAGE);
		dispose();
	}
}
