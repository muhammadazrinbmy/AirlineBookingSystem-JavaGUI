import javax.swing.*;

public class ViewUsersForm extends JFrame {
    public ViewUsersForm() {
        setTitle("View All Users");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Registered Users");
        titleLabel.setBounds(200, 20, 200, 30);
        add(titleLabel);

        JTextArea userArea = new JTextArea();
        userArea.setBounds(50, 60, 400, 200);
        userArea.setEditable(false);
        add(userArea);

        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(200, 280, 100, 30);
        add(closeBtn);

        closeBtn.addActionListener(e -> dispose());

        // 🚀 Display Registered Users
        StringBuilder usersText = new StringBuilder();
        for (Passenger p : GuiManager.passengers) {
            usersText.append("User ID: ").append(p.getUserId())
                     .append(" | Name: ").append(p.getName())
                     .append(" | Email: ").append(p.getEmail())
                     .append(" | Status: ").append(p.isActive() ? "Active" : "Deactivated")
                     .append("\n");
        }

        if (usersText.length() == 0) {
            userArea.setText("No users registered.");
        } else {
            userArea.setText(usersText.toString());
        }

        setVisible(true);
    }
}
