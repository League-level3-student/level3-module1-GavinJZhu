package _09_World_Clocks;

import _08_California_Weather.Utilities;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;
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
    String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

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

    //@Override
    public void gavin_actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == chooseCity) {
            city = JOptionPane.showInputDialog(null, "What city would you like to add? \n (Format it like 'City, TWO LETTER Country Code'");
            city = Utilities.capitalizeWords(city);
            timeZone = clockUtil.getTimeZoneFromCityName(city);
        }
        if (timeZone == null) {
            timeZone = clockUtil.getTimeZoneFromCityName("San Diego, US");
        }
        if (city == null) {
            city = "San Diego, US";
        }
        Calendar c = Calendar.getInstance(timeZone);
        String militaryTime = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
        String twelveHourTime = " [" + c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) + "]";
        timeStr = militaryTime + twelveHourTime;
        int monthIndex = c.get(Calendar.MONTH);
        String date = monthNames[monthIndex] + " " + c.get(Calendar.DATE) + ", " + c.get(Calendar.YEAR);

        dateStr = date;
        textArea.setText(city + "\n" + dateStr + "\n" + timeStr);
        System.out.println(timeStr);
        frame.pack();
    }


    @Override
    public void actionPerformed(ActionEvent arg0) {
        if (arg0.getSource() == chooseCity) {
            city = getCityFromUser();
            if (city != null){
                //TBD: change second parameter to getUpdatedTimeString(city)
                cities.put(city, clockUtil.getTimeZoneFromCityName(city));
            }
        }
        if (cities.isEmpty()) {
            // Get updated time string for city
            // TBD: change current if statement to iterate thru all my Hashmap for cities, comment out 119
            String updatedTime = getUpdatedTimeString(city);

            // Display time string
            displayTimeString(city, updatedTime);
        }
    }
    String oldCity = "San Diego, US";
    public String getCityFromUser() {
        String city2;
        city2 = JOptionPane.showInputDialog(null, "What city would you like to add? \n (Format it like 'City, TWO LETTER Country Code'");
        city2 = Utilities.capitalizeWords(city2);
        TimeZone newCityZone = clockUtil.getTimeZoneFromCityName(city2);
        if (newCityZone == null || city2.isEmpty() || city2.length() == 1) {
            city2 = null; // default
        }
        return city2;
    }
    public String getUpdatedTimeString(String city) {
        String updatedTime = null;
        //TBD: change instances of oldCity to use isCityDefined
        if (timeZone == null || oldCity != city){
            //called if the queried timeZone doesn't exist or the city has already been entered
            timeZone = clockUtil.getTimeZoneFromCityName(city);
            if (timeZone == null){
                //called if user types in an invalid city
                //defaults program back to previous valid city
                city = oldCity;
                timeZone = clockUtil.getTimeZoneFromCityName(city);
                System.out.println("city: "+city);
            }
        }

        else {
            Calendar c = Calendar.getInstance(timeZone);
            String militaryTime = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
            String twelveHourTime = " [" + c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) + "]";
            timeStr = militaryTime + twelveHourTime;

            int monthIndex = c.get(Calendar.MONTH);
            dateStr = monthNames[monthIndex] + " " + c.get(Calendar.DATE) + ", " + c.get(Calendar.YEAR);

        }
        updatedTime = dateStr + "\n" + timeStr;
        oldCity = city;
        return updatedTime;
    }
    public boolean isCityDefined(String city){
        boolean isCityDefined = false;
        //TBD: Iterate through hashmap of cities and find if queried city is already defined
        return isCityDefined;
    }
    public void displayTimeString(String city2, String updatedTime) {
        //textArea.setText(city2 + "\n" + updatedTime);
        textArea.setText("");
        textArea.append(city2 + "\n" + updatedTime);
        System.out.println(timeStr);
        frame.pack();
    }
}
