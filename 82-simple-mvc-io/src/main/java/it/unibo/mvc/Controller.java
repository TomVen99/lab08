package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 *  1. A method for setting a File as current file
    2. A method for getting the current File
    3. A method for getting the path (in form of String) of the current `File`
    4. A method that gets a `String` as input and saves its content on the current file.
        This method may throw an `IOException`.
    5. By default, the current file is "output.txt" inside the user home folder.
 */
/*
 * A String representing the local user home folder can be accessed using `System.getProperty("user.home")`.
    The separator symbol can be obtained as String through the method `System.getProperty("file.separator")`.
    The combined use of those methods leads to a software that runs correctly on every platform.
 */
public class Controller {
    private static final String PATH = System.getProperty("user.home") + System.getProperty("file.separator");
    private static final String FILENAME = "output.txt";

    private String currentFile = FILENAME;
    private File dest = new File(PATH + File.separator + FILENAME);

    // A method for setting a File as current file
    public void setFile(String name) {
        this.currentFile = name;
    }

    // A method for getting the current File
    public File getFile() {
        return this.dest;
    }

    // A method for getting the path (in form of String) of the current `File`
    public String getFilePath() {
        return PATH+this.currentFile;
    }

    // A method that gets a `String` as input and saves its content on the current file.
    // This method may throw an `IOException`.
    public void printOnFile(final String text) throws IOException{
        try (PrintStream out = new PrintStream(getFilePath(), StandardCharsets.UTF_8)) {
            out.print(text);
            System.out.println(text);
        }/*catch (IOException ex) {
            System.out.println(ex);
        }*/
    }
}
