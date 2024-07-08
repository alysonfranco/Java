import java.util.*;
import java.io.*;

public class al531370_Final_Project {

	// menu method
	public static int menu() {

		int option = 0;
		String selection;
		Scanner myScan = new Scanner(System.in);

		while (true) {
			System.out.println("Choose one of the options: \n");
			System.out.println("1- Enter faculty member information");
			System.out.println("2- Enter staff member information");
			System.out.println("3- Enter student information");
			System.out.println("4- Print faculty information");
			System.out.println("5- Print staff member information");
			System.out.println("6- Print student tuition invoice");
			System.out.println("7- Delete a person");
			System.out.println("0- Exit Program");

			System.out.print("\n\tEnter your selection: ");
			selection = myScan.nextLine().trim();
			System.out.print("\n");

			try {
				option = Integer.parseInt(selection);
				if (option >= 0 && option <= 7) {
					break;
				} else {
					System.out.println("Invalid input... Please enter a valid option...");
				}
			} catch (Exception e) {
				System.out.println("Invalid input... Please enter a valid option...");
			}

		}

		return option;
	}

	// capitalize method to clean up code
	public static String capitalizeInput(String userInput) {
		if (userInput == null || userInput.isEmpty()) {
			return userInput;
		}

		return userInput.substring(0, 1).toUpperCase() + userInput.substring(1).toLowerCase();
	}

