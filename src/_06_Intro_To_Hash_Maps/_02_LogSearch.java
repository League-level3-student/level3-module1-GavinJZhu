package _06_Intro_To_Hash_Maps;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class _02_LogSearch implements ActionListener {
    /*
     * Create a HashMap of Integers for the keys and Strings for the values.
     * Create a GUI with three buttons.
     * Button 1: Add Entry
     *      When this button is clicked, use an input dialog to ask the user
     *      to enter an ID number.
     *      After an ID is entered, use another input dialog to ask the user
     *      to enter a name. Add this information as a new entry to your
     *      HashMap.
     *
     * Button 2: Search by ID
     *      When this button is clicked, use an input dialog to ask the user
     *      to enter an ID number.
     *      If that ID exists, display that name to the user.
     *      Otherwise, tell the user that that entry does not exist.
     *
     * Button 3: View List
     *      When this button is clicked, display the entire list in a message
     *      dialog in the following format:
     *      ID: 123  Name: Harry Howard
     *      ID: 245  Name: Polly Powers
     *      ID: 433  Name: Oliver Ortega
     *      etc...
     *
     * When this is complete, add a fourth button to your window.
     * Button 4: Remove Entry
     *      When this button is clicked, prompt the user to enter an ID using
     *      an input dialog.
     *      If this ID exists in the HashMap, remove it. Otherwise, notify the
     *      user that the ID is not in the list.
     */
    static HashMap<Integer, String> ids = new HashMap<Integer, String>();
    static JFrame frame = new JFrame();
    static JPanel panel = new JPanel();
    static JButton add = new JButton();
    static JButton search = new JButton();
    static JButton view = new JButton();
    static JButton remove = new JButton();

    public static void main(String[] args) {
        panel.add(add);
        add.setText("Add Entry");
        panel.add(search);
        search.setText("Search by ID");
        panel.add(view);
        view.setText("View List");
        panel.add(remove);
        remove.setText("Remove Entry");
        frame.add(panel);
        frame.setSize(250, 100);
        frame.setTitle("ID Database");
        frame.setVisible(true);
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idNumber = JOptionPane.showInputDialog("Please enter your ID number.", null);
                String name = JOptionPane.showInputDialog("Please enter your name.", null);
                ids.put(Integer.valueOf(idNumber), name);
                JOptionPane.showMessageDialog(null, "Thanks! The database has been updated.");
            }
        });
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog("Enter ID", null);
                String personAt = ids.get(Integer.valueOf(id));
                if (personAt == null) {
                    JOptionPane.showMessageDialog(null, "ID doesn't exist.");
                } else {
                    JOptionPane.showMessageDialog(null, "The person with ID #" + id + " is " + personAt);
                }
            }
        });
        view.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String message = "ID List: ";
                for (Integer i : ids.keySet()) {
                    message += "\nID: " + i + "   Name: " + ids.get(i);
                }
                JOptionPane.showMessageDialog(null, message);
            }
        });
        remove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = JOptionPane.showInputDialog("Enter the ID you would like to remove", null);
                String idNum = ids.remove(Integer.valueOf(id));
                if (idNum == null) {
                    JOptionPane.showMessageDialog(null, "ID doesn't exist.");
                } else {
                    JOptionPane.showMessageDialog(null, "ID sucessfully removed.");
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
