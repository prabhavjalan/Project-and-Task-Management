import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class MainGUI extends JFrame {

    private JPanel contentPane;

	public MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBorder(BorderFactory.createTitledBorder("Main"));
		
		JButton b1 = new JButton("Add Project");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addProject();
			}
		});
		b1.setBounds(170, 54, 117, 29);
		contentPane.add(b1);
		
		JButton b2 = new JButton("Edit Project");
		b2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editProject();
			}
		});
		b2.setBounds(170, 108, 117, 29);
		contentPane.add(b2);
		
		JButton b3 = new JButton("Delete Project");
		b3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteProject();
			}
		});
		b3.setBounds(170, 170, 117, 29);
		contentPane.add(b3);

    }

    void addProject() {
		ProjectGUI c1 = new ProjectGUI(1);
		c1.show();
		
	}

	void editProject() {
		ProjectGUI c1 = new ProjectGUI(2);
		c1.show();

	}

	void deleteProject() {
		ProjectGUI c1 = new ProjectGUI(3);
		c1.show();
	}

}