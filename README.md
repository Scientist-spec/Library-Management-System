Library Management System
A comprehensive Java-based Library Management System that demonstrates object-oriented programming principles with proper data encapsulation, validation, and business logic.

Table of Contents
Features

Class Structure

Installation

Usage

Class Documentation

Examples

Business Rules

Features
📚 Book Management
Add, remove, and search books

Track book availability and borrowing status

Automatic due date calculation

Overdue book detection and fine calculation

Search by ISBN, title, or author

👥 Member Management
Member registration and account management

Borrowing limits enforcement

Fine management for overdue books

Membership status tracking

Contact information management

🔄 Library Operations
Book borrowing and returning

Automatic fine calculation

Real-time status reporting

Search and filtering capabilities

Comprehensive reporting

Class Structure
The system consists of four main classes:

Book - Represents individual books in the library

Member - Represents library members

Library - Manages the collection of books and members

LibraryManagementSystem - Main class with demo application

Installation
Prerequisites
Java JDK 8 or higher

Any Java IDE (Eclipse, IntelliJ, VS Code) or command line

Steps
Clone or download the source files

Ensure all four Java files are in the same directory:

Book.java

Member.java

Library.java

LibraryManagementSystem.java

Compile the code:

bash
javac *.java
Run the application:

bash
java LibraryManagementSystem
Usage
Basic Operations
Creating a Library
java
Library library = new Library("My Library");
Adding Books
java
library.addBook(new Book("978-0134685991", "Effective Java", "Joshua Bloch", "Programming", 2018));
Adding Members
java
library.addMember(new Member("M001", "John Doe", "john@email.com", "123-456-7890", "123 Main St"));
Borrowing Books
java
library.borrowBook("978-0134685991", "M001", 14); // 14-day loan period
Returning Books
java
library.returnBook("978-0134685991", "M001");
Search Operations
Search by Title
java
List<Book> results = library.findBooksByTitle("Java");
Search by Author
java
List<Book> results = library.findBooksByAuthor("Bloch");
Search by ISBN
java
Book book = library.findBookByISBN("978-0134685991");
Reporting
java
// Display all books
library.displayAllBooks();

// Display all members
library.displayAllMembers();

// Show library status
library.displayLibraryStatus();

// Get specific lists
List<Book> availableBooks = library.getAvailableBooks();
List<Book> borrowedBooks = library.getBorrowedBooks();
List<Book> overdueBooks = library.getOverdueBooks();
Class Documentation
Book Class
Constructor
java
Book(String isbn, String title, String author, String genre, int publicationYear)
Key Methods
borrowBook(String memberId, int loanDays) - Marks book as borrowed

returnBook() - Marks book as returned

isOverdue() - Checks if book is overdue

getDaysOverdue() - Calculates days overdue

Properties
ISBN, title, author, genre, publication year

Availability status

Borrower information

Due date

Member Class
Constructor
java
Member(String memberId, String name, String email, String phoneNumber, String address)
Key Methods
borrowBook(String isbn) - Adds book to member's borrowed list

returnBook(String isbn) - Removes book from borrowed list

addFine(double amount) - Adds fine to member's account

payFine(double amount) - Reduces outstanding fines

canBorrowMoreBooks() - Checks if member can borrow more books

Properties
Member ID, name, contact information

Membership status and date

Borrowed books list

Outstanding fines

Borrowing limits

Library Class
Constructor
java
Library(String libraryName)
Key Methods
addBook(Book book) - Adds book to library

addMember(Member member) - Registers new member

borrowBook(String isbn, String memberId, int loanDays) - Processes book borrowing

returnBook(String isbn, String memberId) - Processes book return

Various search and reporting methods

Examples
Complete Workflow Example
java
// Create library
Library library = new Library("City Library");

// Add books
library.addBook(new Book("123-4567890", "Java Programming", "John Smith", "Programming", 2023));

// Add members
library.addMember(new Member("M001", "Alice Brown", "alice@email.com", "555-1234", "123 Street"));

// Borrow book
library.borrowBook("123-4567890", "M001", 14);

// Display status
library.displayLibraryStatus();

// Return book
library.returnBook("123-4567890", "M001");
Error Handling Examples
java
// Try to borrow unavailable book
library.borrowBook("nonexistent-isbn", "M001", 14); // Fails with error message

// Try to borrow when member has fines
Member member = library.findMemberById("M001");
member.addFine(10.00);
library.borrowBook("123-4567890", "M001", 14); // Fails due to fines
Business Rules
Borrowing Limits
Default maximum books per member: 5

Members cannot borrow if they have outstanding fines

Members cannot borrow if their membership is inactive

Fine Calculation
Overdue fine rate: $0.50 per day

Fines are calculated automatically when books are returned

Members must pay fines before borrowing more books

Validation Rules
Book titles, authors, and genres cannot be empty

Email addresses must contain '@' symbol

Publication year must be valid (positive and not in future)

ISBN must be unique for books

Member ID must be unique for members

Membership Rules
New members are automatically active

Members with borrowed books cannot be removed

Members with outstanding fines cannot be removed

File Structure
text
library-management-system/
├── Book.java                 # Book entity class
├── Member.java              # Member entity class
├── Library.java             # Library management class
└── LibraryManagementSystem.java  # Main demo class
Contributing
Feel free to extend this system with additional features such as:

Database persistence

GUI interface

Advanced reporting

Email notifications

Reservation system

Multiple copy support

License
This project is open source and available under the MIT License.

