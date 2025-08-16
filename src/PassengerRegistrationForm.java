import javax.swing.*;

public class PassengerRegistrationForm extends JFrame {
    public PassengerRegistrationForm() {
        setTitle("Passenger Registration");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(null);

        // Labels
        JLabel userIdLabel = new JLabel("User ID:");
        userIdLabel.setBounds(50, 50, 100, 25);
        add(userIdLabel);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50, 90, 100, 25);
        add(nameLabel);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(50, 130, 100, 25);
        add(emailLabel);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(50, 170, 100, 25);
        add(passwordLabel);

        // Input Fields
        JTextField userIdField = new JTextField();
        userIdField.setBounds(150, 50, 200, 25);
        add(userIdField);

        JTextField nameField = new JTextField();
        nameField.setBounds(150, 90, 200, 25);
        add(nameField);

        JTextField emailField = new JTextField();
        emailField.setBounds(150, 130, 200, 25);
        add(emailField);

        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(150, 170, 200, 25);
        add(passwordField);

        // Register Button
        JButton registerBtn = new JButton("Register");
        registerBtn.setBounds(100, 220, 100, 30);
        add(registerBtn);

        // Close Button
        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(220, 220, 100, 30);
        add(closeBtn);

        // 🚀 Button Action: Validate Input
        registerBtn.addActionListener(e -> {
            String userId = userIdField.getText().trim();
            String name = nameField.getText().trim();
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword());

            if (userId.isEmpty() || name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
             return;
            }
            for (Passenger p : GuiManager.passengers) {
                if (p.getEmail().equalsIgnoreCase(email)) {
                    JOptionPane.showMessageDialog(this, "Email is already registered!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }

            // ✅ Register the Passenger
            Passenger newPassenger = new Passenger(userId, name, email, password);
            GuiManager.passengers.add(newPassenger);
            JOptionPane.showMessageDialog(this, "Registration successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        closeBtn.addActionListener(e -> dispose());

        setVisible(true);
    }
}
