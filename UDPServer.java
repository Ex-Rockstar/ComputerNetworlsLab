import java.io.*;
import java.net.*;

class UDPServer {
    public static DatagramSocket serverSocket;
    public static DatagramPacket dp;
    public static BufferedReader dis;
    public static InetAddress ia;
    public static byte[] buf = new byte[1024];
    public static int cport = 789, sport = 790;

    public static void main(String[] a) throws IOException {
        serverSocket = new DatagramSocket(sport);
        dp = new DatagramPacket(buf, buf.length);
        dis = new BufferedReader(new InputStreamReader(System.in));
        ia = InetAddress.getLocalHost();

        System.out.println("Server is Running...");

        while (true) {
            serverSocket.receive(dp);
            String str = new String(dp.getData(), 0, dp.getLength());

            if (str.equals("STOP")) {
                System.out.println("Terminated...");
                break;
            }

            System.out.println("Client: " + str);

            // Create a new BufferedReader for reading input from the client
            BufferedReader clientInput = new BufferedReader(new InputStreamReader(new ByteArrayInputStream(dp.getData())));

            String str1 = clientInput.readLine();
            buf = str1.getBytes();
            serverSocket.send(new DatagramPacket(buf, str1.length(), ia, cport));
        }
    }
}
