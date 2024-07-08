import java.util.*;

public class HW3 {

	public static void main(String[] args) {

		// setting up bookList with two books
		BookList b = new BookList();
		b.addBook(new BookstoreBook("alyson franco", "franco's flora", "161651", 14.99, 15));
		b.addBook(new LibraryBook("alyson franco", "franco's folio", "161651"));

		int option = -1; // for menu

		System.out.println("Welcome to the book program!");
		do {
			// display menu
			option = BookList.menu();
			Scanner myScan = new Scanner(System.in);

			switch (option) {
			case 1:
				// initialize booleans used in do while loops and if statements
				boolean exit = false;
				boolean addMore = false;
				boolean isCorrect = false;

				do { // prompt user to enter book into until they input correctly
					System.out.print("Please enter the author, title, and isbn of the library book separated by /: ");
					String userInput = myScan.nextLine();
					String[] bookInfo = userInput.split("/");

					
					if (bookInfo.length == 3) { // check if input has enough data and creates library book object, otherwise it loops until correct input
						String author = bookInfo[0].trim();
						String title = bookInfo[1].trim();
						String isbn = bookInfo[2].trim();

						b.addBook(new LibraryBook(author, title, isbn));
						System.out.println("\nUpdating our database...\n");
						isCorrect = true;
					} else {
						System.out.println("\nInvalid Entry! Please enter three entries separtaed by /...");
						isCorrect = false;
					}

					// if the input is correct ask prompt to ask user if they'd like to add another book
					if (isCorrect) {
						do {
							System.out.print("Would you like to add another library book?(y/n) ");
							userInput = myScan.nextLine();
							if (userInput.equalsIgnoreCase("n")) {
								addMore = false;
								break;
							} else if (userInput.equalsIgnoreCase("y")) {
								addMore = true;
								break;
							} else {
								System.out.println("\nInvalid Entry! Please enter y for yes or n for no...");
								exit = false;
							}

						} while (!exit);
					}

				} while (addMore || !isCorrect);

				System.out.println("Here is a list of current books in our database:");
				b.printAllBooks();
				break;
			case 2:
				// initialize booleans used in do while loops and if statements
				isCorrect = false;
				exit = true;
				addMore = true;

				do { // prompt user to enter book into until they input correctly
					System.out.print("Please enter the author, title, and isbn of the bookstore book separated by /: ");
					String userInput = myScan.next();
					String[] bookInfo = userInput.split("/");

					if (bookInfo.length == 3) { // check if input has enough data and creates library book object, otherwise it loops until correct input
						String author = bookInfo[0].trim();
						String title = bookInfo[1].trim();
						String isbn = bookInfo[2].trim();

						// loop until user enters valid price
						double price = 0;
						do {
							System.out.print("Please enter the list price of the book: $");

							if (myScan.hasNextDouble()) {
								price = myScan.nextDouble();
								System.out.print("\n");
							} else {
								System.out.println("\nInvalid entry! Please enter a valid price...");
								myScan.next(); 
							}
						} while (price == 0); 

						do { // checks if book is on sale and allows user to enter discount and loops until input is valid
							System.out.print("Is this book on sale? (y/n) ");
							userInput = myScan.next();

							if (userInput.equalsIgnoreCase("n")) { // checks yes and no
								b.addBook(new BookstoreBook(author, title, isbn, price)); // adds book with no discount
								exit = true;
							} else if (userInput.equalsIgnoreCase("y")) {
								double discount = 0;

								do { // checks discount
									System.out.print("Discount percentage: ");
									userInput = myScan.next();

									userInput = userInput.replace("%", "");

									if (userInput.matches("\\d+")) {
										discount = Double.parseDouble(userInput);
										exit = true;
									} else {
										System.out.println("\nInvalid Entry! Please enter an integer percentage value..");
										exit = false;
									}
								} while (!exit);

								b.addBook(new BookstoreBook(author, title, isbn, price, discount)); // adds book with discount
							} else {
								System.out.println("\nInvalid Entry! Please enter y for yes or n for no...");
								exit = false;
							}
						} while (!exit);

						System.out.println("\nUpdating our database...\n");
						isCorrect = true;
					} else {
						System.out.println("\nInvalid Entry! Please enter three entries separated by /...");
						isCorrect = false;
					}

					if (isCorrect) { // asks if user would like to add another book
						System.out.print("Would you like to add another bookstore book? (y/n) ");
						userInput = myScan.next();

						if (userInput.equalsIgnoreCase("n")) {
							addMore = false;
						} else if (userInput.equalsIgnoreCase("y")) {
							addMore = true;
						} else {
							System.out.println("\nInvalid Entry! Please enter y for yes or n for no...");
							exit = false;
						}
					}
				} while (addMore || !isCorrect);

				System.out.println("\nHere is a list of current books in our database:");
				b.printAllBooks();

				break;

			case 3:
				System.out.println("Here is a list of all books in our database: ");
				b.printAllBooks();
				break;
			case 4:
				System.out.println("Here is a list of all Library Books in out database: ");
				System.out.println("----------------------------------------------------");
				b.printLibraryBooks();
				break;
			case 5:
				System.out.println("Here is a list of all Bookstrore Books in out database: ");
				System.out.println("-------------------------------------------------------");
				b.printBookstoreBooks();
				break;
			case 0:
				System.out.println("Bye!");
			}

		} while (option != 0);

	}
}

abstract class Book {
	
	//private fields
	private String author, title, isbn;

	// constructors
	public Book() {
		author = "unknown author";
		title = "untitled";
		isbn = "N/A";
	}

	public Book(String isbn) {
		this.isbn = isbn;
		author = "unknown author";
		title = "untitled";
	}

