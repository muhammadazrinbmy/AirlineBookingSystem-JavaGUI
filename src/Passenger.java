public class Passenger extends User {
    private boolean isActive; // To track if the account is active or deactivated

    public Passenger(String userId, String name, String email, String password) {
        super(userId, name, email, password);
        this.isActive = true; // By default, accounts are active
    }

    public String getName() {
        return name;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean status) {
        this.isActive = status;
    }
    
    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    public String getUserId() {
        return userId;  // ✅ Ensure userId exists in Passenger class
    }






public void viewTickets() {
    System.out.println("Your Tickets:");
    for (Ticket ticket : MainTesting.getTickets()) {
    	if (ticket.getPassenger().equals(this.getUserId())) {
            System.out.println("Ticket: " + ticket.getTicketNumber() + 
                               " | Flight: " + ticket.getFlight().getFlightNumber() +
                               " | Seat: " + ticket.getSeatNumber());
        }
    }
}

}


