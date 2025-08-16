import java.util.ArrayList;

public class Airline {
    private String airlineName;
    private ArrayList<Flight> flights;

    public Airline(String airlineName) {
        this.airlineName = airlineName;
        this.flights = new ArrayList<>();
    }

    public String getAirlineName() {
        return airlineName;
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
        System.out.println("Flight " + flight.getFlightNumber() + " has been added to " + airlineName);
    }

    public void removeFlight(String flightNumber) {
        for (int i = 0; i < flights.size(); i++) {
            if (flights.get(i).getFlightNumber().equals(flightNumber)) {
                flights.remove(i);
                System.out.println("Flight " + flightNumber + " has been removed.");
                return;
            }
        }
        System.out.println("Flight not found.");
    }

    public void displayFlights() {
        if (flights.isEmpty()) {
            System.out.println("No flights available for " + airlineName);
            return;
        }

        System.out.println("\nFlights under " + airlineName + ":");
        for (Flight flight : flights) {
            System.out.println("Flight " + flight.getFlightNumber() + " | " + flight.getSource() + " → " + flight.getDestination() +
                " | Departure: " + flight.getDepartureTime() + " | Arrival: " + flight.getArrivalTime() + 
                " | Price: $" + flight.getPrice() + " | Available Seats: " + flight.getAvailableSeatNumbers().size());
        }
    }

    public Flight findFlight(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }
}

