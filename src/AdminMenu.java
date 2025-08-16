import javax.swing.*;

public class AdminMenu extends JFrame {
    public AdminMenu() {
        setTitle("Admin Panel");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Admin Panel");
        titleLabel.setBounds(200, 20, 200, 30);
        add(titleLabel);

        JButton flightManagementBtn = new JButton("Flight Management");
        flightManagementBtn.setBounds(150, 70, 200, 30);
        add(flightManagementBtn);

        JButton userManagementBtn = new JButton("User Management");
        userManagementBtn.setBounds(150, 110, 200, 30);
        add(userManagementBtn);

        JButton viewTransactionsBtn = new JButton("View Transaction Logs");
        viewTransactionsBtn.setBounds(150, 150, 200, 30);
        add(viewTransactionsBtn);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(150, 190, 200, 30);
        add(logoutBtn);

        // 🚀 Button Actions
        flightManagementBtn.addActionListener(e -> new FlightManagementMenu());
        userManagementBtn.addActionListener(e -> new UserManagementMenu());
        viewTransactionsBtn.addActionListener(e -> new TransactionLogsForm());

        logoutBtn.addActionListener(e -> {
            dispose();
            JOptionPane.showMessageDialog(null, "Admin logged out successfully!");
        });

        setVisible(true);
    }
}

