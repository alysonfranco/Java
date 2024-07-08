import java.io.*;
import java.util.*;

public class HW5 {

	// method to reduce redundant code when Scanning file
	private static Scanner newScanner(File file) {
		Scanner newScanner = null;
		try {
			newScanner = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("File not found...\n");
		}
		return newScanner;
	}

	public static void main(String[] args) {

		// checking to make sure the file was located
		File file = new File("lec.txt");
		Scanner myScan = null;
		myScan = newScanner(file);

		// basic variables for storing course info for loops and conditional statements
		String courses;
		String courseInfo[] = null;

		// counting the number of online lectures
		int onlineLec = 0;
		while (myScan.hasNextLine()) {
			courses = myScan.nextLine();
			courseInfo = courses.split(",");

			if (courseInfo.length > 2) {
				if (courseInfo[4].compareToIgnoreCase("online") == 0) {
					onlineLec++;
				}
			}
		}
		System.out.println("There are " + onlineLec + " online lectures offered");
		// end of counting online lectures

		// searching for a specific class
		int crns = 0;
		List<String> crnsList = new ArrayList<>();
		Scanner input = new Scanner(System.in);

		System.out.print("Enter the classroom: ");
		String userInput = input.nextLine();

		myScan = newScanner(file);

		while (myScan.hasNextLine()) {
			courses = myScan.nextLine();
			courseInfo = courses.split(",");
			if (courseInfo.length >= 2 && courseInfo[1].equalsIgnoreCase(userInput)
					|| (courseInfo.length > 5 && courseInfo[5].equalsIgnoreCase(userInput))) {
				crnsList.add(courseInfo[0]);
				crns++;
			}
		}

		if (crnsList.isEmpty()) {
			System.out.println("There are no courses held in " + userInput);
		} else {
			String crnsString = String.join(", ", crnsList);
			System.out.println("The courses held in " + userInput + " are: " + crnsString);
		}
		// end of class search

		// creating lecturesOnly file
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("lecturesOnly.txt");
			System.out.println("\nlecturesOnly.txt was created...\n");
		} catch (FileNotFoundException e) {
			System.out.println("Unable to create file...\n");
		}
		// end of file creation

		// adding data to lecturesOnly.txt
		myScan = newScanner(file);

		// print and add online lectures to file
		System.out.println("Online Lectures:");
		writer.println("Online Lectures:");

		while (myScan.hasNextLine()) {
			courses = myScan.nextLine();
			courseInfo = courses.split(",");
			if (courses.toLowerCase().contains("online")) {
				System.out.println(courses);
				writer.println(courses);
			}
		}

		System.out.print("\n");
		writer.print("\n");

		myScan = newScanner(file);

		// print and add lectures with no lab to file
		System.out.println("Lectures with No Lab:");
		writer.println("Lectures with No Lab:");
		while (myScan.hasNextLine()) {
			courses = myScan.nextLine();
			courseInfo = courses.split(",");
			if (courseInfo.length == 7) {
				if (courseInfo[6].toLowerCase().contains("no")) {
					System.out.println(courses);
					writer.println(courses);
				}
			}
		}

		writer.close();
		// end of adding data to lecturesOnly.txt
	}

}
