import javax.swing.*;
import java.util.ArrayList;

public class PassengerMenu extends JFrame {
    private Passenger loggedInPassenger;

    public PassengerMenu(Passenger passenger) {
        this.loggedInPassenger = passenger;
        setTitle("Passenger Menu - " + passenger.getName());
        setSize(500, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel welcomeLabel = new JLabel("Welcome, " + passenger.getName());
        welcomeLabel.setBounds(180, 30, 200, 30);
        add(welcomeLabel);

        // Buttons
        JButton viewFlightsBtn = new JButton("View Available Flights");
        viewFlightsBtn.setBounds(150, 80, 200, 30);
        add(viewFlightsBtn);

        JButton bookFlightBtn = new JButton("Book a Flight");
        bookFlightBtn.setBounds(150, 120, 200, 30);
        add(bookFlightBtn);

        JButton viewTicketsBtn = new JButton("View My Tickets");
        viewTicketsBtn.setBounds(150, 160, 200, 30);
        add(viewTicketsBtn);

        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setBounds(150, 200, 200, 30);
        add(logoutBtn);

        // Button Actions
        viewFlightsBtn.addActionListener(e -> new FlightListView());
        bookFlightBtn.addActionListener(e -> new FlightBookingForm(passenger));
        viewTicketsBtn.addActionListener(e -> new ViewTicketsForm(passenger));
        logoutBtn.addActionListener(e -> {
            dispose();
            JOptionPane.showMessageDialog(null, "Logged out successfully!");
        });

        setVisible(true);
    }
}
