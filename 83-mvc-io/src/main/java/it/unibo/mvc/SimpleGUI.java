package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * A very simple program using a graphical interface.
 * 
 * 
 */
public final class SimpleGUI {

    private static final int PROPORTION = 5;
    private final JFrame frame = new JFrame("Application");

    /**
     * @param ctrl controller of MVC
     */
    public SimpleGUI(final SimpleController ctrl) {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        final JTextField write = new JTextField();
        final JTextArea show = new JTextArea();
        final JButton print = new JButton("Print");
        print.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                ctrl.setNextStringToPrint(write.getText());
                ctrl.print();
                write.setText("");
            }
        });

        final JButton history = new JButton("Show history");
        history.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent arg0) {
                show.setText(ctrl.getHistory().toString());
            }
        });

        final JPanel pan = new JPanel();
        pan.setLayout(new BorderLayout());

        pan.add(write, BorderLayout.NORTH);
        pan.add(show, BorderLayout.CENTER);

        final JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(1, 2));
        buttons.add(print);
        buttons.add(history);
        pan.add(buttons, BorderLayout.SOUTH);

        frame.getContentPane().add(pan);
    }

    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        frame.setVisible(true);
        }

    /**
     * @param args
     */
    public static void main(final String...args) {
        final SimpleGUI sg = new SimpleGUI(new SimpleController());
        sg.display();
    }
}
