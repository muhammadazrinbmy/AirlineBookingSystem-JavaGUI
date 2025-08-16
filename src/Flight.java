import java.util.ArrayList;


public class Flight {
    private String flightNumber;
    private String airline;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    private double price;
    //private int availableSeats;
    
    private ArrayList<String> seatNumbers; //added 
    private ArrayList<String> bookedSeats; //added

    public Flight(String flightNumber, String airline, String source, String destination,
                  String departureTime, String arrivalTime, double price, int seatCount) {
        this.flightNumber = flightNumber;
        this.airline = airline;
        this.source = source;
        this.destination = destination;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.price = price;
        //this.availableSeats = availableSeats;
        
        this.seatNumbers = new ArrayList<>(); // added
        this.bookedSeats = new ArrayList<>(); // added 
        
     // Generate seat numbers dynamically (e.g., S1, S2, S3, ...)
        for (int i = 1; i <= seatCount; i++) {
            seatNumbers.add("S" + i);
        }     
        
    }

    // Getter Methods
    public String getFlightNumber() { return flightNumber; }
    public String getAirline() { return airline; }
    public String getSource() { return source; }
    public String getDestination() { return destination; }
    public String getDepartureTime() { return departureTime; }
    public String getArrivalTime() { return arrivalTime; }
    public double getPrice() { return price; }
    //public int getAvailableSeats() { return availableSeats; }
    
    public ArrayList<String> getAvailableSeatNumbers() { return seatNumbers; }
    public ArrayList<String> getBookedSeats() {
        ArrayList<String> bookedSeats = new ArrayList<>();
        for (Ticket ticket : GuiManager.tickets) {
            if (ticket.getFlight().getFlightNumber().equals(this.getFlightNumber())) {
                bookedSeats.add(ticket.getSeatNumber());
            }
        }
        return bookedSeats;
    }


 // Book a specific seat
    
    public boolean bookSeat(String seatNumber) {
        if (bookedSeats.contains(seatNumber)) {  // ❌ Already booked
            System.out.println("Error: Seat " + seatNumber + " is already booked.");
            return false;
        }
        
        if (seatNumbers.contains(seatNumber)) {  // ✅ Check if seat is available
            seatNumbers.remove(seatNumber);  // ✅ Remove from available seats
            bookedSeats.add(seatNumber);  // ✅ Add to booked seats
            return true;
        }

        System.out.println("Error: Invalid seat number."); // 🚨 Catch invalid seat numbers
        return false;
    }
    
    public ArrayList<String> getAllSeatNumbers() {
        return new ArrayList<>(seatNumbers); // ✅ Returns dynamic seat list
    }

    


    
    public void updateSeatNumbers(int newSeatCount) {
        seatNumbers.clear(); // Reset seat list
        bookedSeats.clear(); // Clear booked seats

        for (int i = 1; i <= newSeatCount; i++) {
            seatNumbers.add("S" + i);
        }
    }

    public void setPrice(double price) {
        this.price = price;
    }
    
    
}



