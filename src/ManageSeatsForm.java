import javax.swing.*;

public class ManageSeatsForm extends JFrame {
    private JLabel currentSeatsLabel;
    private JComboBox<String> flightDropdown;

    public ManageSeatsForm() {
        setTitle("Manage Seat Availability");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Select Flight:");
        titleLabel.setBounds(150, 20, 200, 30);
        add(titleLabel);

        flightDropdown = new JComboBox<>();
        flightDropdown.setBounds(100, 70, 200, 30);
        add(flightDropdown);

        currentSeatsLabel = new JLabel("Available Seats: -"); // ✅ Dynamic seat count
        currentSeatsLabel.setBounds(100, 110, 200, 25);
        add(currentSeatsLabel);

        JLabel seatsLabel = new JLabel("New Seat Count:");
        seatsLabel.setBounds(50, 150, 120, 25);
        add(seatsLabel);

        JTextField seatsField = new JTextField();
        seatsField.setBounds(180, 150, 150, 25);
        add(seatsField);

        JButton updateBtn = new JButton("Update Seats");
        updateBtn.setBounds(100, 200, 200, 30);
        add(updateBtn);

        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(150, 250, 100, 30);
        add(closeBtn);

        closeBtn.addActionListener(e -> dispose());

        // 🚀 Load flights into dropdown
        loadFlightList();

        // ✅ Update seat count label when selecting a flight
        flightDropdown.addActionListener(e -> updateSeatCount());

        // 🚀 Update Seat Availability Action
        updateBtn.addActionListener(e -> {
            int selectedIndex = flightDropdown.getSelectedIndex();
            if (selectedIndex >= 0) {
                try {
                    int newSeats = Integer.parseInt(seatsField.getText().trim());
                    
                    // ✅ Update seat numbers
                    GuiManager.flights.get(selectedIndex).updateSeatNumbers(newSeats);
                    
                    JOptionPane.showMessageDialog(this, "Seat availability updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                    // ✅ Refresh seat count after update
                    updateSeatCount();
                    
                    dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Invalid number! Enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "No flight selected!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

    // ✅ Load Flights into Dropdown
    private void loadFlightList() {
        flightDropdown.removeAllItems();
        for (Flight flight : GuiManager.flights) {
            flightDropdown.addItem(flight.getFlightNumber() + " - " + flight.getSource() + " → " + flight.getDestination());
        }
    }

    // ✅ Update Available Seats Label
    private void updateSeatCount() {
        int selectedIndex = flightDropdown.getSelectedIndex();
        if (selectedIndex >= 0) {
            int availableSeats = GuiManager.flights.get(selectedIndex).getAvailableSeatNumbers().size();
            currentSeatsLabel.setText("Available Seats: " + availableSeats);
        } else {
            currentSeatsLabel.setText("Available Seats: -");
        }
    }
}

