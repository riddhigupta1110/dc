package dsm;

import java.io.*;
import java.net.*;
import java.util.*;

public class SharedMemoryClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
                BufferedReader cin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintStream cout = new PrintStream(socket.getOutputStream());
                Scanner sc = new Scanner(System.in)) {

            System.out.println("Connected to SharedMemoryServer. Type 'exit' to quit.");

            while (true) {
                System.out.print("Enter Command (get/set/exit): ");
                String command = sc.nextLine().trim();
                cout.println(command);

                if (command.equalsIgnoreCase("get") || command.equalsIgnoreCase("exit")) {
                    System.out.println(cin.readLine());
                    if (command.equalsIgnoreCase("exit"))
                        break;
                } else if (command.equalsIgnoreCase("set")) {
                    System.out.print("Enter Value: ");
                    while (!sc.hasNextInt()) {
                        System.out.println("Invalid input. Enter a valid integer:");
                        sc.next();
                    }
                    int value = sc.nextInt();
                    sc.nextLine(); // Consume newline
                    cout.println(value);
                    System.out.println(cin.readLine());
                } else {
                    System.out.println(cin.readLine());
                }
            }
        } catch (IOException e) {
            System.err.println("Client Error: " + e.getMessage());
        }
    }
}