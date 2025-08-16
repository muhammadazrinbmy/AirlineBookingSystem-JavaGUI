import javax.swing.*;
import java.util.ArrayList;

public class FlightListView extends JFrame {
    public FlightListView() {
        setTitle("Available Flights");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Available Flights");
        titleLabel.setBounds(250, 20, 200, 30);
        add(titleLabel);

        JTextArea flightArea = new JTextArea();
        flightArea.setBounds(50, 60, 500, 250);
        flightArea.setEditable(false);
        add(flightArea);

        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(250, 320, 100, 30);
        add(closeBtn);

        closeBtn.addActionListener(e -> dispose());

        // 🚀 Display Flights
        if (GuiManager.flights.isEmpty()) {
            flightArea.setText("No flights available.");
        } else {
            StringBuilder flightsText = new StringBuilder();
            for (Flight f : GuiManager.flights) {
                flightsText.append("Flight: ").append(f.getFlightNumber())
                        .append(" | ").append(f.getSource()).append(" → ").append(f.getDestination())
                        .append(" | Price: $").append(f.getPrice()).append("\n");
            }
            flightArea.setText(flightsText.toString());
        }

        setVisible(true);
    }
}

