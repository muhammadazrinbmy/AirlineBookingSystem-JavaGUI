import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;


public class MainTesting {
    private static ArrayList<Flight> flights = new ArrayList<>();
    private static ArrayList<Passenger> passengers = new ArrayList<>();
    private static ArrayList<Admin> admins = new ArrayList<>();
    private static ArrayList<Ticket> tickets = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        admins.add(new Admin("A1", "AdminUser", "admin@airline.com", "admin123"));

        flights.add(new Flight("FL001", "Airline A", "Singapore", "Tokyo", "10:00 AM", "4:00 PM", 300.50, 100));
        flights.add(new Flight("FL002", "Airline B", "Singapore", "London", "6:00 AM", "12:00 PM", 550.75, 150));

        while (true) {
            System.out.println("\nWelcome to the Airline Ticket Booking System");
            System.out.println("1. Register as a New Passenger");
            System.out.println("2. Login as Passenger");
            System.out.println("3. Login as Admin");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");

            int choice = getValidatedChoice(1, 4);

            switch (choice) {
                case 1:
                    registerPassenger();
                    break;
                case 2:
                    loginPassenger();
                    break;
                case 3:
                    loginAdmin();
                    break;
                case 4:
                    System.out.println("Exiting system...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void adminMenu() {
        while (true) {
            System.out.println("\nAdmin Panel:");
            System.out.println("1. Flight Management");
            System.out.println("2. User Management");
            System.out.println("3. Logout");
            System.out.print("Choose an option: ");

            int choice = getValidatedChoice(1, 3);

            switch (choice) {
                case 1:
                    flightManagementMenu();
                    break;
                case 2:
                    userManagementMenu();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void passengerMenu(Passenger passenger) {
        while (true) {
            System.out.println("\nPassenger Menu:");
            System.out.println("1. View Available Flights");
            System.out.println("2. Book a Flight");
            System.out.println("3. View My Tickets");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");

            int choice = getValidatedChoice(1, 4);

            switch (choice) {
                case 1:
                    viewFlights();
                    break;
                case 2:
                    bookFlight(passenger);
                    break;
                case 3:
                    passenger.viewTickets();
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void bookFlight(Passenger passenger) {
        if (flights.isEmpty()) {
            System.out.println("No flights available for booking.");
            return;
        }

        System.out.println("\nAvailable Flights:");
        for (Flight f : flights) {
            System.out.println("Flight Number: " + f.getFlightNumber() + " | Airline: " + f.getAirline() +
                    " | Source: " + f.getSource() + " | Destination: " + f.getDestination() +
                    " | Departure: " + f.getDepartureTime() + " | Arrival: " + f.getArrivalTime() +
                    " | Price: $" + f.getPrice() + " | Available Seats: " + f.getAvailableSeatNumbers().size());
        }

        System.out.println("\nEnter Flight Number to book or type 'exit' to return:");
        String flightNumber = scanner.nextLine();

        if (flightNumber.equalsIgnoreCase("exit")) {
            System.out.println("Returning to Passenger Menu...");
            return; // Go back to the menu
        }

        Flight selectedFlight = null;
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                selectedFlight = flight;
                break;
            }
        }

        if (selectedFlight == null) {
            System.out.println("Flight not found. Returning to Passenger Menu...");
            return;
        }

        ArrayList<String> availableSeats = selectedFlight.getAvailableSeatNumbers();
        if (availableSeats.isEmpty()) {
            System.out.println("No available seats for this flight.");
            return;
        }

        System.out.println("Available Seats: " + availableSeats);
        System.out.print("Choose a seat or type 'exit' to cancel: ");
        String chosenSeat = scanner.nextLine();

        if (chosenSeat.equalsIgnoreCase("exit")) {
            System.out.println("Booking cancelled. Returning to Passenger Menu...");
            return;
        }

        String ticketNumber ="";  // ✅ Declare ticketNumber outside

        if (!selectedFlight.bookSeat(chosenSeat)) {  
            System.out.println("Booking failed. Returning to Passenger Menu...");
            return;  // 🚨 EXIT IMMEDIATELY to prevent ticket creation
        }

        // ✅ Assign ticketNumber only if booking succeeds
        ticketNumber = "T" + (tickets.size() + 1);
        tickets.add(new Ticket(ticketNumber, passenger.getUserId(), selectedFlight, chosenSeat, selectedFlight.getPrice(), "Confirmed"));
        System.out.println("Booking successful! Your ticket number: " + ticketNumber);

        // ✅ Proceed to payment
        double totalPrice = selectedFlight.getPrice();
        System.out.println("\nTotal Price: $" + totalPrice);
        System.out.println("Proceed to payment? (yes/no)");
        String confirmPayment = scanner.nextLine();

        if (!confirmPayment.equalsIgnoreCase("yes")) {
            System.out.println("Booking cancelled. Returning to Passenger Menu...");
            selectedFlight.getAvailableSeatNumbers().add(chosenSeat); // Restore seat
            return;
        }

        // ✅ Process payment only if confirmed
        boolean paymentSuccess = processPayment(totalPrice);

        if (!paymentSuccess) {
            System.out.println("Payment failed. Booking not completed.");
            selectedFlight.getAvailableSeatNumbers().add(chosenSeat); // Restore seat
            return;
        }

        System.out.println("Payment successful! Your ticket is confirmed.");



        // Generate ticket and confirm booking
        
    }
    
    private static boolean processPayment(double amount) {
        System.out.println("\nSelect Payment Method:");
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        System.out.println("3. Bank Transfer");
        System.out.print("Enter your choice: ");

        int paymentChoice = getValidatedChoice(1, 3);

        System.out.println("\nProcessing payment of $" + amount + " using " + 
            (paymentChoice == 1 ? "Credit Card" : paymentChoice == 2 ? "PayPal" : "Bank Transfer") + "...");

        // Simulate payment processing delay
        try {
            Thread.sleep(2000); // Simulate a delay of 2 seconds
        } catch (InterruptedException e) {
            System.out.println("Unexpected error in payment processing.");
            return false;
        }

        // Simulate random success or failure
        if (Math.random() > 0.2) { // 80% chance of success
            System.out.println("Payment Successful!");
            return true;
        } else {
            System.out.println("Payment Failed. Please try again.");
            return false;
        }
    }

    private static void flightManagementMenu() {
        while (true) {
            System.out.println("\nFlight Management:");
            System.out.println("1. View Flights");
            System.out.println("2. Add a Flight");
            System.out.println("3. Delete a Flight");
            System.out.println("4. Update Flight Price");
            System.out.println("5. Manage Seat Availability");
            System.out.println("6. Back to Admin Panel");
            System.out.print("Choose an option: ");

            int choice = getValidatedChoice(1, 6);

            switch (choice) {
                case 1:
                    viewFlights();
                    break;
                case 2:
                    addFlight();
                    break;
                case 3:
                    deleteFlight();
                    break;
                case 4:
                    updateFlightPrice();
                    break;
                case 5:
                    manageSeatAvailability();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void viewFlights() {
        if (flights.isEmpty()) {
            System.out.println("No flights available.");
            return;
        }
        System.out.println("\nAvailable Flights:");
        for (Flight f : flights) {
            System.out.println("Flight Number: " + f.getFlightNumber() + " | Airline: " + f.getAirline() +
                    " | Source: " + f.getSource() + " | Destination: " + f.getDestination() +
                    " | Departure: " + f.getDepartureTime() + " | Arrival: " + f.getArrivalTime() +
                    " | Price: $" + f.getPrice() + " | Available Seats: " + f.getAvailableSeatNumbers().size());

        }
    }

    private static void addFlight() {
        System.out.print("Enter Flight Number: ");
        String flightNumber = scanner.nextLine();
        System.out.print("Enter Airline Name: ");
        String airline = scanner.nextLine();
        System.out.print("Enter Source: ");
        String source = scanner.nextLine();
        System.out.print("Enter Destination: ");
        String destination = scanner.nextLine();
        System.out.print("Enter Departure Time: ");
        String departureTime = scanner.nextLine();
        System.out.print("Enter Arrival Time: ");
        String arrivalTime = scanner.nextLine();
        System.out.print("Enter Price: ");
        double price = Double.parseDouble(scanner.nextLine());  // Use nextLine() + parsing to avoid skipping
        System.out.print("Enter Available Seats: ");
        int availableSeats = Integer.parseInt(scanner.nextLine());
        scanner.nextLine();

        flights.add(new Flight(flightNumber, airline, source, destination, departureTime, arrivalTime, price, availableSeats));
        System.out.println("Flight added successfully!");
    }

    private static void deleteFlight() {
        System.out.print("Enter Flight Number to Delete: ");
        String flightNumber = scanner.nextLine();

        boolean removed = flights.removeIf(flight -> flight.getFlightNumber().equals(flightNumber));
        if (removed) {
            System.out.println("Flight deleted successfully.");
        } else {
            System.out.println("Flight not found.");
        }
    }

    private static void updateFlightPrice() {
        System.out.print("Enter Flight Number to Update Price: ");
        String flightNumber = scanner.nextLine();

        for (Flight f : flights) {
            if (f.getFlightNumber().equals(flightNumber)) {
                System.out.print("Enter new price: ");
                
                if (scanner.hasNextDouble()) { // Ensure valid numeric input
                    double newPrice = scanner.nextDouble();
                    scanner.nextLine(); // Clear buffer

                    f.setPrice(newPrice); // ✅ Update the flight price
                    System.out.println("Flight price updated successfully.");
                } else {
                    System.out.println("Invalid input. Please enter a numeric price.");
                    scanner.nextLine(); // Clear invalid input
                }
                return;
            }
        }
        System.out.println("Flight not found.");
    }
    private static void manageSeatAvailability() {
        System.out.print("Enter Flight Number to Manage Seat Availability: ");
        String flightNumber = scanner.nextLine();

        for (Flight f : flights) {
            if (f.getFlightNumber().equals(flightNumber)) {
                System.out.print("Enter new available seat count: ");
                
                int newSeatCount = scanner.nextInt();
                scanner.nextLine(); // Clear scanner buffer
                f.updateSeatNumbers(newSeatCount);

                
                //f.setAvailableSeats(scanner.nextInt());
                scanner.nextLine();
                System.out.println("Seat availability updated successfully.");
                return;
            }
        }
        System.out.println("Flight not found.");
    }


    private static void userManagementMenu() {
        while (true) {
            System.out.println("\nUser Management:");
            System.out.println("1. View Users");
            System.out.println("2. Deactivate/Reactivate User");
            System.out.println("3. Back to Admin Panel");
            System.out.print("Choose an option: ");

            int choice = getValidatedChoice(1, 3);

            switch (choice) {
                case 1:
                    viewUsers();
                    break;
                case 2:
                    manageUserStatus();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void viewUsers() {
        System.out.println("\nRegistered Users:");
        if (passengers.isEmpty()) {
            System.out.println("No users registered.");
            return;
        }
        for (Passenger p : passengers) {
            System.out.println("ID: " + p.userId + " | Name: " + p.getName() + " | Email: " + p.email);
        }
    }

    private static void manageUserStatus() {
        System.out.print("Enter User ID to Deactivate/Reactivate: ");
        String userId = scanner.nextLine();

        for (Passenger p : passengers) {
            if (p.userId.equals(userId)) {
                System.out.print("Enter 'deactivate' or 'reactivate': ");
                String action = scanner.nextLine().toLowerCase();

                if (action.equals("deactivate")) {
                    p.password = null;
                    System.out.println("User " + p.getName() + " has been deactivated.");
                } else if (action.equals("reactivate")) {
                    System.out.print("Enter new password: ");
                    p.password = scanner.nextLine();
                    System.out.println("User " + p.getName() + " has been reactivated.");
                } else {
                    System.out.println("Invalid action.");
                }
                return;
            }
        }
        System.out.println("User not found.");
    }
    
    private static int getValidatedChoice(int min, int max) {
        while (true) {
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.println("Invalid choice. Enter a number between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    private static void registerPassenger() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        passengers.add(new Passenger(userId, name, email, password));
        System.out.println("Registration successful! You can now log in.");
    }

    private static void loginPassenger() {
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for (Passenger p : passengers) {
            if (p.login(email, password)) {
                System.out.println("Login successful. Welcome, " + p.getName());
                passengerMenu(p);
                return;
            }
        }
        System.out.println("Invalid login credentials.");
    }

    private static void loginAdmin() {
        System.out.print("Enter admin email: ");
        String email = scanner.nextLine();
        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        for (Admin a : admins) {
            if (a.login(email, password)) {
                System.out.println("Admin login successful. Welcome, " + a.getName());
                adminMenu();
                return;
            }
        }
        System.out.println("Invalid admin credentials.");
    }
    
    public static ArrayList<Ticket> getTickets() {
        return tickets;
    }

    
    private static void bookFlight() {
        System.out.print("Enter Flight Number: ");
        String flightNumber = scanner.nextLine();

        Flight selectedFlight = null;
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                selectedFlight = flight;
                break;
            }
        }

        if (selectedFlight == null) {
            System.out.println("Flight not found.");
            return;
        }

        // Display available seats
        ArrayList<String> availableSeats = selectedFlight.getAvailableSeatNumbers();
        if (availableSeats.isEmpty()) {
            System.out.println("No available seats for this flight.");
            return;
        }

        System.out.println("Available Seats: " + availableSeats);
        System.out.print("Choose a seat: ");
        String chosenSeat = scanner.nextLine();

        // Try to book the seat
        if (selectedFlight.bookSeat(chosenSeat)) {
            // Generate ticket and confirm booking
            String ticketNumber = "T" + (tickets.size() + 1);
            tickets.add(new Ticket(ticketNumber, "PassengerName", selectedFlight, chosenSeat, selectedFlight.getPrice(), "Confirmed"));
            System.out.println("Booking successful! Your ticket number: " + ticketNumber);
        } else {
            System.out.println("Seat not available. Please try again.");
        }
    }


}


