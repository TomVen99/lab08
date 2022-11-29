package it.unibo.mvc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

/**
 */
public final class DrawNumberApp implements DrawNumberViewObserver {
    private static final int MIN = 0;
    private static final int MAX = 100;
    private static final int ATTEMPTS = 10;

    private final DrawNumber model;
    private final List<DrawNumberView> views;

    /**
     * @param views
     *            the views to attach
     */
    public DrawNumberApp(final String configFile, final DrawNumberView... views) {
        /*
         * Side-effect proof
         */
        this.views = Arrays.asList(Arrays.copyOf(views, views.length));
        for (final DrawNumberView view: views) {
            view.setObserver(this);
            view.start();
        }
        Configuration.Builder configurationBuilder = new Configuration.Builder();
        try (var read = new BufferedReader(new InputStreamReader(ClassLoader.getSystemResourceAsStream(configFile)))) {
            String line;
            while ((line = read.readLine()) != null) {
                System.out.println(line);
                var configs = line.split(":");
                if (configs.length == 2) {
                    final int value = Integer.parseInt(configs[1].trim());
                    switch (configs[0]) {
                        case "minimum":
                            configurationBuilder.setMin(value);
                        case "maximum":
                            configurationBuilder.setMax(value);
                        case "attempts":
                            configurationBuilder.setAttempts(value);
                        default:
                            throw new IllegalArgumentException("Invelid column on file " + configs[0]);
                    }
                }else {
                    throw new IllegalArgumentException("Invalid column in file " + configs[0]);
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        final var conf = configurationBuilder.build();
        if (conf.isConsistent()) {
            this.model = new DrawNumberImpl(conf);
        } else {
            System.out.println("Can't read configuration. Using default configs");
            this.model = new DrawNumberImpl(new Configuration.Builder().build());
        }
    }

    @Override
    public void newAttempt(final int n) {
        try {
            final DrawResult result = model.attempt(n);
            for (final DrawNumberView view: views) {
                view.result(result);
            }
        } catch (IllegalArgumentException e) {
            for (final DrawNumberView view: views) {
                view.numberIncorrect();
            }
        }
    }

    @Override
    public void resetGame() {
        this.model.reset();
    }

    @Override
    public void quit() {
        /*
         * A bit harsh. A good application should configure the graphics to exit by
         * natural termination when closing is hit. To do things more cleanly, attention
         * should be paid to alive threads, as the application would continue to persist
         * until the last thread terminates.
         */
        System.exit(0);
    }

    /**
     * @param args
     *            ignored
     * @throws FileNotFoundException 
     */
    public static void main(final String... args) throws FileNotFoundException {
        new DrawNumberApp("config.yml",new DrawNumberViewImpl());/*,
        new PrintStreamView(System.out),
        new PrintStreamView("output.log"));*/
    }
    
}
