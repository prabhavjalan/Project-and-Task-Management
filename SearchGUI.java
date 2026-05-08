import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.border.*;

public class SearchGUI extends JFrame {

    private JPanel contentPane;

    public SearchGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 420);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        contentPane.setBorder(BorderFactory.createTitledBorder("Search"));

        JLabel label = new JLabel("Search Term");
        label.setBounds(40, 30, 100, 16);
        contentPane.add(label);

        JTextField t1 = new JTextField();
        t1.setBounds(160, 25, 280, 26);
        contentPane.add(t1);
        t1.setColumns(10);

        JRadioButton r1 = new JRadioButton("By Name");
        r1.setBounds(40, 65, 100, 25);
        r1.setSelected(true);
        contentPane.add(r1);

        JRadioButton r2 = new JRadioButton("By Keyword");
        r2.setBounds(150, 65, 130, 25);
        contentPane.add(r2);

        ButtonGroup bg = new ButtonGroup();
        bg.add(r1);
        bg.add(r2);

        JButton bSearch = new JButton("Search");
        bSearch.setBounds(320, 65, 120, 25);
        contentPane.add(bSearch);

        JTextArea results = new JTextArea();
        results.setEditable(false);
        JScrollPane sp = new JScrollPane(results);
        sp.setBounds(40, 110, 400, 220);
        contentPane.add(sp);

        bSearch.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String input = t1.getText();
                ProjectManager pm = new ProjectManager();
                Search s = new Search(pm);
                List<Object> matches;
                if (r1.isSelected()) {
                    matches = s.searchByName(input);
                } else {
                    matches = s.searchByKeyword(input);
                }
                StringBuilder sb = new StringBuilder();
                if (matches.isEmpty()) {
                    sb.append("No results found.");
                } else {
                    for (Object o : matches) {
                        if (o instanceof Project) {
                            sb.append("[Project] ").append(((Project) o).getName()).append("\n");
                        } else if (o instanceof Task) {
                            sb.append("[Task]    ").append(((Task) o).getName()).append("\n");
                        }
                    }
                }
                results.setText(sb.toString());
            }
        });

        JButton bClose = new JButton("Close");
        bClose.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        bClose.setBounds(340, 345, 100, 29);
        contentPane.add(bClose);
    }
}
