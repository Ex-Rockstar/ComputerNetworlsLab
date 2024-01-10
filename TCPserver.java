import java.io.*;
import java.net.*;

class TCPServer {
    public static void main(String argv[]) throws Exception {
        String fromClient;
        String toClient;

        ServerSocket server = new ServerSocket(5000);
        System.out.println("TCP Server waiting for client on port 5000");

        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        

        while (true) {
            Socket connected = server.accept();
            System.out.println("The client " + connected.getInetAddress() + ":" + connected.getPort() + " is connected");

            BufferedReader inFromClient = new BufferedReader(new InputStreamReader(connected.getInputStream()));
            PrintWriter outToClient = new PrintWriter(connected.getOutputStream(), true);

            while (true) {
                System.out.println("Send (Type 'Q' or 'q' to Quit):");
                toClient = inFromUser.readLine();

                if (toClient.equalsIgnoreCase("q")) {
                    outToClient.println(toClient);
                    connected.close(); 
                    break;
                } else {
                    outToClient.println(toClient);
                }

                fromClient = inFromClient.readLine();

                if (fromClient.equalsIgnoreCase("q")) {
                    connected.close();
                    break;
                } else {
                    System.out.println("Received: " + fromClient);
                }
            }
        }
    }
}
