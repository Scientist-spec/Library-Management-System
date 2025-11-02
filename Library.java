import java.util.*;

public class Library {
    private Map<String, Book> books;
    private Map<String, Member> members;
    private String libraryName;

    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.books = new HashMap<>();
        this.members = new HashMap<>();
    }

    // Book management methods
    public void addBook(Book book) {
        if (book != null && !books.containsKey(book.getIsbn())) {
            books.put(book.getIsbn(), book);
            System.out.println("Book added: " + book.getTitle());
        }
    }

    public Book findBookByISBN(String isbn) {
        return books.get(isbn);
    }

    public List<Book> findBooksByTitle(String title) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                result.add(book);
            }
        }
        return result;
    }

    public boolean removeBook(String isbn) {
        Book book = books.get(isbn);
        if (book != null && book.isAvailable()) {
            books.remove(isbn);
            System.out.println("Book removed: " + book.getTitle());
            return true;
        }
        System.out.println("Cannot remove book. It may not exist or is currently borrowed.");
        return false;
    }

    // Member management methods
    public void addMember(Member member) {
        if (member != null && !members.containsKey(member.getMemberId())) {
            members.put(member.getMemberId(), member);
            System.out.println("Member added: " + member.getName());
        }
    }

    public Member findMemberById(String memberId) {
        return members.get(memberId);
    }

    public boolean removeMember(String memberId) {
        Member member = members.get(memberId);
        if (member != null && member.getBorrowedBooksCount() == 0 && member.getOutstandingFines() == 0) {
            members.remove(memberId);
            System.out.println("Member removed: " + member.getName());
            return true;
        }
        System.out.println("Cannot remove member. They may have borrowed books or outstanding fines.");
        return false;
    }

    // Borrow/Return operations
    public boolean borrowBook(String isbn, String memberId, int loanDays) {
        Book book = books.get(isbn);
        Member member = members.get(memberId);
        
        if (book == null) {
            System.out.println("Book not found with ISBN: " + isbn);
            return false;
        }
        if (member == null) {
            System.out.println("Member not found with ID: " + memberId);
            return false;
        }
        if (!book.isAvailable()) {
            System.out.println("Book '" + book.getTitle() + "' is already borrowed.");
            return false;
        }
        if (!member.canBorrowMoreBooks()) {
            System.out.println("Member " + member.getName() + " cannot borrow more books.");
            return false;
        }
        
        book.borrowBook(memberId, loanDays);
        member.borrowBook(isbn);
        return true;
    }

    public boolean returnBook(String isbn, String memberId) {
        Book book = books.get(isbn);
        Member member = members.get(memberId);
        
        if (book == null || member == null) {
            System.out.println("Book or member not found.");
            return false;
        }
        if (book.isAvailable()) {
            System.out.println("Book is not currently borrowed.");
            return false;
        }
        if (!member.getBorrowedBookISBNs().contains(isbn)) {
            System.out.println("This book was not borrowed by this member.");
            return false;
        }
        
        // Check for overdue and apply fines if any
        if (book.isOverdue()) {
            int daysOverdue = book.getDaysOverdue();
            double fine = daysOverdue * 0.50; // $0.50 per day
            System.out.println("Book is overdue by " + daysOverdue + " days. Fine: $" + fine);
            member.addFine(fine);
        }
        
        book.returnBook();
        member.returnBook(isbn);
        return true;
    }

    // Reporting methods
    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.isAvailable()) {
                availableBooks.add(book);
            }
        }
        return availableBooks;
    }

    public List<Book> getBorrowedBooks() {
        List<Book> borrowedBooks = new ArrayList<>();
        for (Book book : books.values()) {
            if (!book.isAvailable()) {
                borrowedBooks.add(book);
            }
        }
        return borrowedBooks;
    }

    public List<Book> getOverdueBooks() {
        List<Book> overdueBooks = new ArrayList<>();
        for (Book book : books.values()) {
            if (book.isOverdue()) {
                overdueBooks.add(book);
            }
        }
        return overdueBooks;
    }

    public void displayAllBooks() {
        System.out.println("\n=== ALL BOOKS ===");
        if (books.isEmpty()) {
            System.out.println("No books in library.");
        } else {
            for (Book book : books.values()) {
                System.out.println(book);
            }
        }
    }

    public void displayAllMembers() {
        System.out.println("\n=== ALL MEMBERS ===");
        if (members.isEmpty()) {
            System.out.println("No members registered.");
        } else {
            for (Member member : members.values()) {
                System.out.println(member);
            }
        }
    }

    public void displayLibraryStatus() {
        System.out.println("\n=== " + libraryName + " LIBRARY STATUS ===");
        System.out.println("Total Books: " + books.size());
        System.out.println("Available Books: " + getAvailableBooks().size());
        System.out.println("Borrowed Books: " + getBorrowedBooks().size());
        System.out.println("Overdue Books: " + getOverdueBooks().size());
        System.out.println("Total Members: " + members.size());
    }

    // Getters
    public Map<String, Book> getBooks() { return new HashMap<>(books); }
    public Map<String, Member> getMembers() { return new HashMap<>(members); }
    public String getLibraryName() { return libraryName; }
}
