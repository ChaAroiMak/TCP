import java.io.*;

public class LNStreams {
    public static void main(String [] args) {
        OutputStream os = null;
        String filename = null;

        try {
            filename = "testFile.txt";
            os = new FileOutputStream(filename);
        } catch (FileNotFoundException ex) {
            System.err.println("couldn't open file - fatal");
            System.exit(0); // brutal exception handling
        }

        String someText = "Hallo";
        byte[] textAsByte = someText.getBytes();

        try {
            os.write(textAsByte);
        } catch (IOException ex) {
            System.err.println("couldn't write dara (fatal)");
            System.exit(0);
        }

        InputStream is = null;
        try {
            is = new FileInputStream(filename);
        } catch (FileNotFoundException ex) {
            System.err.println("couldn't open file - fatal");
            System.exit(0);
        }

        byte[] readBuffer = new byte[100];
        try {
            is.read(readBuffer);
        } catch (IOException ex) {
            System.err.println("couldn't read data (fatal)");
            System.exit(0);
        }

        String readString = new String (readBuffer);
        System.out.println("read something: " + readString);

    }
}
