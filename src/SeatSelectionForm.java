import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SeatSelectionForm extends JDialog {
    private ArrayList<String> availableSeats;
    private ArrayList<String> bookedSeats;
    private String selectedSeat = null;
    private JPanel seatPanel;
    private JButton moreSeatsBtn; // Declare the button here
    private int currentSeatIndex = 0;

    public SeatSelectionForm(JFrame parent, ArrayList<String> allSeats, ArrayList<String> bookedSeats) {
        super(parent, "Select Your Seat", true);
        this.availableSeats = new ArrayList<>(allSeats);
        this.bookedSeats = bookedSeats;
        setSize(350, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        // Create seat panel
        seatPanel = new JPanel();
        seatPanel.setLayout(new BoxLayout(seatPanel, BoxLayout.X_AXIS)); // Horizontal layout

        // Create left and right seat panels (for left 3 seats, right 3 seats)
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 5));

        // Create aisle label
        JLabel aisle = new JLabel("Aisle", JLabel.CENTER);

        // Initialize "More Seats" button before calling loadSeats()
        moreSeatsBtn = new JButton("More Seats");
        moreSeatsBtn.setFont(new Font("Arial", Font.BOLD, 12));
        moreSeatsBtn.addActionListener(e -> loadSeats(leftPanel, rightPanel, aisle));

        // Load the first batch of seats
        loadSeats(leftPanel, rightPanel, aisle);

        // Create confirmation button
        JButton confirmBtn = new JButton("Confirm");
        confirmBtn.setFont(new Font("Arial", Font.BOLD, 12));
        confirmBtn.addActionListener(e -> {
            if (selectedSeat == null) {
                JOptionPane.showMessageDialog(this, "Please select a seat!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Seat " + selectedSeat + " selected!");
                dispose();
            }
        });

        // Add components to the layout
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1));
        buttonPanel.add(moreSeatsBtn);
        buttonPanel.add(confirmBtn);

        // Add left panel, aisle, and right panel to the main seat panel
        seatPanel.add(leftPanel);
        seatPanel.add(aisle);
        seatPanel.add(rightPanel);

        add(seatPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack(); // Auto-adjust size
        setResizable(false);
        setVisible(true);
    }

    // Load seats dynamically in batches and add to the panels
    private void loadSeats(JPanel leftPanel, JPanel rightPanel, JLabel aisle) {
        leftPanel.removeAll();
        rightPanel.removeAll();

        int seatsDisplayed = 0;
        int totalSeats = availableSeats.size();

        for (int i = currentSeatIndex; i < totalSeats; i++) {
            if (seatsDisplayed == 4) break; // Limit to 6 seats per page (3 left + 1 aisle + 3 right)

            String seatNumber = availableSeats.get(i);
            JButton seatBtn = new JButton(seatNumber);
            seatBtn.setFont(new Font("Arial", Font.BOLD, 10));

            if (bookedSeats.contains(seatNumber)) {
                seatBtn.setText("NA");
                seatBtn.setEnabled(false); // Disable booked seats
            } else {
                seatBtn.addActionListener(new SeatClickListener(seatNumber));
            }

            // Add seats to the left or right panel
            if (seatsDisplayed < 2) {
                leftPanel.add(seatBtn); // Left side seats (S1, S2, S3)
            } else if (seatsDisplayed >= 2 && seatsDisplayed < 4) {
                rightPanel.add(seatBtn); // Right side seats (S4, S5, S6)
            }

            seatsDisplayed++;
        }

        currentSeatIndex += seatsDisplayed; // Update index for the next batch

        // Hide "More Seats" button if no more seats are available
        moreSeatsBtn.setVisible(currentSeatIndex < totalSeats);

        seatPanel.revalidate();
        seatPanel.repaint();
    }

    // Handles seat selection
    private class SeatClickListener implements ActionListener {
        private String seatNumber;

        public SeatClickListener(String seatNumber) {
            this.seatNumber = seatNumber;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            selectedSeat = seatNumber;
            JOptionPane.showMessageDialog(null, "You selected seat: " + seatNumber);
        }
    }

    // Get the selected seat
    public String getSelectedSeat() {
        return selectedSeat;
    }
}

