import javax.swing.*;
import java.util.List;


public class ViewTicketsForm extends JFrame {
    private Passenger passenger;  // ✅ Store the logged-in passenger

    // ✅ Constructor that accepts a Passenger object
    public ViewTicketsForm(Passenger passenger) {
        this.passenger = passenger; // ✅ Store the logged-in passenger
        setTitle("My Tickets - " + passenger.getName());
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Your Tickets:");
        titleLabel.setBounds(200, 20, 200, 30);
        add(titleLabel);

        JTextArea ticketArea = new JTextArea();
        ticketArea.setBounds(50, 60, 400, 200);
        ticketArea.setEditable(false);
        add(ticketArea);

        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(200, 280, 100, 30);
        add(closeBtn);

        closeBtn.addActionListener(e -> dispose());

        // 🚀 Display Passenger's Tickets
        StringBuilder ticketsText = new StringBuilder();
        for (Ticket ticket : GuiManager.tickets) {
        	if (ticket.getPassengerId().equals(passenger.getUserId())) { // ✅ Reference passenger object // ✅ Ensure method exists // ✅ Match userId instead of name
                ticketsText.append("Ticket: ").append(ticket.getTicketNumber())
                        .append(" | Flight: ").append(ticket.getFlight().getFlightNumber())
                        .append(" | Seat: ").append(ticket.getSeatNumber())
                        .append(" | Price: $").append(ticket.getPrice())
                        .append(" | Status: ").append(ticket.getStatus()).append("\n");
            }
        }

        if (ticketsText.length() == 0) {
            ticketArea.setText("You have no booked tickets.");
        } else {
            ticketArea.setText(ticketsText.toString());
        }

        setVisible(true);
    }
}
