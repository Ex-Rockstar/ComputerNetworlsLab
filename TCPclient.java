import java.io.*;
import java.net.*;

class TCPClient {
    public static void main(String argv[]) throws Exception {
        String fromServer;
        String toServer;

        Socket clientSocket = new Socket("localhost", 5000);
        BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter outToServer = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        while (true) {
            fromServer = inFromServer.readLine();

            if (fromServer.equalsIgnoreCase("q")) {
                clientSocket.close();
                break;
            } else {
                System.out.println("Received: " + fromServer);
                System.out.println("Send (Type 'Q' or 'q' to Quit):");
                toServer = inFromUser.readLine();

                if (toServer.equalsIgnoreCase("q")) {
                    outToServer.println(toServer);
                    clientSocket.close();
                    break;
                } else {
                    outToServer.println(toServer);
                }
            }
        }
    }
}
