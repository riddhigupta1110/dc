package rmi;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class CalculatorClient {
	public static void main(String[] args) {
    	try {
        	// Step 5: Look up the remote object from the RMI registry
        	Calculator calc = (Calculator) Naming.lookup("//localhost/CalculatorService");
       	 
        	// Step 2: Marshal the input data (client prepares inputs)
        	Scanner scanner = new Scanner(System.in);
        	System.out.print("Enter first number: ");
        	int a = scanner.nextInt();
        	System.out.print("Enter second number: ");
        	int b = scanner.nextInt();
       	 
        	// Step 6: Call the remote method on the serve
        	int result = calc.add(a, b);  // Call remote method

        	System.out.println("The sum of " + a + " and " + b + " is: " + result);  // Show the result
       	 
    	} catch (Exception e) {
        	System.out.println("Client failed: " + e);
    	}
	}
}

