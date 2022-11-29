package it.unibo.mvc;

import java.util.List;

/**
 * 
 */
public interface Controller {

    /**
     * 
     * @param nextString next string to print
     */
    void setNextStringToPrint(String nextString);

    /**
     * @return next String to print
     */
    String getNextString();

    /**
     * @return List of Strings already printed
     */
    List<String> getHistory();

    /**
     * 
     */
    void print();
}
