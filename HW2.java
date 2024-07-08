import java.util.ArrayList;

public class HW2 {

	public static void main(String[] args) {
		String fullName = "Erika T. Jones";
		String employeeNumber = "ej789";
		double payRate = 100.0, hoursWorked = 1.0;
		// TA will change the payrate and the hours worked to test your code

		Employee1 e;
		e = new Employee1(fullName, employeeNumber, payRate, hoursWorked);
		System.out.println(e); // To Test your toString method
		e.printCheck(); // This prints the check of Erika T. Jones
		Company company = new Company();

		company.hire(new Employee1("Saeed Happy", "sh895", 2, 200)); 
		company.hire(e);
		company.printCompanyInfo();
		company.hire(new Employee1("Enrico Torres", "et897", 3, 150));
		// You may add as many employees to company as you want.
		// The TAs will add their own employees
		// Make sure that each employee of company has a unique employeeNumber

		company.printCheck("ab784");
		company.deleteEmployeesBySalary(256.36);
		company.reverseEmployees();

		System.out.println(company.searchByName("WaLiD WiLLiAms"));
		company.printEmployees();
		System.out.println("Bye!");
	}

}

class Company {

	private ArrayList<Employee1> employeeList;
	private String companyName;
	private static String companyTaxId;

	public Company() {
		employeeList = new ArrayList<>();
		companyName = "People's Place";
		companyTaxId = "v1rtua7C0mpan1";
	}

	// company name getter
	public String getCompanyName() {
		return companyName;
	}

	// company name getter
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	// company tax id getter
	public static String getCompanyTaxId() {
		return companyTaxId;
	}

	// company tax id setter
	public static void setCompanyTaxId(String companyTaxId) {
		Company.companyTaxId = companyTaxId;
	}

	public boolean hire(Employee1 e) {
		// add a for loop and if statement somewhere
		for (int i = 0; i < employeeList.size(); i++) {
			if (employeeList.get(i).getEmployeeNumber().compareToIgnoreCase(e.getEmployeeNumber()) == 0) {
				System.out.println("Employee already exists");
				return false;
			}
		}
		employeeList.add(e);
		return true;
	}

	public void printCompanyInfo() {
		// This method prints the company name, the tax id and the current number of
		// employees
		System.out.println("Company name: " + companyName + "\n" + "TaxID: " + companyTaxId + "\n" + "Currently Employeed: " + employeeList.size() + "\n");
	}

	public void printEmployees() {
		// This methods prints all employees (One employee per line)
		// Note that you already have toString in Employee
		for (Employee1 e : employeeList) {
			System.out.println(e);
		}
	}

	public int countEmployees(double maxSalary) {
		// This method returns the number of employees paid less than maxSalary
		int count = 0;
		for (Employee1 e: employeeList) {
			if (e.getGrossPay() < maxSalary) {
				count++;
			}
		}
		return count; 
	}

	public boolean searchByName(String fullName) {
		// This method returns true if fullName exists as an employee.
		// It returns false otherwise
		// this is a not a case sensitive search.
		for (int i = 0; i < employeeList.size(); i++) {
			if (fullName.compareToIgnoreCase(employeeList.get(i).getFullName()) == 0) {
				return true;
			}
		}
		return false;
	}

	public void reverseEmployees() {
		// This method reverses the order in which the employees were added to the list.
		// The last employee is swapped with the first employee, the
		// second last with the second and so on..
		for (int i = 0, j = employeeList.size()-1; i < j; i++) {
			employeeList.add(i, employeeList.remove(j));
		}
	}

	public void deleteEmployeesBySalary(double targetSalary) {
		// This method deletes all employees who are paid targetSalary as a gross
		//salary
		for (int i = 0; i < employeeList.size(); i++) {
			if (employeeList.get(i).getGrossPay() == targetSalary) {
				employeeList.remove(i);
			}
		}
	}

	public void printCheck(String employeeNumber) {
		// This method prints the check of the employee whose employee number is
		// employeeNumber.
		// It prints NO SUCH EMPLOYEE EXISTS if employeeNumber is not a registered
		// employee number.

		for (Employee1 e: employeeList) {
			if(e.getEmployeeNumber() == employeeNumber) {
				e.printCheck();
				break;
			} else { 
				System.out.println("NO SUCH EMPLOYEE EXSISTS"); 
				break;
			}
		}

	}

}// end of class company

class Employee1 {
	private String fullName, employeeNumber;
	private double payRate, hoursWorked;
	private double grossPay;
	
	public Employee1(String newFullName, String newEmployeeNumber, double newPayRate, double newHoursWorked) {
		fullName = newFullName;
		employeeNumber = newEmployeeNumber;
		payRate = newPayRate;
		hoursWorked = newHoursWorked;
		grossPay = payRate * hoursWorked;
	}

	public double getGrossPay() {
		return grossPay;
	}

	public void setGrossPay(double grossPay) {
		this.grossPay = payRate * hoursWorked;
	}

	private double netPay() {
		return grossPay * 0.94;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmployeeNumber() {
		return employeeNumber;
	}

	public void setEmployeeNumber(String employeeNumber) {
		this.employeeNumber = employeeNumber;
	}

	public double getPayRate() {
		return payRate;
	}

	public void setPayRate(double payRate) {
		this.payRate = payRate;
	}

	public double getHoursWorked() {
		return hoursWorked;
	}

	public void setHoursWorked(double hoursWorked) {
		this.hoursWorked = hoursWorked;
	}

	public void printCheck() {
		System.out.println("---------------------------------------------------------------------------");
		System.out.println("Employee's name:\t" + fullName);
		System.out.println("Employee's number:\t" + employeeNumber);
		System.out.println("Hourly rate of pay:\t$" + payRate);
		System.out.println("Hours worked:\t\t" + hoursWorked);
		System.out.print("\n");
		System.out.println("Total Gross Pay:\t$" + grossPay);
		System.out.print("\n");
		System.out.println("Deductions");
		System.out.println("Tax(6%):\t\t$" + getGrossPay() * 0.06);
		System.out.print("\n");
		System.out.println("Net Pay:\t\t$" + netPay());
		System.out.println("---------------------------------------------------------------------------");
	}

	@Override
	public String toString() {
		return "[" + employeeNumber + "/" + fullName + ", " + hoursWorked + " @ " + payRate + " per hour]\n";
	}

}
