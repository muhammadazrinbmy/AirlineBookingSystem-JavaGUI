import javax.swing.*;

public class FlightManagementMenu extends JFrame {
    public FlightManagementMenu() {
        setTitle("Flight Management");
        setSize(500, 350);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titleLabel = new JLabel("Flight Management");
        titleLabel.setBounds(180, 20, 200, 30);
        add(titleLabel);

        JButton viewFlightsBtn = new JButton("View Flights");
        viewFlightsBtn.setBounds(150, 60, 200, 30);
        add(viewFlightsBtn);

        JButton addFlightBtn = new JButton("Add a Flight");
        addFlightBtn.setBounds(150, 100, 200, 30);
        add(addFlightBtn);

        JButton deleteFlightBtn = new JButton("Delete a Flight");
        deleteFlightBtn.setBounds(150, 140, 200, 30);
        add(deleteFlightBtn);

        JButton updatePriceBtn = new JButton("Update Flight Price");
        updatePriceBtn.setBounds(150, 180, 200, 30);
        add(updatePriceBtn);

        JButton manageSeatsBtn = new JButton("Manage Seat Availability");
        manageSeatsBtn.setBounds(150, 220, 200, 30);
        add(manageSeatsBtn);

        JButton backBtn = new JButton("Back to Admin Panel");
        backBtn.setBounds(150, 260, 200, 30);
        add(backBtn);

        // 🚀 Button Actions
        viewFlightsBtn.addActionListener(e -> new ViewAllFlightsForm());
        addFlightBtn.addActionListener(e -> new AddFlightForm());
        deleteFlightBtn.addActionListener(e -> new RemoveFlightForm());
        updatePriceBtn.addActionListener(e -> new UpdateFlightPriceForm());
        manageSeatsBtn.addActionListener(e -> new ManageSeatsForm());
        backBtn.addActionListener(e -> dispose());

        setVisible(true);
    }
}
