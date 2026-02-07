package _09_World_Clocks;

import _08_California_Weather.Utilities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.TimeZone;

import javax.swing.*;
//Auckland, Tokyo, Dhaka, Moscow, London, Rio de Janeiro, NYC, LA, Honolulu

/*
 * Your task is to create a java program that:
 * 1. Displays the time for multiple cities around the world on one display.
 * 2. Gives the user the ability to add a city to the display. One possible
 *    way to do this is to create a HashMap of city names and their
 *    corresponding time zones, e.g. HashMap<String, TimeZone>, then use each
 *    city's TimeZone to get the current date/time every second using a
 *    Timer object (see example code below).
 * 
 * The code below is an example of how to print out a clock for San Diego.
 * Use the ClockUtilities class to find the time zone of each city, then use
 * Calendar.getInstance to return a Calendar object to get the current time for
 * that city. Example:
 *   TimeZone timeZone = clockUtil.getTimeZoneFromCityName("San Diego, US");
 *   Calendar c = Calendar.getInstance(timeZone);
 *   System.out.println("Full date and time: " + calendar.getTime());
 * 
 * NOTE: The program may take a second or two to execute
 * 
 * Calendar class:
 * https://docs.oracle.com/javase/7/docs/api/java/util/Calendar.html
 */

public class WorldClocks implements ActionListener {
    ClockUtilities clockUtil;
    Timer timer;
    TimeZone timeZone;

    JFrame frame;
    JPanel panel;
    JTextArea textArea;
    JButton chooseCity;


    String city;
    String dateStr;
    String timeStr;
    HashMap<String, TimeZone> cities = new HashMap<>();
    
    public WorldClocks() {
        clockUtil = new ClockUtilities();

        // The format for the city must be: city, country (all caps)

        // Sample starter program
        frame = new JFrame();
        panel = new JPanel();
        textArea = new JTextArea();
        chooseCity = new JButton();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(200, 200);
        frame.add(panel);
        panel.add(textArea);
        chooseCity.setText("Add city");
        chooseCity.addActionListener(this);
        panel.add(chooseCity);
        frame.pack();
        frame.setVisible(true);
        // This Timer object is set to call the actionPerformed() method every
        // 1000 milliseconds
        timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == chooseCity) {
            city = JOptionPane.showInputDialog(null, "What city would you like to add? \n (Format it like 'City, TWO LETTER Country Code'");
            city = Utilities.capitalizeWords(city);
            timeZone = clockUtil.getTimeZoneFromCityName(city);
            if (timeZone == null){
                timeZone = clockUtil.getTimeZoneFromCityName("San Diego, US");
            }
            if (city == null){
                city = "San Diego, US";
            }
        }

        Calendar c = Calendar.getInstance(timeZone);
        String militaryTime = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
        String twelveHourTime = " [" + c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) + "]";
        timeStr = militaryTime + twelveHourTime;
        textArea.setText(city + "\n" + dateStr + "\n" + timeStr);
        System.out.println(timeStr);
        frame.pack();
    }
}
