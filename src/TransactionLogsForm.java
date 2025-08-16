import javax.swing.*;

public class TransactionLogsForm extends JFrame {
    public TransactionLogsForm() {
        setTitle("Transaction Logs - Purchased Tickets");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Transaction Logs - Purchased Tickets");
        titleLabel.setBounds(180, 20, 300, 30);
        add(titleLabel);

        JTextArea logsArea = new JTextArea();
        logsArea.setBounds(50, 60, 500, 250);
        logsArea.setEditable(false);
        add(logsArea);

        JButton closeBtn = new JButton("Close");
        closeBtn.setBounds(250, 320, 100, 30);
        add(closeBtn);

        closeBtn.addActionListener(e -> dispose());

        // 🚀 Display Transaction Logs
        StringBuilder logsText = new StringBuilder();
        for (Ticket ticket : GuiManager.tickets) {
            logsText.append("Ticket: ").append(ticket.getTicketNumber())
                    .append(" | Passenger: ").append(ticket.getPassengerId())
                    .append(" | Flight: ").append(ticket.getFlight().getFlightNumber())
                    .append(" | Seat: ").append(ticket.getSeatNumber())
                    .append(" | Price: $").append(ticket.getPrice())
                    .append(" | Status: ").append(ticket.getStatus())
                    .append("\n");
        }

        if (logsText.length() == 0) {
            logsArea.setText("No transactions recorded.");
        } else {
            logsArea.setText(logsText.toString());
        }

        setVisible(true);
    }
}