	public static void main(String[] args) {
		University university = new University();

		int option = -1;
		String userInput = null;
		boolean personFound;

		do {
			option = menu();
			Scanner myScan = new Scanner(System.in);

			switch (option) {
			case 1: // adding faculty to database
				Faculty faculty = null;
				boolean isCorrect = false;
				System.out.println("Enter faculty information:");

				do {
					System.out.print("\tName of the Faculty Member: ");
					userInput = myScan.nextLine().toLowerCase().trim();

					// checking for valid id and looping until valid input
					if (Person.validFullName(userInput)) {
						String[] name = userInput.split(" ");
						String fullName = capitalizeInput(name[0]).trim() + " " + capitalizeInput(name[1]).trim();

						String id = null;
						do {
							System.out.print("\tID: ");
							userInput = myScan.nextLine().toLowerCase().trim();

							if (Person.validID(userInput)) {
								if (!university.searchById(userInput.toLowerCase())) {
									id = userInput.toLowerCase();
								} else {
									System.out.println("Person already exists! Please enter a unique ID...");
								}
							} else {
								System.out.println(
										"\nInvalid Entry... Please enter valid id in the format LetterLetterDigitDigitDigitDigit...");
							}
						} while (id == null || !Person.validID(id));

						// checking for valid department and looping until valid input
						String department = null;
						do {
							System.out.print("\tDepartment: ");
							userInput = myScan.nextLine().toLowerCase().trim();

							if (Employee.validDepartment(userInput)) {
								department = capitalizeInput(userInput);
							} else {
								System.out.println(
										"\nInvalid Entry... Please enter a valid department (mathematics, engineering, or english)...");
							}
						} while (department == null || !Employee.validDepartment(department));

						// checking for valid rank and looping until valid input
						String rank = null;
						do {
							System.out.print("\tRank: ");
							userInput = myScan.nextLine().toLowerCase().trim();
							if (Faculty.validRank(userInput)) {
								rank = capitalizeInput(userInput);

							} else {
								System.out.println("\n'" + userInput
										+ "' is invalid...Please enter a valid rank (professor or adjunct)...");
							}
						} while (rank == null || !Faculty.validRank(rank));

						// saving and adding faculty member info
						faculty = new Faculty(fullName, id, department, rank);
						university.addPerson(faculty);
						isCorrect = true;

					} else {
						System.out.println("\nInvalid Entry! Please enter a valid full name...");
					}
				} while (!isCorrect);

				System.out.println("\nUploding informaiton...\n");
				break;
			case 2: // adding staff to database
				Staff staff = null;
				isCorrect = false;
				System.out.println("Enter Staff information:");

				do {
					System.out.print("\tName of the Staff Member: ");
					userInput = myScan.nextLine().toLowerCase().trim();

					// checing for valid id and looping until valid info
					if (Person.validFullName(userInput)) {
						String[] name = userInput.split(" ");
						String fullName = capitalizeInput(name[0]).trim() + " " + capitalizeInput(name[1]).trim();

						String id = null;
						do {
							System.out.print("\tID: ");
							userInput = myScan.nextLine().toLowerCase().trim();

							if (Person.validID(userInput)) {
								if (!university.searchById(userInput.toLowerCase())) {
									id = userInput.toLowerCase();
								} else {
									System.out.println("Person already exists! Please enter a unique ID...");
								}
							} else {
								System.out.println(
										"\nInvalid Entry... Please enter valid id in the format LetterLetterDigitDigitDigitDigit...");
							}
						} while (id == null || !Person.validID(id));

						// checking for valid department and looping until valid info
						String department = null;
						do {
							System.out.print("\tDepartment: ");
							userInput = myScan.nextLine().toLowerCase().trim();

							if (Employee.validDepartment(userInput)) {
								department = capitalizeInput(userInput);
							} else {
								System.out.println(
										"\nInvalid Entry... Please enter a valid department (mathematics, engineering, or english)...");
							}
						} while (department == null || !Employee.validDepartment(department));

						// checking for valid status and looping until valid info
						String status = null;
						do {
							System.out.print("\tStatus, Enter F for full time or P for part time: ");
							userInput = myScan.nextLine().toLowerCase().trim();
							if (Staff.validStatus(userInput)) {
								if (userInput.equalsIgnoreCase("f")) {
									status = "Full Time";
									break;
								}
								if (userInput.equalsIgnoreCase("p")) {
									status = "Part Time";
									break;
								}
							} else {
								System.out.println(
										"\nInvalid Entry...Please enter a valid status (F for full time or P for part time)...");
							}
						} while (status == null || !Staff.validStatus(status));

						// saving and adding staff member info
						staff = new Staff(fullName, id, department, status);
						university.addPerson(staff);
						isCorrect = true;

					} else {
						System.out.println("\nInvalid Entry! Please enter a valid full name...");
					}
				} while (!isCorrect);

				System.out.println("\nUploding informaiton...\n");
				break;
			case 3: // adding student to database
				Student student = null;
				isCorrect = false;
				System.out.println("Enter student information:");

				do {
					System.out.print("\tName of the Student: ");
					userInput = myScan.nextLine().toLowerCase().trim();

					// checking for valid if and looping until valid input
					if (Person.validFullName(userInput)) {
						String[] name = userInput.split(" ");
						String fullName = capitalizeInput(name[0]).trim() + " " + capitalizeInput(name[1]).trim();

						String id = null;
						do {
							System.out.print("\tID: ");
							userInput = myScan.nextLine().toLowerCase().trim();

							if (Person.validID(userInput)) {
								if (!university.searchById(userInput.toLowerCase())) {
									id = (userInput.toLowerCase()).trim();
								} else {
									System.out.println("Person already exists! Please enter a unique ID...");
								}
							} else {
								System.out.println(
										"\nInvalid Entry... Please enter valid id in the format LetterLetterDigitDigitDigitDigit...");
							}

						} while (id == null || !Person.validID(id));

						// checking for valid gpa and looping until valid input
						Double gpa = null;
						do {
							System.out.print("\tGPA: ");
							userInput = myScan.nextLine().toLowerCase().trim();

							if (Student.validGPA(userInput)) {
								gpa = Double.parseDouble(userInput); // make sure it is 4.0 or less
								if (gpa > 4.0) {
									System.out.println(
											"\nInvalid Entry... A GPA cannot be greater than a 4.0. Please enter a valid GPA...");
									gpa = null;
								}
							} else {
								System.out.println(
										"\nInvalid Entry... Please enter a valid GPA (hint: double value no greater than 3 digits, i.e 3.25)...");
							}
						} while (gpa == null || !Student.validGPA(userInput));

						// checking for valid credit hours and looping until valid input
						int creditHours = 0;
						do {
							System.out.print("\tCredit Hours: ");
							userInput = myScan.nextLine().toLowerCase().trim();

							if (Student.validCreditHours(userInput)) {
								creditHours = Integer.parseInt(userInput);

							} else {
								System.out.println(
										"\nInvalid Entry... Please enter a valid number of credit hours (hint: integer value)...");
							}
						} while (!Student.validCreditHours(userInput));

						// saving and adding staff info
						student = new Student(fullName, id, gpa, creditHours);
						university.addPerson(student);
						isCorrect = true;

					} else {
						System.out.println("\nInvalid Entry! Please enter a valid full name...");
					}
				} while (!isCorrect);

				System.out.println("\nUploding informaiton...\n");
				break;

			case 4: // print faculty info
				personFound = false;
				do {
					System.out.print("Enter the faculty members's ID: ");
					userInput = myScan.nextLine().toLowerCase().trim();

					// searching for faculty by id and printing info
					if (Person.validID(userInput)) {
						if (university.searchById(userInput)) {
							personFound = false;
							for (Person p : university.personList) {
								if (p != null && p instanceof Faculty && userInput.equalsIgnoreCase(p.getId())) {
									System.out.println(
											"---------------------------------------------------------------------------");
									((Faculty) p).print();
									System.out.println(
											"---------------------------------------------------------------------------");
									personFound = true;
									break;
								}
							}
							if (!personFound) {
								System.out.println(
										"The ID does not belong to a faculty member! Please enter a faculty member's ID...");
								personFound = false;
							}
						} else {
							System.out.println("No faculty member matched!! Please enter the ID of a faculty member..");
						}
					} else {
						System.out.println(
								"\nInvalid Entry... Please enter valid id in the format LetterLetterDigitDigitDigitDigit...");
					}
				} while (!Person.validID(userInput) || !personFound);

				break;
			case 5: // print staff info
				personFound = false;
				do {
					System.out.print("Enter the staff members's ID: ");
					userInput = myScan.nextLine().toLowerCase().trim();

					// searching for staff by id and printing info
					if (Person.validID(userInput)) {
						if (university.searchById(userInput)) {
							personFound = false;
							for (Person p : university.personList) {
								if (p != null && p instanceof Staff && userInput.equalsIgnoreCase(p.getId())) {
									System.out.println(
											"---------------------------------------------------------------------------");
									((Staff) p).print();
									System.out.println(
											"---------------------------------------------------------------------------");
									personFound = true;
									break;
								}
							}
							if (!personFound) {
								System.out.println(
										"The ID does not belong to a staff member! Please enter a staff member's ID...");
								personFound = false;
							}
						} else {
							System.out.println("No staff member matched!! Please enter the ID of a staff member..");
						}
					} else {
						System.out.println(
								"\nInvalid Entry... Please enter valid id in the format LetterLetterDigitDigitDigitDigit...");
					}
				} while (!Person.validID(userInput) || !personFound);

				break;
			case 6: // print student invoice
				personFound = false;
				do {
					System.out.print("Enter the student's ID: ");
					userInput = myScan.nextLine().toLowerCase().trim();

					// searching for student by id and printing tuition invoice
					if (Person.validID(userInput)) {
						if (university.searchById(userInput)) {
							personFound = false;
							for (Person p : university.personList) {
								if (p != null && p instanceof Student && userInput.equalsIgnoreCase(p.getId())) {
									System.out.println("Here is the tuition invoice for: " + p.getFullName());
									((Student) p).invoice();
									personFound = true;
									break;
								}
							}
							if (!personFound) {
								System.out
										.println("The ID does not belong to a student! Please enter a student's ID...");
								personFound = false;
							}
						} else {
							System.out.println("No student matched!! Please enter the ID of a current student...");
						}
					} else {
						System.out.println(
								"\nInvalid Entry... Please enter valid id in the format LetterLetterDigitDigitDigitDigit...");
					}
				} while (!Person.validID(userInput) || !personFound);

				break;
			case 7: // delete a person
				personFound = false;
				do {
					System.out.print("Enter the ID of the person you want to delete: ");
					userInput = myScan.nextLine().toLowerCase().trim();

					// searching for person by id and removing from personList
					if (Person.validID(userInput)) {
						if (university.searchById(userInput)) {
							personFound = false;
							for (Person p : university.personList) {
								if (p != null && userInput.equalsIgnoreCase(p.getId())) {
									university.deletePersonById(userInput);
									System.out.println("Person successfully deleted!");
									personFound = true;
									break;
								}
							}
						} else {
							System.out.println("No person matched!! Please enter the ID of a person...");
						}
					} else {
						System.out.println(
								"\nInvalid Entry... Please enter valid id in the format LetterLetterDigitDigitDigitDigit...");
					}
				} while (!Person.validID(userInput) || !personFound);

				break;
			case 0: // end program and create a report
				Date date = new Date();
				// asking if user would like to create a report an looping until valid input
				do {
					System.out.print("Would you like to create the report? (y/n): ");
					userInput = myScan.nextLine().toLowerCase().trim();

					if (!userInput.equalsIgnoreCase("y") && !userInput.equalsIgnoreCase("n")) {
						System.out.println("Invalid option! Please choose y for yes or n for no...");
					}

					// if yes create report.txt
					if (userInput.equalsIgnoreCase("y")) {
						PrintWriter writer = null;
						try {
							writer = new PrintWriter("report.txt");
							System.out.println("Creating report...");
							System.out.print("\n");

							System.out.println("Report created on: " + date);
							System.out.println("***********************************************\n");
							writer.println("Report created on: " + date);
							writer.println("***********************************************\n");

							System.out.println("Faculty Members");
							System.out.println("---------------");

							writer.println("Faculty Members");
							writer.println("---------------");

							for (Person p : university.personList) {
								if (p != null && p instanceof Faculty) {
									p.print();
									p.print(writer);
									writer.print("\n");
									System.out.print("\n");
								}
							}

							System.out.println("Staff Members");
							System.out.println("-------------");

							writer.println("Staff Members");
							writer.println("-------------");

							for (Person p : university.personList) {
								if (p != null && p instanceof Staff) {
									p.print();
									p.print(writer);
									writer.print("\n");
									System.out.print("\n");
								}
							}

							System.out.println("Students");
							System.out.println("--------");

							writer.println("Students");
							writer.println("--------");

							for (Person p : university.personList) {
								if (p != null && p instanceof Student) {
									p.print();
									p.print(writer);
									writer.print("\n");
									System.out.print("\n");
								}
							}

						} catch (FileNotFoundException e) {
							System.out.println("Unable to create file...\n");
						}
					} else if (userInput.equalsIgnoreCase("n")) {
						System.out.println("Bye!");
						break;
					}
				} while (!userInput.equals("y") && !userInput.equals("n"));

				// create report.txt and add sorting function for students by gpa o|r name
				// report should print the date create

				System.out.println("Bye!");
				break;
			default:
				System.out.println("Invalid option! Please enter a number between 0 and 7.");

			}
		} while (option != 0);
	}
}