	public Book(String author, String title) {
		this.author = author;
		this.title = title;
		isbn = "N/A";
	}

	public Book(String author, String title, String isbn) {
		this.author = author;
		this.title = title;
		this.isbn = isbn;
	}

	// getters and setters
	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	// abstract method
	abstract public String bookInfo();

	// overridden toString
	@Override
	public String toString() {
		return "[ " + isbn + " " + title.toUpperCase() + " by " + author.toUpperCase() + " ]";
	}
}

class BookstoreBook extends Book {
	
	// private fields and methods
	private double price, discount;

	private double salePrice(double discount) {
		return price - (price * discount);
	}

	// constructors
	public BookstoreBook() {
		super();
		price = 0.0;
		discount = 0.0;
	}

	public BookstoreBook(String author, String title, String isbn, double price) {
		super(author, title, isbn);
		this.price = price;
	}

	public BookstoreBook(String author, String title, String isbn, double price, double discount) {
		super(author, title, isbn);
		this.price = price;
		this.discount = discount / 100;
		discount = salePrice(discount);
	}

	// getters and setters
	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	// overridden abstract method and toString method
	@Override  
	public String bookInfo() { // doesnt display discount price without a discount
		if (discount != 0) {
			return " - $" + price + " listed for $" + salePrice(discount) + " ]";
		} else {
			return " - $" + price + " ]";
		}
	}

	@Override
	public String toString() {
		return "[ " + getIsbn() + " - " + getTitle().toUpperCase() + " by " + getAuthor().toUpperCase() + bookInfo();
	}
}

class LibraryBook extends Book {

	// private fields 
	private String callNumber;
	
	// random number generator
	private void randomCallNumber() {
		// random number between 1 and 99 inclusive
		Random random = new Random();
		int floorNumber = random.nextInt(99) + 1; 
		
		// get first three letters of authors name 
		String authorName;
		if (getAuthor().length() >= 3) {
			authorName = getAuthor().substring(0,3).toUpperCase();
		} else {
			authorName = getAuthor().toUpperCase();
		}
		
		char isbnChar = getIsbn().charAt(getIsbn().length()-1);// get the last character of isbn
		
		callNumber = String.format("%02d.%s.%c", floorNumber, authorName, isbnChar);
	}
	
	// getters and setters
	public String getCallNumber() {
		return callNumber;
	}

	public void setCallNumber(String callNumber) {
		this.callNumber = callNumber;
	}

	// constructors
	public LibraryBook() {
		super();
		randomCallNumber();
	}
	
	public LibraryBook(String author, String title, String isbn) {
		super(author, title, isbn);
		randomCallNumber();
	}

	public LibraryBook(String author, String title, String isbn, String callNumber) {
		super(author, title, isbn);
		randomCallNumber();
	}
	
	// overridden abstract method and toString method
	@Override 
	public String bookInfo() {
		return " - located at: " + callNumber + " ]";
	}

	@Override
	public String toString() {
		return "[ " + getIsbn() + " - " + getTitle().toUpperCase() + " by " + getAuthor().toUpperCase() + bookInfo();
	}
}

class BookList {
	// final private variables and methods
	private final int TOTAL_BOOKS = 100;
	private static Book[] bookList;

	private int numberOfLibraryBooks() {
		int numberOfLibraryBooks = 0;
		for (Book b : bookList) {
			if (b instanceof LibraryBook) {
				numberOfLibraryBooks++;
			}
		}
		return numberOfLibraryBooks;
	}

	private int numberOfBookstoreBooks() {
		int numberOfBookstoreBooks = 0;
		for (Book b : bookList) {
			if (b instanceof BookstoreBook) {
				numberOfBookstoreBooks++;
			}
		}
		return numberOfBookstoreBooks;
	}
	
	// constructor
	public BookList() {
		bookList = new Book[TOTAL_BOOKS];
		for (int i = 0; i < TOTAL_BOOKS; i++) {
			bookList[i] = null;
		}
	}

	// add book method 
	public void addBook(Book b) {
		boolean full = true;
		for (int i = 0; i < TOTAL_BOOKS; i++) { // checks if array is full before adding
			if (bookList[i] == null) {
				bookList[i] = b;
				full = false;
				break;
			}
		}
		if (full) {
			System.out.println("Sorry, there are too many books in the booklist.");
		}
	}

	// print all books and display number of books of each instance
	public void printLibraryBooks() {

		System.out.println("Library Books (" + numberOfLibraryBooks() + "):\n");
		for (Book b : bookList) {
			if (b instanceof LibraryBook) {
				System.out.println(b);
			}
		}
	}

	public void printBookstoreBooks() {

		System.out.println("Bookstore Books (" + numberOfBookstoreBooks() + "):\n");
		for (Book b : bookList) {
			if (b instanceof BookstoreBook) {
				System.out.println(b);
			}
		}
	}
	
	public void printAllBooks() {
		System.out.println("---------------------------------------------");
		printLibraryBooks();
		System.out.print("\n");
		printBookstoreBooks();
	}

	
	// method used to display menu IU
	public static int menu() {
		int option = 0;
		String selection;
		Scanner myScan = new Scanner(System.in);

		do {
			System.out.println("\nWhat would you like to do today?\n");
			System.out.println("1 - Add library book");
			System.out.println("2 - Add bookstore book");
			System.out.println("3 - View all books");
			System.out.println("4 - View library books");
			System.out.println("5 - View bookstore books");
			System.out.println("0 - Exit Program");

			selection = myScan.nextLine();

			option = Integer.parseInt(selection);

		} while (option > 5 || option < 0);

		return option;
	}

}
