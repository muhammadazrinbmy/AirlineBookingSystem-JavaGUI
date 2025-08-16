import javax.swing.*;

public class UserManagementMenu extends JFrame {
    public UserManagementMenu() {
        setTitle("User Management");
        setSize(500, 250);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("User Management");
        titleLabel.setBounds(180, 20, 200, 30);
        add(titleLabel);

        JButton viewUsersBtn = new JButton("View Users");
        viewUsersBtn.setBounds(150, 60, 200, 30);
        add(viewUsersBtn);

        JButton manageUserBtn = new JButton("Activate/Deactivate User");
        manageUserBtn.setBounds(150, 100, 200, 30);
        add(manageUserBtn);

        JButton backBtn = new JButton("Back to Admin Panel");
        backBtn.setBounds(150, 140, 200, 30);
        add(backBtn);

        // 🚀 Button Actions
        viewUsersBtn.addActionListener(e -> new ViewUsersForm());
        manageUserBtn.addActionListener(e -> new ManageUserStatusForm());
        backBtn.addActionListener(e -> dispose());

        setVisible(true);
    }
}
