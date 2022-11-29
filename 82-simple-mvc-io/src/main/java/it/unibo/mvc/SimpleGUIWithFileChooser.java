package it.unibo.mvc;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.BorderLayout;


/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser extends SimpleGUI{

    private static final String TITLE = "lab08-MVC";
    private final JFrame frame = new JFrame(TITLE);

    public SimpleGUIWithFileChooser(Controller ctrl) {
        super(ctrl);

        JPanel search = new JPanel();
        search.setLayout(new BorderLayout());
        JTextField tf = new JTextField();
        JButton browse = new JButton("Browse");
        browse.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent e) {
                final JFileChooser fc = new JFileChooser("Scegli dove salvare");
                fc.setSelectedFile(ctrl.getFile());
                final int result = fc.showSaveDialog(frame);
                switch (result) {
                case JFileChooser.APPROVE_OPTION:
                    final File newDest = fc.getSelectedFile();
                    System.out.println(newDest.getName());
                    ctrl.setFile(newDest.getName());
                    tf.setText(newDest.getPath());
                    break;
                case JFileChooser.CANCEL_OPTION:
                    break;
                default:
                    JOptionPane.showMessageDialog(frame, result, "Errore", JOptionPane.ERROR_MESSAGE);
                }
            }
        } );

        search.add(tf, BorderLayout.CENTER);
        search.add(browse, BorderLayout.LINE_END);

        myPanel.add(search, BorderLayout.NORTH);

        frame.getContentPane().add(myPanel);
        frame.setVisible(true);

    }    
    
    private void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 3, sh / 3);
        frame.setVisible(true);
    }

    public static void main(final String... args) {
        SimpleGUIWithFileChooser gui = new SimpleGUIWithFileChooser(new Controller());
        gui.display();
    }

}
