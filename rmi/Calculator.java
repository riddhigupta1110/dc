package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
	// Step 1: Define the remote procedure to add two numbers
	int add(int a, int b) throws RemoteException;  // Remote procedure to add two integers
}
