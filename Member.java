import java.util.ArrayList;
import java.util.List;

public class Member {
    private String memberId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private java.time.LocalDate membershipDate;
    private boolean isActive;
    private int maxBooksAllowed;
    private List<String> borrowedBookISBNs;
    private double outstandingFines;

    public Member(String memberId, String name, String email, String phoneNumber, String address) {
        this.memberId = memberId;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.membershipDate = java.time.LocalDate.now();
        this.isActive = true;
        this.maxBooksAllowed = 5;
        this.borrowedBookISBNs = new ArrayList<>();
        this.outstandingFines = 0.0;
    }

    // Getters
    public String getMemberId() { return memberId; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getAddress() { return address; }
    public java.time.LocalDate getMembershipDate() { return membershipDate; }
    public boolean isActive() { return isActive; }
    public int getMaxBooksAllowed() { return maxBooksAllowed; }
    public List<String> getBorrowedBookISBNs() { return new ArrayList<>(borrowedBookISBNs); }
    public double getOutstandingFines() { return outstandingFines; }

    // Setters with validation
    public void setName(String name) {
        if (name != null && !name.trim().isEmpty()) {
            this.name = name;
        }
    }

    public void setEmail(String email) {
        if (email != null && email.contains("@")) {
            this.email = email;
        }
    }

    public void setPhoneNumber(String phoneNumber) {
        if (phoneNumber != null && !phoneNumber.trim().isEmpty()) {
            this.phoneNumber = phoneNumber;
        }
    }

    public void setAddress(String address) {
        if (address != null && !address.trim().isEmpty()) {
            this.address = address;
        }
    }

    public void setMaxBooksAllowed(int maxBooksAllowed) {
        if (maxBooksAllowed > 0) {
            this.maxBooksAllowed = maxBooksAllowed;
        }
    }

    // Business methods
    public boolean canBorrowMoreBooks() {
        return isActive && borrowedBookISBNs.size() < maxBooksAllowed && outstandingFines == 0;
    }

    public boolean borrowBook(String isbn) {
        if (canBorrowMoreBooks()) {
            borrowedBookISBNs.add(isbn);
            return true;
        }
        return false;
    }

    public boolean returnBook(String isbn) {
        boolean removed = borrowedBookISBNs.remove(isbn);
        if (removed) {
            System.out.println("Book " + isbn + " returned by member " + name);
        }
        return removed;
    }

    public void addFine(double amount) {
        if (amount > 0) {
            this.outstandingFines += amount;
            System.out.println("Fine of $" + amount + " added to member " + name + ". Total fines: $" + outstandingFines);
        }
    }

    public void payFine(double amount) {
        if (amount > 0) {
            this.outstandingFines = Math.max(0, this.outstandingFines - amount);
            System.out.println("Payment of $" + amount + " received from member " + name + ". Remaining fines: $" + outstandingFines);
        }
    }

    public void activateMembership() {
        this.isActive = true;
        System.out.println("Membership activated for " + name);
    }

    public void deactivateMembership() {
        this.isActive = false;
        System.out.println("Membership deactivated for " + name);
    }

    public int getBorrowedBooksCount() {
        return borrowedBookISBNs.size();
    }

    @Override
    public String toString() {
        String status = isActive ? "Active" : "Inactive";
        return String.format("ID: %s | Name: %-15s | Email: %-20s | Status: %-8s | Books: %d/%d | Fines: $%.2f",
                memberId, name, email, status, borrowedBookISBNs.size(), maxBooksAllowed, outstandingFines);
    }
}
