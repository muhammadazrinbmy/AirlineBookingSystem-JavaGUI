import javax.swing.*;

public class UpdateFlightPriceForm extends JFrame {
    public UpdateFlightPriceForm() {
        setTitle("Update Flight Price");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Select Flight to Update:");
        titleLabel.setBounds(150, 20, 200, 30);
        add(titleLabel);

        JComboBox<String> flightDropdown = new JComboBox<>();
        flightDropdown.setBounds(100, 70, 200, 30);
        add(flightDropdown);

        JLabel priceLabel = new JLabel("New Price:");
        priceLabel.setBounds(50, 120, 120, 25);
        add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBounds(180, 120, 150, 25);
        add(priceField);

        JButton updateBtn = new JButton("Update Price");
        updateBtn.setBounds(100, 170, 200, 30);
        add(updateBtn);

        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(150, 210, 100, 30);
        add(closeBtn);

        closeBtn.addActionListener(e -> dispose());

        // 🚀 Load flights into dropdown
        for (Flight flight : GuiManager.flights) {
            flightDropdown.addItem(flight.getFlightNumber() + " - " + flight.getSource() + " → " + flight.getDestination());
        }

        // 🚀 Update Price Action
        updateBtn.addActionListener(e -> {
            int selectedIndex = flightDropdown.getSelectedIndex();
            if (selectedIndex >= 0) {
                try {
                    double newPrice = Double.parseDouble(priceField.getText().trim());
                    GuiManager.flights.get(selectedIndex).setPrice(newPrice);
                    JOptionPane.showMessageDialog(this, "Flight price updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid price! Enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No flight selected!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }
}
