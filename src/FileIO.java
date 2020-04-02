
import java.io.*;

/**
 * Writes text to a character-output stream, buffering characters so as to provide for the efficient writing
 * of single characters, arrays, and strings.
 * @author Nelson Zeas
 *
 */
public class FileIO {

    /**
     * Name of the file to open
     */
    static String inputFileName;

    /**
     * Reads characters from a file
     */
    static BufferedReader reader;

    /**
     * Constructs a new FileWriter and FileReader
     * @param inputFileName The name of the file to be read
     */
    public static void setIo(String inputFileName) {
        try {
            reader = new BufferedReader(new FileReader(inputFileName));
        }catch ( IOException e ){
            System.out.println("Error opening input file!");
        }
        inputFileName = inputFileName;
    }


    /**
     * Reads a char from inputFile
     * @return Char read from input file
     * @throws IOException
     */
    public static int getNextChar(){
        try {
            return reader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }



    /**
     * Closes file
     */
    public static void close() {
        try {
            reader.close();
        }catch ( IOException e ){
            System.out.println("Error Closing files!");
        }
    }

}
