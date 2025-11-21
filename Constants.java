// File Name: Constants.java

// This class contains all constants used in the EmployeeManager program

public class Constants {
    public static final String EMPLOYEE_FILE = "employees.txt";   // File path to store employee names
    public static final String ERROR_ARGUMENTS = "Error: Exactly one argument required."; // Error message for invalid argument count
    public static final String USAGE_MESSAGE = 
            "Usage:\n" +
            "  l          - Load employees\n" +
            "  s          - Show random employee\n" +
            "  +Name      - Add employee\n" +
            "  ?Name      - Search employee\n" +
            "  uName      - Update employee\n" +
            "  dName      - Delete employee\n" +
            "  c          - Count words";
}
