package it.unibo.mvc;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private String nextString;
    private final List<String> history = new LinkedList<>();

    @Override
    public void setNextStringToPrint(final String nextString) {
        if (nextString == null) {
            throw new IllegalStateException("Stringa non settata (set)");
        }
        this.nextString = nextString;
    }

    @Override
    public String getNextString() {
        return this.nextString;
    }

    @Override
    public List<String> getHistory() {
        return Collections.unmodifiableList(history);
    }

    @Override
    public void print() {
        if (nextString == null) {
            throw new IllegalStateException("La stringa non è settata (print)");
        }
        System.out.println(nextString); //NOPMD
        history.add(nextString);
    }

}
