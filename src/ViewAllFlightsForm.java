import javax.swing.*;

public class ViewAllFlightsForm extends JFrame {
    public ViewAllFlightsForm() {
        setTitle("All Available Flights");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("All Flights");
        titleLabel.setBounds(200, 20, 200, 30);
        add(titleLabel);

        JTextArea flightArea = new JTextArea();
        flightArea.setBounds(50, 60, 400, 250);
        flightArea.setEditable(false);
        add(flightArea);

        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(200, 320, 100, 30);
        add(closeBtn);

        closeBtn.addActionListener(e -> dispose());

        // 🚀 Display Flights with Available Seats
        StringBuilder flightsText = new StringBuilder();
        for (Flight f : GuiManager.flights) {
            flightsText.append("Flight: ").append(f.getFlightNumber())
                    .append(" | ").append(f.getSource()).append(" → ").append(f.getDestination())
                    .append(" | Price: $").append(f.getPrice())
                    .append(" | Available Seats: ").append(f.getAvailableSeatNumbers().size()) // ✅ Show Available Seats
                    .append("\n");
        }

        if (flightsText.length() == 0) {
            flightArea.setText("No flights available.");
        } else {
            flightArea.setText(flightsText.toString());
        }

        setVisible(true);
    }
}

