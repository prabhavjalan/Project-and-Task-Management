import java.awt.event.*;
import java.time.LocalDate;
import javax.swing.*;
import javax.swing.border.*;

public class EditTaskGUI extends JFrame {
    
    private JPanel contentPane;

    public EditTaskGUI(Project project, Task task) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 280);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBorder(BorderFactory.createTitledBorder("Edit Task"));

        final String currentName = task.getName();

        JLabel name = new JLabel("Name");
		name.setBounds(30, 35, 100, 20);
		contentPane.add(name);

        JTextField t1 = new JTextField();
		t1.setBounds(260, 32, 190, 26);
		contentPane.add(t1);
		t1.setColumns(10);
		if (task.getName() != null) t1.setText(task.getName());
		
		JLabel deadline = new JLabel("Deadline (yyyy-mm-dd)");
		deadline.setBounds(30, 75, 220, 20);
		contentPane.add(deadline);

		JTextField t2 = new JTextField();
		t2.setBounds(260, 72, 190, 26);
		contentPane.add(t2);
		t2.setColumns(10);
		if (task.getDeadline() != null) {
			t2.setText(task.getDeadline().toString());
		}
		
		JButton b1 = new JButton("Save");
		b1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edit(project, currentName, t1.getText(), t2.getText());
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

    void edit(Project project, String currentName, String newName, String newDeadline) {
        Task data = new Task();
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
        ProjectManager pm = new ProjectManager();
        pm.editTask(project, currentName, data);
		dispose();
    }
}
