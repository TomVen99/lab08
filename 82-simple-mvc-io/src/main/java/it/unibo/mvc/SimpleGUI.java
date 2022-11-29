package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.Toolkit;


/**
 * A very simple program using a graphical interface.
 *  1. It has a main method that starts the graphical application
    2. In its constructor, sets up the whole view
    3. The graphical interface consists of a `JTextArea` with a button "Save" right below
        (see `src/test/resources/ex02.png` for the expected result). 
    4. SUGGESTION: Use a `JPanel` with `BorderLayout`
    5. By default, if the graphical interface is closed the program must exit (call `setDefaultCloseOperation`)
    6. The program asks the controller to save the file if the button "Save" gets pressed.
 */
public class SimpleGUI {

    private static final String TITLE = "lab08-MVC";
    private final JFrame frame = new JFrame(TITLE);
    final JPanel myPanel = new JPanel();

    public SimpleGUI(final Controller  ctrl) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        myPanel.setLayout(new BorderLayout());
        frame.getContentPane().add(myPanel);
        JTextArea ta = new JTextArea();
        JButton save = new JButton("Save");
        myPanel.add(save, BorderLayout.SOUTH);
        myPanel.add(ta);

        save.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
               try {
                ctrl.printOnFile(ta.getText());             
               } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "An error occurred", JOptionPane.ERROR_MESSAGE);
               }
            }            
        });
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 5, sh / 5);
        // frame.pack();
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }

    public static void main(final String... args) {
        SimpleGUI gui = new SimpleGUI(new Controller());
        gui.display();
    }
}
