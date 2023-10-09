import java.util.Scanner;

public class al531370_HW1 {

	public static void main(String[] args) {

		// constants
		final double perCreditHour = 120.25;
		final double healthFees = 35.00;

		// student information variables
		String ID = null;
		String name;

		// data collection for the CRN and credit hours
		String data = null;
		String[] courseInfo;
		int crn1, creditHours1, crn2, creditHours2;

		// total calculations
		double courseTotal1, courseTotal2;
		double totalPayment = 0;

		Scanner myScan = new Scanner(System.in);
		
		// id input ================================================

		// checking to make sure Id is the proper format
		boolean isCorrect = false;
		do {
			System.out.print("Enter the Student's ID (V####): ");
			ID = myScan.nextLine();

			// if first character of the string is lower or upper V...
			if ((ID.charAt(0) == 'v' || ID.charAt(0) == 'V') && ID.length() == 5) {
				for (int i = 1; i < 5; i++) { // loop through last four digits to make sure they are integers
					if (!(ID.charAt(i) >= 48 && ID.charAt(i) <= 57)) { // ASCII for 0-9: 48 - 57 will not work for special characters
						System.out.println("INVALID ENTRY!! Please Enter Proper Format...");
						isCorrect = false;
						break;
					} else {
						isCorrect = true;
					}
				}
			} else {
				System.out.println("INVALID ENTRY!! Please Enter Proper Format...");
			}
		} while (isCorrect == false);
		
		// name input ==============================================

		// checking to make sure name does not contain an integer
		isCorrect = false;
		do {
			System.out.print("Enter the Student's Full Name: ");
			name = myScan.nextLine();

			// loops through string to make sure it contains no integers
			for (int i = 0; i < name.length(); i++) {
				if (name.charAt(i) >= 48 && name.charAt(i) <= 57) { // ASCII for 0-9: 48 - 57 will not work for special characters
					System.out.println("INVALID ENTRY!! Please Enter valid name...");
					isCorrect = false;
					break;
				} else {
					isCorrect = true;
				}
			}
		} while (isCorrect == false);

		System.out.println(" ");
		
		// first course info input ==============================================
		
		/* if the do while loop below does not work, comment it out and use this code segment, however, it should work as intended
			System.out.print("Enter the Student's CRN/CREDIT HOURS for the First Class (####/#): ");
			data = myScan.nextLine();
		*/

		// checking to make sure input only contains integers
		isCorrect = false;
		do {
			System.out.print("Enter the Student's CRN/CREDIT HOURS for the First Class (####/#): ");
			data = myScan.nextLine();

			// first checks if the input is the correct length
			if (data.length() >= 6) { 
				for (int i = 0; i < data.length(); i++) { // loops through input to make sure it contains only integers
					if (i != 4) { // does not check the 5th character as it was already checked in the first if statement
						if (!(data.charAt(i) >= 48 && data.charAt(i) <= 57)) { // ASCII for 0-9: 48 - 57 will not work for special characters
							System.out.println("INVALID ENTRY!! Please Enter valid CRN/CREDIT HOURS...");
							isCorrect = false;
							break;
						} else {
							isCorrect = true;
						}
					} else if (data.charAt(4) != 47) { // if 5th character is not a /
						System.out.println("INVALID ENTRY!! Please Enter valid CRN/CREDIT HOURS...");
						break;
					}
				}
			} else {
				System.out.println("INVALID ENTRY!! Please Enter valid CRN/CREDIT HOURS...");
			}
		} while (isCorrect == false);

		// split method and variable assignment
		courseInfo = data.split("/");
		crn1 = Integer.parseInt(courseInfo[0]);
		creditHours1 = Integer.parseInt(courseInfo[1]);
		
		// course total calculations
		courseTotal1 = creditHours1 * perCreditHour;
		
		// second course info input ==============================================
		
		/* if the do while loop below does not work, comment it out and use this code segment, however, it should work as intended
			System.out.print("Enter the Student's CRN/CREDIT HOURS for the Second Class (####/#): ");
			data = myScan.nextLine();
		*/

		// checking to make sure input only contains integers
		isCorrect = false;
		do {
			System.out.print("Enter the Student's CRN/CREDIT HOURS for the Second Class (####/#): ");
			data = myScan.nextLine();

			// first checks if the input is the correct length
			if (data.length() >= 6) {
				for (int i = 0; i < data.length(); i++) { // loops through input to make sure it contains only integers
					if (i != 4) { // does not check the 5th character as it was already checked in the first if statement
						if (!(data.charAt(i) >= 48 && data.charAt(i) <= 57)) { // ASCII for 0-9: 48 - 57 will not work for special characters
							System.out.println("INVALID ENTRY!! Please Enter valid CRN/CREDIT HOURS...");
							isCorrect = false;
							break;
						} else {
							isCorrect = true;
						}
					} else if (data.charAt(4) != 47) { // if 5th character is not a /
						System.out.println("INVALID ENTRY!! Please Enter valid CRN/CREDIT HOURS...");
						break;
					}
				}
			} else {
				System.out.println("INVALID ENTRY!! Please Enter valid CRN/CREDIT HOURS...");
			}
		} while (isCorrect == false);
		
		// split method and variable assignment
		courseInfo = data.split("/");
		crn2 = Integer.parseInt(courseInfo[0]);
		creditHours2 = Integer.parseInt(courseInfo[1]);
		
		// course total calculations
		courseTotal2 = creditHours2 * perCreditHour;

		// total payment calculations
		totalPayment = courseTotal1 + courseTotal2 + healthFees;

		//invoice formating ==============================================
		System.out.println(" ");

		System.out.println("Thank you!");
		System.out.format("Fee Invoice prepared for: " + name.toUpperCase() + "\n");
		System.out.println(" ");

		System.out.println("SIMPLE COLLEGE");
		System.out.println("ORLANDO, FL 10101");
		System.out.println("*************************\n");

		System.out.println("FEE INVOICE PREPARED FOR:");
		System.out.println("[" + name.toUpperCase() + "][" + ID + "]\n");

		System.out.println("1 Credit Hour = $" + perCreditHour + "\n");

		System.out.println("CRN\tCREDIT HOURS");
		System.out.println(crn1 + "\t" + creditHours1 + "\t\t\t" + courseTotal1);
		System.out.println(crn2 + "\t" + creditHours2 + "\t\t\t" + courseTotal2);

		System.out.println(" ");

		System.out.format("\tHEALTH & ID FEES\t" + "$%.2f\n\n", healthFees);

		System.out.println("----------------------------------------");

		System.out.format("\tTOTAL PAYMENTS\t\t$%.2f\n", totalPayment);

	}

}
