import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class PingServer {
    public static void main(String args[]) {
        try {
            String str;
            System.out.print("Enter the IP Address to be Ping: ");
            BufferedReader buf1 = new BufferedReader(new InputStreamReader(System.in));
            String ip = buf1.readLine();

            ProcessBuilder processBuilder = new ProcessBuilder("ping", ip);
            Process process = processBuilder.start();

            InputStream in = process.getInputStream();
            BufferedReader buf2 = new BufferedReader(new InputStreamReader(in));

            while ((str = buf2.readLine()) != null) {
                System.out.println(" " + str);
            }

            // Check the exit value of the process to determine success or failure
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Ping successful.");
            } else {
                System.out.println("Ping failed. Exit code: " + exitCode);
            }

        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
