// File Name: EmployeeManager.java
// This program manages employee names stored in a text file.
// It allows loading, adding, searching, updating, deleting, counting, and showing random employees.

import java.io.*;
import java.util.*;

public class EmployeeManager {

    // Method: readEmployeeFile
    // Reads all employees from the file and returns an array of employee names
    public static String[] readEmployeeFile() throws Exception {
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(Constants.EMPLOYEE_FILE)))) {
            return reader.readLine().split(",");
        }
    }

    // Method: writeEmployeeFile
    // Writes the given employee data string to the file
    public static void writeEmployeeFile(String employeeData) throws Exception {
        try (BufferedWriter writer = new BufferedWriter(
                new FileWriter(Constants.EMPLOYEE_FILE))) {
            writer.write(employeeData);
        }
    }

    // MAIN PROGRAM
    public static void main(String[] args) {

        // Validate argument count
        if (args.length != 1) {
            System.out.println(Constants.ERROR_ARGUMENTS);
            System.out.println(Constants.USAGE_MESSAGE);
            return;
        }

        String userInput = args[0];  // User input argument

        // LOAD EMPLOYEES (l)
        if (userInput.equals("l")) {
            System.out.println("Loading data ...");
            try {
                for (String employee : readEmployeeFile()) {
                    System.out.println(employee.trim());
                }
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (userInput.equals("s")) {           // SHOW RANDOM EMPLOYEE (s)
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeeFile();
                System.out.println("Random Employee: "
                        + employees[new Random().nextInt(employees.length)].trim());
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (userInput.startsWith("+")) {        // ADD EMPLOYEE (+Name)
            System.out.println("Loading data ...");
            try {
                String newEmployee = userInput.substring(1);
                writeEmployeeFile(String.join(",", readEmployeeFile()) + ", " + newEmployee);
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (userInput.startsWith("?")) {             // SEARCH EMPLOYEE (?Name)
            System.out.println("Loading data ...");
            try {
                String searchName = userInput.substring(1);
                boolean found = Arrays.stream(readEmployeeFile())
                        .map(String::trim)
                        .anyMatch(emp -> emp.equals(searchName));

                System.out.println(found
                        ? "Employee \"" + searchName + "\" found!"
                        : "Employee \"" + searchName + "\" NOT found.");
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (userInput.equals("c")) {              // COUNT EMPLOYEES (c)
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeeFile();
                int employeeCount = employees.length;
                int characterCount = String.join(",", employees).length();
                System.out.println(employeeCount + " employee(s) | " + characterCount + " characters");
            } catch (Exception e) {
            }
            System.out.println("Data Loaded.");
        } else if (userInput.startsWith("u")) {                // UPDATE EMPLOYEE (uName)
            System.out.println("Loading data ...");
            try {
                String[] employees = readEmployeeFile();
                String employeeToUpdate = userInput.substring(1);

                for (int i = 0; i < employees.length; i++) {
                    if (employees[i].trim().equals(employeeToUpdate)) {
                        employees[i] = "Updated";
                    }
                }

                writeEmployeeFile(String.join(",", employees));
            } catch (Exception e) {
            }
            System.out.println("Data Updated.");
        } // DELETE EMPLOYEE (dName)
        else if (userInput.startsWith("d")) {              // DELETE EMPLOYEE (dName)
            System.out.println("Loading data ...");
            try {
                List<String> employeeList = new ArrayList<>(Arrays.asList(readEmployeeFile()));
                employeeList.remove(userInput.substring(1));
                writeEmployeeFile(String.join(",", employeeList));
            } catch (Exception e) {
            }
            System.out.println("Data Deleted.");
        } // INVALID ARGUMENT HANDLING
        else {
            System.out.println("Invalid argument: \"" + userInput + "\"");
            System.out.println(Constants.USAGE_MESSAGE);
        }
    }
}
