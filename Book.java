import java.time.LocalDate;

public class Book {
    private String isbn;
    private String title;
    private String author;
    private String genre;
    private int publicationYear;
    private boolean isAvailable;
    private String borrowedByMemberId;
    private LocalDate dueDate;

    public Book(String isbn, String title, String author, String genre, int publicationYear) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publicationYear = publicationYear;
        this.isAvailable = true;
        this.borrowedByMemberId = null;
        this.dueDate = null;
    }

    // Getters
    public String getIsbn() { return isbn; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getGenre() { return genre; }
    public int getPublicationYear() { return publicationYear; }
    public boolean isAvailable() { return isAvailable; }
    public String getBorrowedByMemberId() { return borrowedByMemberId; }
    public LocalDate getDueDate() { return dueDate; }

    // Setters with validation
    public void setTitle(String title) {
        if (title != null && !title.trim().isEmpty()) {
            this.title = title;
        }
    }

    public void setAuthor(String author) {
        if (author != null && !author.trim().isEmpty()) {
            this.author = author;
        }
    }

    public void setGenre(String genre) {
        if (genre != null && !genre.trim().isEmpty()) {
            this.genre = genre;
        }
    }

    public void setPublicationYear(int publicationYear) {
        if (publicationYear > 0 && publicationYear <= java.time.Year.now().getValue()) {
            this.publicationYear = publicationYear;
        }
    }

    // Business methods
    public void borrowBook(String memberId, int loanDays) {
        if (isAvailable) {
            this.isAvailable = false;
            this.borrowedByMemberId = memberId;
            this.dueDate = LocalDate.now().plusDays(loanDays);
            System.out.println("Book '" + title + "' borrowed by member " + memberId + ". Due date: " + dueDate);
        }
    }

    public void returnBook() {
        this.isAvailable = true;
        this.borrowedByMemberId = null;
        this.dueDate = null;
    }

    public boolean isOverdue() {
        if (dueDate == null || isAvailable) {
            return false;
        }
        return LocalDate.now().isAfter(dueDate);
    }

    public int getDaysOverdue() {
        if (!isOverdue()) {
            return 0;
        }
        return (int) java.time.temporal.ChronoUnit.DAYS.between(dueDate, LocalDate.now());
    }

    @Override
    public String toString() {
        String status = isAvailable ? "Available" : "Borrowed by " + borrowedByMemberId + " (Due: " + dueDate + ")";
        return String.format("ISBN: %s | Title: %-25s | Author: %-20s | Genre: %-15s | Status: %s",
                isbn, title, author, genre, status);
    }
}
