package dsm;

import java.io.*;
import java.net.*;

class SharedMemoryServer {
    private static int var = 50;

    private static synchronized int getVar(PrintStream cout) {
        cout.println("Accessed Shared Variable: " + var);
        return var;
    }

    private static synchronized int setVar(int value, PrintStream cout) {
        var = value;
        cout.println("Updated Shared Variable: " + var);
        return var;
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("SharedMemoryServer started on port 5000...");

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client Connected: " + socket.getInetAddress().getHostAddress());

                new Thread(new ClientHandler(socket)).start();
            }
        } catch (IOException e) {
            System.err.println("Server Error: " + e.getMessage());
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket socket;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try (
                    BufferedReader cin = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    PrintStream cout = new PrintStream(socket.getOutputStream())) {
                String str;
                while ((str = cin.readLine()) != null) {
                    if (str.equalsIgnoreCase("get")) {
                        getVar(cout);
                    } else if (str.equalsIgnoreCase("set")) {
                        try {
                            int value = Integer.parseInt(cin.readLine());
                            setVar(value, cout);
                        } catch (NumberFormatException e) {
                            cout.println("Error: Invalid number format.");
                        }
                    } else if (str.equalsIgnoreCase("exit")) {
                        cout.println("Disconnecting...");
                        break;
                    } else {
                        cout.println("Invalid Command");
                    }
                }
            } catch (IOException e) {
                System.err.println("Client Disconnected: " + socket.getInetAddress().getHostAddress());
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    System.err.println("Error closing socket: " + e.getMessage());
                }
            }
        }
    }
}