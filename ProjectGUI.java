import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class ProjectGUI extends JFrame {
    
    private JPanel contentPane;

    public ProjectGUI(int i) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

        switch (i) {
            case 1 ->                 {
					contentPane.setBorder(BorderFactory.createTitledBorder("Add Project"));
                    JTextField t1 = new JTextField();
                    t1.setBounds(240, 40, 130, 26);
                    contentPane.add(t1);
                    t1.setColumns(10);
                    JTextField t2 = new JTextField();
                    t2.setBounds(240, 88, 130, 26);
                    contentPane.add(t2);
                    t2.setColumns(10);
                    JButton b1 = new JButton("Save");
                    b1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String input1 = t1.getText();
                            String input2 = t2.getText();
                            save(input1, input2);
                        }
                    });
                    b1.setBounds(51, 172, 117, 29);
                    contentPane.add(b1);
                    JButton b2 = new JButton("Cancel");
                    b2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                        }
                    });
                    b2.setBounds(240, 172, 117, 29);
                    contentPane.add(b2);
                    JLabel title = new JLabel("Title");
                    title.setBounds(100, 45, 61, 16);
                    contentPane.add(title);
                    JLabel deadline = new JLabel("Deadline (yyyy-mm-dd)");
                    deadline.setBounds(100, 93, 200, 16);
                    contentPane.add(deadline);
                }
            case 2 ->                 {
					contentPane.setBorder(BorderFactory.createTitledBorder("Edit Project"));
                    JTextField t1 = new JTextField();
                    t1.setBounds(240, 68, 130, 26);
                    contentPane.add(t1);
                    t1.setColumns(10);
                    JButton b1 = new JButton("Edit Project");
                    b1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String input = t1.getText();
                            editProject(input);
                        }
                    });
                    b1.setBounds(51, 172, 117, 29);
                    contentPane.add(b1);

                    JButton b2 = new JButton("Cancel");
                    b2.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                        }
                    });
                    b2.setBounds(240, 172, 117, 29);
                    contentPane.add(b2);

					JButton b3 = new JButton("Add Task");
                    b3.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String input = t1.getText();
                            task(input, 1);
                        }
                    });
                    b3.setBounds(21, 210, 117, 29);
                    contentPane.add(b3);

                    JButton b4 = new JButton("Edit Task");
                    b4.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String input = t1.getText();
                            task(input, 2);
                        }
                    });
                    b4.setBounds(145, 210, 117, 29);
                    contentPane.add(b4);

					JButton b5 = new JButton("Delete Task");
                    b5.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String input = t1.getText();
                            task(input, 3);
                        }
                    });
                    b5.setBounds(270, 210, 117, 29);
                    contentPane.add(b5);

                    JLabel projectName = new JLabel("Project Name");
                    projectName.setBounds(137, 73, 100, 16);
                    contentPane.add(projectName);
                }
            case 3 ->                 {
					contentPane.setBorder(BorderFactory.createTitledBorder("Delete Project"));
                    JTextField t1 = new JTextField();
                    t1.setBounds(240, 68, 130, 26);
                    contentPane.add(t1);
                    t1.setColumns(10);
                    JButton b1 = new JButton("Delete");
                    b1.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            String input = t1.getText();
                            delete(input);
                        }
                    });
                    b1.setBounds(51, 172, 117, 29);
                    contentPane.add(b1);
                    JButton b3 = new JButton("Cancel");
                    b3.addActionListener(new ActionListener() {
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                        }
                    });
                    b3.setBounds(240, 172, 117, 29);
                    contentPane.add(b3);
                    JLabel projectName = new JLabel("Project Name");
                    projectName.setBounds(137, 73, 100, 16);
                    contentPane.add(projectName);
                }
            default -> {
            }
        }
    }
    
    void save(String input1, String input2) {
    	ProjectManager p = new ProjectManager();
		p.createProject(input1, input2);
		dispose();
    }

	void editProject(String input) {
		ProjectManager p = new ProjectManager();
		List<Project> list = p.getProjects();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTitle().equals(input)) {
				EditProjectGUI c1 = new EditProjectGUI(list.get(i));
				c1.show();
			}
		}
		
	}

	void delete(String title) {
		ProjectManager p = new ProjectManager();
		List<Project> list = p.getProjects();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTitle().equals(title)) {
				p.deleteProject(list.get(i));
				dispose();
			}
		}
		
		
	}

	void task(String title, int action) {
		ProjectManager p = new ProjectManager();
		List<Project> list = p.getProjects();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTitle().equals(title)) {
				TaskGUI c1 = new TaskGUI(list.get(i), action);
				c1.show();
				dispose();
			}
		}
		
	}
}