abstract class Person extends University {

	private String fullName;
	private String id;

	// getters and setters
	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// constructors
	public Person() {
		fullName = "no name";
		id = "xx0000";
	}

	public Person(String fullName, String id) {
		this.fullName = fullName;
		this.id = id;
	}

	public static boolean validFullName(String fullName) {
		return (fullName.trim()).matches("^[a-zA-Z]+\\s[a-zA-Z]+$");
	}

	public static boolean validID(String id) {
		return (id.trim()).matches("[A-Za-z]{2}\\d{4}");
	}

	// abstract methods
	public abstract void print();

	public abstract void print(PrintWriter writer);

	public abstract double getGpa();
}

abstract class Employee extends Person {
	private String department;

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Employee() {
		super();
		department = "n/a";
	}

	public Employee(String fullName, String id, String department) {
		super(fullName, id);
		this.department = department;
	}

	public static boolean validDepartment(String department) {
		return ((department.trim()).toLowerCase()).matches("mathematics")
				|| ((department.trim()).toLowerCase()).matches("engineering")
				|| ((department.trim()).toLowerCase()).matches("english");
	}

	// method that checks if input is math, english or engineering
}

interface tuitionInvoice {
	final double perCreditHour = 236.45;
	final int adminFee = 52;

	public double tuitionDiscount();

