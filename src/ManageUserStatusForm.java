import javax.swing.*;

public class ManageUserStatusForm extends JFrame {
    private JComboBox<String> userDropdown;

    public ManageUserStatusForm() {
        setTitle("Manage User Status");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Select User to Manage:");
        titleLabel.setBounds(150, 20, 200, 30);
        add(titleLabel);

        userDropdown = new JComboBox<>();
        userDropdown.setBounds(100, 70, 200, 30);
        add(userDropdown);

        JButton toggleStatusBtn = new JButton("Activate/Deactivate User");
        toggleStatusBtn.setBounds(80, 120, 240, 30);
        add(toggleStatusBtn);

        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(150, 170, 100, 30);
        add(closeBtn);

        closeBtn.addActionListener(e -> dispose());

        // 🚀 Load users into dropdown
        loadUserList();

        // 🚀 Toggle User Status Action
        toggleStatusBtn.addActionListener(e -> {
            int selectedIndex = userDropdown.getSelectedIndex();
            if (selectedIndex >= 0) {
                Passenger selectedUser = GuiManager.passengers.get(selectedIndex);
                selectedUser.setActive(!selectedUser.isActive());

                JOptionPane.showMessageDialog(this, "User " + selectedUser.getUserId() +
                        " is now " + (selectedUser.isActive() ? "Active" : "Deactivated") + ".", "Status Updated", JOptionPane.INFORMATION_MESSAGE);
                
                // ✅ Refresh dropdown list to show updated status
                loadUserList();
            } else {
                JOptionPane.showMessageDialog(this, "No user selected!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

    // ✅ Method to refresh the user dropdown list
    private void loadUserList() {
        userDropdown.removeAllItems();
        for (Passenger user : GuiManager.passengers) {
            userDropdown.addItem(user.getUserId() + " - " + user.getName() + " (" + (user.isActive() ? "Active" : "Deactivated") + ")");
        }
    }
}
