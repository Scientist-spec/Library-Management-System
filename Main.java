public class Main {
    public static void main(String[] args) {
        System.out.println("=== LIBRARY MANAGEMENT SYSTEM ===");
        
        // Create library
        Library library = new Library("City Central Library");
        
        // Add books to library
        System.out.println("\n--- Adding Books ---");
        library.addBook(new Book("978-0134685991", "Effective Java", "Joshua Bloch", "Programming", 2018));
        library.addBook(new Book("978-0201633610", "Design Patterns", "Erich Gamma", "Computer Science", 1994));
        library.addBook(new Book("978-1593275846", "Eloquent JavaScript", "Marijn Haverbeke", "Programming", 2014));
        library.addBook(new Book("978-0262033848", "Introduction to Algorithms", "Thomas Cormen", "Computer Science", 2009));
        library.addBook(new Book("978-1491950357", "Building Microservices", "Sam Newman", "Software Architecture", 2015));
        
        // Add members to library
        System.out.println("\n--- Adding Members ---");
        library.addMember(new Member("M001", "Alice Johnson", "alice@email.com", "123-456-7890", "123 Main St"));
        library.addMember(new Member("M002", "Bob Smith", "bob@email.com", "123-456-7891", "456 Oak Ave"));
        library.addMember(new Member("M003", "Carol Davis", "carol@email.com", "123-456-7892", "789 Pine Rd"));
        
        // Display initial status
        library.displayLibraryStatus();
        library.displayAllBooks();
        library.displayAllMembers();
        
        // Demonstrate borrowing books
        System.out.println("\n--- Borrowing Books ---");
        library.borrowBook("978-0134685991", "M001", 14); // Alice borrows Effective Java
        library.borrowBook("978-0201633610", "M001", 14); // Alice borrows Design Patterns
        library.borrowBook("978-1593275846", "M002", 21); // Bob borrows Eloquent JavaScript
        library.borrowBook("978-0262033848", "M003", 7);  // Carol borrows Algorithms book
        
        // Try to borrow when member has reached limit
        library.borrowBook("978-1491950357", "M001", 14); // Alice tries to borrow 3rd book
        
        // Display status after borrowing
        library.displayLibraryStatus();
        
        // Display borrowed books
        System.out.println("\n--- Currently Borrowed Books ---");
        for (Book book : library.getBorrowedBooks()) {
            Member member = library.findMemberById(book.getBorrowedByMemberId());
            System.out.println(book.getTitle() + " borrowed by " + member.getName() + " (Due: " + book.getDueDate() + ")");
        }
        
        // Demonstrate returning books
        System.out.println("\n--- Returning Books ---");
        library.returnBook("978-0134685991", "M001"); // Alice returns Effective Java
        library.returnBook("978-1593275846", "M002"); // Bob returns JavaScript book
        
        // Demonstrate book search
        System.out.println("\n--- Searching Books ---");
        System.out.println("Searching for books with 'Java' in title:");
        for (Book book : library.findBooksByTitle("Java")) {
            System.out.println("Found: " + book.getTitle());
        }
        
        System.out.println("\nSearching for books by author 'Erich Gamma':");
        for (Book book : library.findBooksByAuthor("Erich Gamma")) {
            System.out.println("Found: " + book.getTitle());
        }
        
        // Demonstrate member management
        System.out.println("\n--- Member Management ---");
        Member alice = library.findMemberById("M001");
        if (alice != null) {
            alice.addFine(5.00); // Add a fine for demonstration
            alice.payFine(2.50); // Partial payment
        }
        
        // Display final status
        System.out.println("\n--- Final Library Status ---");
        library.displayLibraryStatus();
        library.displayAllBooks();
        library.displayAllMembers();
        
        // Demonstrate removing books and members
        System.out.println("\n--- Cleanup Operations ---");
        library.removeBook("978-0134685991"); // Remove returned book
        library.removeMember("M003"); // Try to remove member with borrowed book (should fail)
        
        // Return Carol's book first
        library.returnBook("978-0262033848", "M003");
        library.removeMember("M003"); // Now should succeed
        
        System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM DEMO COMPLETE ===");
    }
}