	public double tuitionTotal();

	public void invoice();
}

class Student extends Person implements tuitionInvoice {

	private double gpa;
	private int creditHours;

	@Override
	public double getGpa() {
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public int getCreditHours() {
		return creditHours;
	}

	public void setCreditHours(int creditHours) {
		this.creditHours = creditHours;
	}

	public Student() {
		super();
		gpa = 0.0;
		creditHours = 0;
	}

	public Student(String fullName, String id, double gpa, int creditHours) {
		super(fullName, id);
		this.gpa = gpa;
		this.creditHours = creditHours;
	}

	public static boolean validGPA(String userInput) {
		String gpaFormat = "^[0-9](\\.[0-9]{1,2})?$";
		return userInput.matches(gpaFormat);
	}

	public static boolean validCreditHours(String userInput) {
		String creditHoursFormat = "^[0-9]{1,2}$";
		return userInput.matches(creditHoursFormat);
	}

	@Override
	public void print() {
		System.out.println(getFullName());
		System.out.println("ID: " + getId());
		System.out.println("GPA: " + gpa);
		System.out.println("Credit Hours: " + creditHours);
	}

	public void print(PrintWriter writer) {
		writer.println(getFullName());
		writer.println("ID: " + getId());
		writer.println("GPA: " + gpa);
		writer.println("Credit Hours: " + creditHours);
	}

	@Override
	public double tuitionDiscount() {
		double tuition = (creditHours * perCreditHour) + adminFee;
		if (gpa >= 3.85) {
			double discount = tuition * 0.25;
			return Math.round(discount * 100.0) / 100.0;
		} else {
			return 0;
		}
	}

	@Override
	public double tuitionTotal() {
		double tuition = (creditHours * perCreditHour) + adminFee;
		if (gpa >= 3.85) {
			double total = tuition - tuitionDiscount();
			return Math.round(total * 100.0) / 100.0;
		} else {
			return Math.round(tuition * 100.0) / 100.0;
		}
	}

	@Override
	public void invoice() {
		System.out.println("---------------------------------------------------------------------------");
		System.out.println(getFullName() + "\t" + getId());
		System.out.println("Credit Hours: " + getCreditHours() + " ($" + perCreditHour + "/credit hour)");
		System.out.println("Fees: $" + adminFee);
		System.out.println("Total Payment (after discount): $" + tuitionTotal() + "\t" + "($" + tuitionDiscount()
				+ " discount applied)");
		System.out.println("---------------------------------------------------------------------------");
	}
}

class Faculty extends Employee {

