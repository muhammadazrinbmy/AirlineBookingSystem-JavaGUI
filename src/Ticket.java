public class Ticket {
    private String ticketNumber;
    private String passengerId; // ✅ Ensure this variable exists
    private Flight flight;
    private String seatNumber;
    private double price;
    private String status;

    public Ticket(String ticketNumber, String passengerId, Flight flight, String seatNumber, double price, String status) {
        this.ticketNumber = ticketNumber;
        this.passengerId = passengerId;
        this.flight = flight;
        this.seatNumber = seatNumber;
        this.price = price;
        this.status = status;
    }

    public String getTicketNumber() { return ticketNumber; }
    public String getPassengerId() { return passengerId; } // ✅ Use this method in Passenger.java
    public Flight getFlight() { return flight; }
    public String getSeatNumber() { return seatNumber; }
    public double getPrice() { return price; }
    public String getStatus() { return status; }

    // ✅ New Method (Fixes the Red Error)
    public String getPassenger() {
        return passengerId; // ✅ This allows Passenger.java to check ownership
    }
}
