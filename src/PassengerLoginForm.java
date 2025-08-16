import javax.swing.*;

public class PassengerLoginForm extends JFrame {
    public PassengerLoginForm() {
        setTitle("Passenger Login");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(null);

        // Labels
        JLabel emailLabel = new JLabel("Email:");
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
        loginBtn.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            for (Passenger p : GuiManager.passengers) {
                if (p.getEmail().equalsIgnoreCase(email) && p.getPassword().equals(password)) {
                    
                    // 🚨 NEW: Prevent Deactivated Users from Logging In
                    if (!p.isActive()) { 
                        JOptionPane.showMessageDialog(this, "Your account is deactivated. Contact admin.", "Login Failed", JOptionPane.ERROR_MESSAGE);
                        return; // ✅ Prevent login
                    }

                    JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new PassengerMenu(p); // 🚀 Open Passenger Menu
                    dispose();
                    return;
                }
            }

            JOptionPane.showMessageDialog(this, "Invalid email or password!", "Error", JOptionPane.ERROR_MESSAGE);
        });

        closeBtn.addActionListener(e -> dispose());

        setVisible(true);
    }
}