	private String rank;

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Faculty() {
		super();
		rank = "n/a";
	}

	public Faculty(String fullName, String id, String department, String rank) {
		super(fullName, id, department);
		this.rank = rank;
	}

	public static boolean validRank(String rank) {
		return ((rank.trim()).toLowerCase()).matches("professor") || ((rank.trim()).toLowerCase()).matches("adjunct");
	}

	@Override
	public void print() {
		System.out.println(getFullName());
		System.out.println("ID: " + getId());
		System.out.println(getDepartment() + " Department, " + rank);
	}

	public void print(PrintWriter writer) {
		writer.println(getFullName());
		writer.println("ID: " + getId());
		writer.println(getDepartment() + " Department, " + rank);
	}

	@Override
	public double getGpa() {
		// TODO Auto-generated method stub
		return 0;
	}
}

class Staff extends Employee {
	private String status;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Staff() {
		super();
		status = "n/a";
	}

	public Staff(String fullName, String id, String department, String status) {
		super(fullName, id, department);
		this.status = status;
	}

	public static boolean validStatus(String status) {
		return ((status.trim()).toLowerCase()).matches("f") || ((status.trim()).toLowerCase()).matches("p");
	}

	@Override
	public void print() {
		System.out.println(getFullName());
		System.out.println("ID: " + getId());
		System.out.println(getDepartment() + " Department, " + status);
	}

	public void print(PrintWriter writer) {
		writer.println(getFullName());
		writer.println("ID: " + getId());
		writer.println(getDepartment() + " Department, " + status);
	}

	@Override
	public double getGpa() {
		// TODO Auto-generated method stub
		return 0;
	}
}

class University implements Comparable<Person> {
	// array list for dynamic person storage
	ArrayList<Person> personList = new ArrayList<>(Collections.nCopies(100, null));

	// search by ID method (also checks for unique ids
	public boolean searchById(String id) {
		for (Person p : personList) {
			if (p != null) {
				if (id.equalsIgnoreCase(p.getId())) {
					return true;
				}
			}

		}
		return false;
	}

	// delete by ID
	public void deletePersonById(String id) {
		Iterator<Person> deletedPeople = personList.iterator();
		while (deletedPeople.hasNext()) {
			Person p = deletedPeople.next();
			if (p != null && id.equalsIgnoreCase(p.getId())) {
				deletedPeople.remove();
			}
		}
	}

	// add person
	public void addPerson(Person p) {
		personList.add(p);
	}

	@Override
	public int compareTo(Person p) {
		return p.getFullName().compareTo(p.getFullName());
	}

}

//use comparator to sort by name or GPA also unused

// sort by name in descending order
class SortByName implements Comparator<Person> {
	public int compare(Person s1, Person s2) {
		return s2.getFullName().compareTo(s1.getFullName());
	}
}

// sort by GPA in descending order
class SortByGPA implements Comparator<Student> {
	public int compare(Student s1, Student s2) {
		if (s1.getGpa() < s2.getGpa()) {
			return 1;
		}
		if (s1.getGpa() > s2.getGpa()) {
			return -1;
		}
		return 0;
	}
}