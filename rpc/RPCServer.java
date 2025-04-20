package rpc;

import java.io.*;
import java.net.*;

public class RPCServer {
    public static void main(String[] args) {
        try (ServerSocket ss = new ServerSocket(6666)) {
            System.out.println("Listening");
            while (true) {
                Socket s = ss.accept();
                serverStub(s);
                s.close();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void serverStub(Socket s) throws Exception {
        ObjectOutputStream out = new ObjectOutputStream(s.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(s.getInputStream());

        Message RPCRequest = (Message) in.readObject();

        String method = RPCRequest.getMethod();
        int arg1 = RPCRequest.getArg1();
        int arg2 = RPCRequest.getArg2();
        int result;

        switch (method) {
            case "add":
                result = add(arg1, arg2);
                break;
            case "sub":
                result = subtract(arg1, arg2);
                break;
            case "mult":
                result = multiply(arg1, arg2);
                break;
            case "div":
                result = divide(arg1, arg2);
                break;
            default:
                throw new IllegalArgumentException("Unknown method: " + method);
        }

        RPCRequest.setResult(result);
        out.writeObject(RPCRequest);
        out.flush();
    }

    static int add(int a, int b) {
        System.out.println("Received add request from client: " + a + " + " + b);
        return a + b;
    }

    static int subtract(int a, int b) {
        System.out.println("Received subtract request from client: " + a + " - " + b);
        return a - b;
    }

    static int multiply(int a, int b) {
        System.out.println("Received multiply request from client: " + a + " * " + b);
        return a * b;
    }

    static int divide(int a, int b) {
        System.out.println("Received divide request from client: " + a + " / " + b);
        return a / b;
    }
}