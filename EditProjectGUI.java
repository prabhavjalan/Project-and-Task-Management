import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.*;
import javax.swing.*;
import javax.swing.border.*;

public class EditProjectGUI extends JFrame {
    
    private JPanel contentPane;

    public EditProjectGUI(Project project) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
        contentPane.setBorder(BorderFactory.createTitledBorder("Edit Project"));

        JTextField t1 = new JTextField();
        t1.setBounds(240, 20, 130, 26);
        contentPane.add(t1);
        t1.setColumns(10);
        t1.setText(project.getTitle());
        
        JTextField t2 = new JTextField();
        t2.setBounds(240, 68, 130, 26);
        contentPane.add(t2);
        t2.setColumns(10);
        LocalDateTime date = project.getDeadline();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDateTime = date.format(formatter);
        t2.setText(formattedDateTime);

        JTextField t3 = new JTextField();
        t3.setBounds(240, 116, 130, 26);
        contentPane.add(t3);
        t3.setColumns(10);
        t3.setText(project.getDescription());
        
        JButton b1 = new JButton("Save");
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input1 = t1.getText();
                String input2 = t2.getText();
                String input3 = t3.getText();
                edit(project, input1, input2, input3);
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
        title.setBounds(100, 25, 61, 16);
        contentPane.add(title);
        
        JLabel deadline = new JLabel("Deadline (yyyy-mm-dd)");
        deadline.setBounds(100, 73, 200, 16);
        contentPane.add(deadline);

        JLabel description = new JLabel("Description");
        description.setBounds(100, 121, 100, 16);
        contentPane.add(description);
    }

    void edit(Project project, String input1, String input2, String input3) {
        ProjectManager p = new ProjectManager();
		p.editProject(project, input1, input2, input3);
    }
}


