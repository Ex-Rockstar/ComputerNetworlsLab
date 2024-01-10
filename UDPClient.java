import java.io.*;
import java.net.*;

class UDPClient {
    public static DatagramSocket clientSocket;
    public static DatagramPacket dp;
    public static BufferedReader dis;
    public static InetAddress ia;
    public static byte[] buf = new byte[1024];
    public static int cport = 789, sport = 790;

    public static void main(String[] args) throws IOException {
        clientSocket = new DatagramSocket(cport);
        dp = new DatagramPacket(buf, buf.length);
        dis = new BufferedReader(new InputStreamReader(System.in));
        ia = InetAddress.getLocalHost();

        System.out.println("Client is Running... Type 'STOP' to quit.");

        while (true) {
            String str = new String(dis.readLine());
            buf = str.getBytes();
            clientSocket.send(new DatagramPacket(buf, str.length(), ia, sport));

            if (str.equals("STOP")) {
                System.out.println("Terminated...");
                break;
            }

            clientSocket.receive(dp);
            String str2 = new String(dp.getData(), 0, dp.getLength());
            System.out.println("Server: " + str2);
        }

        clientSocket.close();
    }
}
