package rmi;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {
    
	public CalculatorImpl() throws RemoteException {
    	super(); // Step 2: Implement the remote method
	}

	public int add(int a, int b) throws RemoteException {
    	return a + b;  // Add two numbers and return the result
	}

	public static void main(String[] args) {
		try {
			java.rmi.registry.LocateRegistry.createRegistry(1099);
	
			CalculatorImpl obj = new CalculatorImpl();
			java.rmi.Naming.rebind("CalculatorService", obj);
	
			System.out.println("Calculator Server is ready.");
		} catch (Exception e) {
			System.out.println("Calculator Server failed: " + e);
		}
	}
	
}

