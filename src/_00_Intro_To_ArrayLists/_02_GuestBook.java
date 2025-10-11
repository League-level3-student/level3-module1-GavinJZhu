package _00_Intro_To_ArrayLists;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class _02_GuestBook implements ActionListener {
    /*
     * Create a GUI with two buttons. One button reads "Add Name" and the other
     * button reads "View Names". When the add name button is clicked, display
     * an input dialog that asks the user to enter a name. Add that name to an
     * ArrayList. When the "View Names" button is clicked, display a message
     * dialog that displays all the names added to the list. Format the list as
     * follows:
     * Guest #1: Bob Banders
     * Guest #2: Sandy Summers
     * Guest #3: Greg Ganders
     * Guest #4: Donny Doners
     */
    static ArrayList<String> names = new ArrayList<String>();
    static JFrame frame = new JFrame();
    static JPanel panel = new JPanel();
    static JButton addName = new JButton();
    static JButton viewNames = new JButton();

    public static void main(String[] args) {

        viewNames.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (names.isEmpty()){
                    JOptionPane.showMessageDialog(null,"No names currently in the guestbook.");
                }
                else {
                    JOptionPane.showMessageDialog(null, names.toArray());
                }
            }
        });
        addName.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = JOptionPane.showInputDialog(null, "Input a name into the guestbook here.");
                if(name != null) {
                    names.add(formatNames(name, names.size() + 1));
                }
            }
        });
        frame.setSize(100, 100);
        frame.add(panel);
        panel.add(addName);
        panel.add(viewNames);
        viewNames.setText("View Names");
        addName.setText("Add Name");
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
    // Guest #1: Bob Banders
    public static String formatNames(String name, int index){
        String formattedName;
        formattedName = "Guest #"+index+":"+" "+name;
        return formattedName;
    }
}
