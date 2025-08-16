import javax.swing.*;

public class AdminLoginForm extends JFrame {
    public AdminLoginForm() {
        setTitle("Admin Login");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(null);

        // Labels
        JLabel emailLabel = new JLabel("Admin Email:");
        emailLabel.setBounds(50, 50, 100, 25);
        add(emailLabel);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 90, 100, 25);
        add(passwordLabel);

        // Input Fields
        JTextField emailField = new JTextField();
        emailField.setBounds(150, 50, 200, 25);
        add(emailField);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 90, 200, 25);
        add(passwordField);

        // Login Button
        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(100, 140, 100, 30);
        add(loginBtn);

        // Close Button
        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(220, 140, 100, 30);
        add(closeBtn);

        // 🚀 Button Action: Validate Input
     // 🚀 Fix: Open Admin Panel After Login
        loginBtn.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (email.equals("admin@airline.com") && password.equals("admin123")) { // ✅ Admin Credentials Check
                JOptionPane.showMessageDialog(this, "Admin login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                new AdminMenu(); // ✅ Open Admin Panel
                dispose(); // ✅ Close Login Window
            } else {
                JOptionPane.showMessageDialog(this, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        closeBtn.addActionListener(e -> dispose());

        setVisible(true);
    }
}
