import javax.swing.*;
import java.util.ArrayList;

public class FlightBookingForm extends JFrame {
    private Passenger passenger;
    private JComboBox<String> flightDropdown;
    private JButton selectSeatBtn;
    private JLabel selectedSeatLabel;
    private String chosenSeat = null;
    
 // ✅ Load available flights into dropdown
    private void loadFlightList() {
        flightDropdown.removeAllItems();
        for (Flight flight : GuiManager.flights) {
            flightDropdown.addItem(flight.getFlightNumber() + " - " + flight.getSource() + " → " + flight.getDestination());
        }
    }
    
 // ✅ Simulate payment processing
    private boolean processPayment(double amount, String method) {
        JOptionPane.showMessageDialog(this, "Processing payment of $" + amount + " using " + method + "...");

        // Simulate payment processing delay
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            return false;
        }

        return Math.random() > 0.2; // ✅ 80% chance of success
    }



    public FlightBookingForm(Passenger passenger) {
        this.passenger = passenger;
        setTitle("Book a Flight");
        setSize(500, 450);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Select a Flight to Book");
        titleLabel.setBounds(150, 20, 250, 30);
        add(titleLabel);

        flightDropdown = new JComboBox<>();
        flightDropdown.setBounds(150, 70, 200, 30);
        add(flightDropdown);

        JLabel seatLabel = new JLabel("Choose a Seat:");
        seatLabel.setBounds(150, 110, 200, 30);
        add(seatLabel);

        selectSeatBtn = new JButton("Select Seat");
        selectSeatBtn.setBounds(150, 140, 200, 30);
        add(selectSeatBtn);

        selectedSeatLabel = new JLabel("No seat selected");
        selectedSeatLabel.setBounds(150, 180, 200, 30);
        add(selectedSeatLabel);

        JButton bookBtn = new JButton("Proceed to Payment");
        bookBtn.setBounds(150, 230, 200, 30);
        add(bookBtn);

        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(150, 270, 200, 30);
        add(closeBtn);

        closeBtn.addActionListener(e -> dispose());

        // 🚀 Load available flights into dropdown
        loadFlightList();

        // ✅ Open Seat Selection Grid
        selectSeatBtn.addActionListener(e -> {
            int selectedIndex = flightDropdown.getSelectedIndex();
            if (selectedIndex < 0) {
                JOptionPane.showMessageDialog(this, "Please select a flight first!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Flight selectedFlight = GuiManager.flights.get(selectedIndex);
            ArrayList<String> bookedSeats = selectedFlight.getBookedSeats();
            ArrayList<String> allSeats = selectedFlight.getAllSeatNumbers();

            // ✅ Open seat selection UI
            SeatSelectionForm seatForm = new SeatSelectionForm(this, allSeats, bookedSeats);
            
            // ✅ Wait for user selection without freezing UI
            SwingUtilities.invokeLater(() -> {
                chosenSeat = seatForm.getSelectedSeat();
                if (chosenSeat != null) {
                    selectedSeatLabel.setText("Selected Seat: " + chosenSeat);
                }
            });
        });

        // 🚀 Booking Process with Payment
     // 🚀 Booking Process with Payment
        bookBtn.addActionListener(e -> {
            int selectedIndex = flightDropdown.getSelectedIndex();
            if (selectedIndex < 0 || chosenSeat == null) {
                JOptionPane.showMessageDialog(this, "Please select a flight and seat!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Flight selectedFlight = GuiManager.flights.get(selectedIndex);

            // ✅ Confirm Payment
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Total Price: $" + selectedFlight.getPrice() + "\nProceed to payment?", 
                "Payment Confirmation", JOptionPane.YES_NO_OPTION);
            
            if (confirm != JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this, "Booking cancelled. Returning to Passenger Menu.");
                return;
            }

            // ✅ Select Payment Method
            String[] paymentMethods = {"Credit Card", "PayPal", "Bank Transfer"};
            String paymentMethod = (String) JOptionPane.showInputDialog(this, 
                "Select Payment Method:", "Payment", 
                JOptionPane.QUESTION_MESSAGE, null, paymentMethods, paymentMethods[0]);

            if (paymentMethod == null) {
                JOptionPane.showMessageDialog(this, "Booking cancelled. Returning to Passenger Menu.");
                return;
            }

            boolean paymentSuccess = processPayment(selectedFlight.getPrice(), paymentMethod);

            if (!paymentSuccess) {
                JOptionPane.showMessageDialog(this, "Payment failed. Booking not completed.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // ✅ Generate the ticket after payment is successful
            String ticketNumber = "T" + (GuiManager.tickets.size() + 1);
            Ticket newTicket = new Ticket(ticketNumber, passenger.getUserId(), selectedFlight, chosenSeat, selectedFlight.getPrice(), "Confirmed");

            GuiManager.tickets.add(newTicket); // ✅ Add ticket to the list
            JOptionPane.showMessageDialog(this, "Payment successful!\nTicket Number: " + ticketNumber, "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });


        setVisible(true);
    }
}

