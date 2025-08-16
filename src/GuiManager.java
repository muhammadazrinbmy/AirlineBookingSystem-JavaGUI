import javax.swing.*;
import java.util.ArrayList;

public class GuiManager {
	public static ArrayList<Passenger> passengers = new ArrayList<>();
	public static ArrayList<Flight> flights = new ArrayList<>();
	 public static ArrayList<Ticket> tickets = new ArrayList<>(); // 🚀 Add ticket storage

    static {
        // 🚀 Add some sample flights
        flights.add(new Flight("FL001", "Airline A", "Singapore", "Tokyo", "10:00 AM", "4:00 PM", 300.50, 100));
        flights.add(new Flight("FL002", "Airline B", "Singapore", "London", "6:00 AM", "12:00 PM", 550.75, 150));
    }
	
	
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Airline Booking System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(600, 400);
            frame.setLocationRelativeTo(null);
            frame.setLayout(null);

            // Welcome Label
            JLabel welcomeLabel = new JLabel("Welcome to the Airline Booking System");
            welcomeLabel.setBounds(150, 50, 300, 30);
            frame.add(welcomeLabel);

            // Buttons
            JButton registerPassengerBtn = new JButton("Register Passenger");
            registerPassengerBtn.setBounds(200, 100, 200, 30);
            frame.add(registerPassengerBtn);

            JButton loginPassengerBtn = new JButton("Login as Passenger");
            loginPassengerBtn.setBounds(200, 150, 200, 30);
            frame.add(loginPassengerBtn);

            JButton loginAdminBtn = new JButton("Login as Admin");
            loginAdminBtn.setBounds(200, 200, 200, 30);
            frame.add(loginAdminBtn);

            JButton exitBtn = new JButton("Exit");
            exitBtn.setBounds(200, 250, 200, 30);
            frame.add(exitBtn);

            // Exit Button Functionality
            exitBtn.addActionListener(e -> System.exit(0));

            // 🚀 Add Button Actions
            registerPassengerBtn.addActionListener(e -> new PassengerRegistrationForm());
            loginAdminBtn.addActionListener(e -> new AdminLoginForm());
            loginPassengerBtn.addActionListener(e -> new PassengerLoginForm());
           

            frame.setVisible(true);
        });
    }
}

