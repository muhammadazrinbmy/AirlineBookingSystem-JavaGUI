import javax.swing.*;

public class RemoveFlightForm extends JFrame {
    public RemoveFlightForm() {
        setTitle("Remove a Flight");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Select Flight to Remove:");
        titleLabel.setBounds(150, 20, 200, 30);
        add(titleLabel);

        JComboBox<String> flightDropdown = new JComboBox<>();
        flightDropdown.setBounds(100, 70, 200, 30);
        add(flightDropdown);

        JButton removeBtn = new JButton("Remove Flight");
        removeBtn.setBounds(100, 120, 200, 30);
        add(removeBtn);

        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(150, 170, 100, 30);
        add(closeBtn);

        closeBtn.addActionListener(e -> dispose());

        // 🚀 Load flights into dropdown
        for (Flight flight : GuiManager.flights) {
            flightDropdown.addItem(flight.getFlightNumber() + " - " + flight.getSource() + " → " + flight.getDestination());
        }

        // 🚀 Remove Flight Action
        removeBtn.addActionListener(e -> {
            int selectedIndex = flightDropdown.getSelectedIndex();
            if (selectedIndex >= 0) {
                GuiManager.flights.remove(selectedIndex);
                JOptionPane.showMessageDialog(this, "Flight removed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "No flight selected!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}
