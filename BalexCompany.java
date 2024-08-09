package balexpayroll;

import java.text.NumberFormat;  // Import for currency formatting
import java.util.Locale;        // Import to specify currency locale
import java.util.Scanner;

public class BalexCompany {

    // Constants for business rules
    private static final double MINIMUM_WAGE = 8.00;  // Minimum wage requirement
    private static final int REGULAR_HOURS = 40;      // Regular work hours before overtime
    private static final int MAX_HOURS = 60;          // Maximum allowable work hours in a week

    /**
     * Method to calculate and print the total pay for an employee.
     * @param basePay The hourly base pay of the employee.
     * @param hoursWorked The total hours worked by the employee in a week.
     */
    public static void calculatePay(double basePay, int hoursWorked) {
        // Check if the base pay is below the minimum wage
        if (basePay < MINIMUM_WAGE) {
            System.out.println("Error! Base pay cannot be less than $" + MINIMUM_WAGE + " per hour.");
            return;  // Exit the method as further processing is not needed
        }

        // Check if the hours worked exceed the maximum allowed hours
        if (hoursWorked > MAX_HOURS) {
            System.out.println("Error! Employees cannot work more than " + MAX_HOURS + " hours in a week.");
            return;  // Exit the method as further processing is not needed
        }

        double totalPay;  // Variable to store the calculated total pay

        // Calculate pay based on regular and overtime hours
        if (hoursWorked <= REGULAR_HOURS) {
            totalPay = basePay * hoursWorked;  // No overtime, simple calculation
        } else {
            int overtimeHours = hoursWorked - REGULAR_HOURS;  // Calculate overtime hours
            totalPay = (basePay * REGULAR_HOURS) + (overtimeHours * basePay * 1.5);  // Calculate regular and overtime pay
        }

        // Format the total pay as currency (using the US locale)
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        String formattedPay = currencyFormat.format(totalPay);

        // Output the formatted total pay
        System.out.printf("Your total salary is %s%n", formattedPay);
    }

    public static void main(String[] args) {
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);

        // Number of employees to process (could be adjusted as needed)
        int numberOfEmployees = 3;

        // Loop to process each employee's pay calculation
        for (int i = 1; i <= numberOfEmployees; i++) {
            System.out.println("For Employee Number: " + i);  // Prompt for employee number

            // Prompt and read base pay from user input
            System.out.print("Enter Base Pay: ");
            double basePay = scanner.nextDouble();

            // Prompt and read hours worked from user input
            System.out.print("Enter Hours Worked: ");
            int hoursWorked = scanner.nextInt();

            // Call the method to calculate and display pay
            calculatePay(basePay, hoursWorked);

            // Print an empty line for better readability between employees
            System.out.println();
        }

        // Close the scanner to avoid resource leaks
        scanner.close();
    }
}
