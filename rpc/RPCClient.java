package rpc;

import java.io.*;
import java.net.*;

public class RPCClient { 
    public static void main(String[] args) { 
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
        try { 
            System.out.print("Enter first number: "); 
            int num1 = Integer.parseInt(reader.readLine()); 
            System.out.print("Enter second number: "); 
            int num2 = Integer.parseInt(reader.readLine()); 
 
 
            int addResult = add(num1, num2); 
            System.out.println("Addition Result: " + addResult); 
 
            int subResult = sub(num1, num2); 
            System.out.println("Subtraction Result: " + subResult); 
 
            int multResult = mult(num1, num2); 
            System.out.println("Multiplication Result: " + multResult); 
 
            int divResult = divide(num1, num2); 
            System.out.println("Division Result: " + divResult); 
        } catch (Exception e) { 
            System.out.println(e); 
        } 
    } 
 
    static int clientStub(int arg1, int arg2, String method)throws Exception{ 
        Socket s = new Socket("localhost", 6666); 
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream()); 
        ObjectInputStream in = new ObjectInputStream(s.getInputStream()); 

        Message RPCRequest = new Message(arg1, arg2, method); 
 
        out.writeObject(RPCRequest); 
 
        out.flush(); 
 
        Message RPCResponse = (Message) in.readObject(); 
        in.close(); 
        out.close(); 
        s.close(); 
 
        return RPCResponse.getResult(); 
    } 
 
    static int add(int arg1, int arg2) throws Exception{  
        int result = clientStub(arg1, arg2, "add"); 
        return result; 
    } 
 
    static int sub(int arg1, int arg2) throws Exception {  
        int result = clientStub(arg1, arg2, "sub"); 
        return result;
    } 
    
    static int mult(int arg1, int arg2) throws Exception {  
        int result = clientStub(arg1, arg2, "mult");   
        return result; 
    } 
    static int divide(int arg1, int arg2) throws Exception {  
        int result = clientStub(arg1, arg2, "div");   
        return result; 
    } 
}
