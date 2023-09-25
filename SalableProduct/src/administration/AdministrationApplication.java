package administration; 

import java.util.Scanner;
import storeFront.StoreFront;

public class AdministrationApplication {

    public static void main(String[] args) {
        // Create a StoreFront instance
        StoreFront storeFront = new StoreFront();

        // Initialize and run the Administration Application with the StoreFront instance
        AdministrationService administrationService = new AdministrationService(storeFront);
        administrationService.startService();

        // Create a Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Implement user interactions to send commands to the Administration Service
        while (true) {
            System.out.println("Enter a command (U for update, R for retrieve, or Q to quit): ");
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("Q")) {
                break; // Exit the program
            }

            if (command.equalsIgnoreCase("U")) {
                System.out.println("Enter the JSON payload: ");
                String payload = scanner.nextLine();
                administrationService.processCommand(command, payload);
            } else if (command.equalsIgnoreCase("R")) {
                // Retrieve and display all Salable Products in JSON format
                administrationService.retrieveAndDisplayProducts();
            } else {
                System.out.println("Invalid command. Please enter 'U' for update, 'R' for retrieve, or 'Q' to quit.");
            }
        }

        administrationService.stopService();

        // Close the scanner
        scanner.close();
    }
}
