import java.util.Scanner;

public class CRC {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the data bits (0s and 1s): ");
        String data = scanner.nextLine();

        System.out.println("Enter the divisor bits (excluding leading 1): ");
        String divisor = scanner.nextLine();

        String crc = generateCRC(data, divisor);

        System.out.println("CRC code: " + crc);
        scanner.close();
    }

    static String generateCRC(String data, String divisor) {
        int dataLength = data.length();
        int divisorLength = divisor.length();

        // Append zeros to the data for division
        StringBuilder dataWithZeros = new StringBuilder(data);
        for (int i = 0; i < divisorLength - 1; i++) {
            dataWithZeros.append("0");
        }

        char[] dataBits = dataWithZeros.toString().toCharArray();
        char[] divisorBits = divisor.toCharArray();

        // Perform binary division
        for (int i = 0; i < dataLength; i++) {
            if (dataBits[i] == '1') {
                for (int j = 0; j < divisorLength; j++) {
                    dataBits[i + j] = (dataBits[i + j] == divisorBits[j]) ? '0' : '1';
                }
            }
        }

        // Extract the remainder (CRC)
        StringBuilder crc = new StringBuilder();
        for (int i = dataLength; i < dataBits.length; i++) {
            crc.append(dataBits[i]);
        }

        return crc.toString();
    }
}
