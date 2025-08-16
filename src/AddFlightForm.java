import javax.swing.*;

public class AddFlightForm extends JFrame {
    public AddFlightForm() {
        setTitle("Add New Flight");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Enter Flight Details:");
        titleLabel.setBounds(150, 20, 200, 30);
        add(titleLabel);

        JLabel flightNumLabel = new JLabel("Flight Number:");
        flightNumLabel.setBounds(50, 60, 120, 25);
        add(flightNumLabel);

        JTextField flightNumField = new JTextField();
        flightNumField.setBounds(180, 60, 150, 25);
        add(flightNumField);

        JLabel sourceLabel = new JLabel("Source:");
        sourceLabel.setBounds(50, 100, 120, 25);
        add(sourceLabel);

        JTextField sourceField = new JTextField();
        sourceField.setBounds(180, 100, 150, 25);
        add(sourceField);

        JLabel destLabel = new JLabel("Destination:");
        destLabel.setBounds(50, 140, 120, 25);
        add(destLabel);

        JTextField destField = new JTextField();
        destField.setBounds(180, 140, 150, 25);
        add(destField);

        JLabel priceLabel = new JLabel("Price:");
        priceLabel.setBounds(50, 180, 120, 25);
        add(priceLabel);

        JTextField priceField = new JTextField();
        priceField.setBounds(180, 180, 150, 25);
        add(priceField);

        JButton addBtn = new JButton("Add Flight");
        addBtn.setBounds(100, 230, 200, 30);
        add(addBtn);

        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(150, 270, 100, 30);
        add(closeBtn);

        closeBtn.addActionListener(e -> dispose());

        // 🚀 Add Flight Action
        addBtn.addActionListener(e -> {
            String flightNumber = flightNumField.getText().trim();
            String source = sourceField.getText().trim();
            String destination = destField.getText().trim();
            double price;

            try {
                price = Double.parseDouble(priceField.getText().trim());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid price! Enter a number.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // ✅ Add flight to system
            Flight newFlight = new Flight(flightNumber, "New Airline", source, destination, "10:00 AM", "4:00 PM", price, 100);
            GuiManager.flights.add(newFlight);
            JOptionPane.showMessageDialog(this, "Flight added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        setVisible(true);
    }
}
