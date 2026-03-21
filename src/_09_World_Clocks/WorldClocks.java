package _09_World_Clocks;

import _08_California_Weather.Utilities;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

    JFrame frame;
    //JPanel buttonPanel;
    JPanel panel = new JPanel();
//    JTextArea textArea;
    JButton chooseCity;


    String city;
    String dateStr;
    String timeStr;

    HashMap<String, TimeZone> citiesAndTimeZones = new HashMap<>();
    String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public WorldClocks() {
        clockUtil = new ClockUtilities();

        // The format for the city must be: city, country (all caps)

        // Sample starter program
        frame = new JFrame();
        //buttonPanel = new JPanel();
//        textArea = new JTextArea();
        chooseCity = new JButton();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
//        panel.add(textArea);
        frame.add(panel);
        chooseCity.setText("Add city");
        chooseCity.addActionListener(this);
        //buttonPanel.add(chooseCity);
        frame.add(chooseCity);
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
            city = getCityFromUser();
            if (city != null){
                citiesAndTimeZones.put(city, clockUtil.getTimeZoneFromCityName(city));
                // Display time string
                addNewCityClock(city, getUpdatedTimeString(city));
                frame.pack();
            }
            else{
                System.out.println("city doesn't exist");
            }
        }
        int i=0;
        for (String cityInKeyset : citiesAndTimeZones.keySet()) {
            String updatedTime = getUpdatedTimeString(cityInKeyset);
            textAreas.get(i).setText(cityInKeyset + "\n" + updatedTime);
            i++;
            frame.pack();
        }
    }
    //String oldCity = "San Diego, US";
    public String getCityFromUser() {
        String city2;
        city2 = JOptionPane.showInputDialog(null, "What city would you like to add? \n (Format it like 'City, TWO LETTER Country Code'");
        city2 = Utilities.capitalizeWords(city2);
        TimeZone newCityZone = clockUtil.getTimeZoneFromCityName(city2);
        if (newCityZone == null || city2.isEmpty() || city2.length() == 1) {
            city2 = null;
            JOptionPane.showMessageDialog(null, "City doesn't exist.");// default
        }

        if (isCityDefined(city2)) {
            //called if the queried timeZone doesn't exist or the city has already been entered
            city2 = null;
            JOptionPane.showMessageDialog(null, "City is already defined.");
            //System.out.println("city is defined");
        }
        return city2;
    }
    public String getUpdatedTimeString(String city) {
        String updatedTime;
        //TBD: change instances of oldCity to use isCityDefined
//        if (isCityDefined(city)){
//            //called if the queried timeZone doesn't exist or the city has already been entered
//            JOptionPane.showMessageDialog(null, "City is already defined or doesn't exist.");
//        }

        //else {
//        TimeZone timeZone;
//        timeZone = clockUtil.getTimeZoneFromCityName(city);
            Calendar c = Calendar.getInstance(citiesAndTimeZones.get(city));
            String militaryTime = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
            String twelveHourTime = " [" + c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) + "]";
            timeStr = militaryTime + twelveHourTime;

            int monthIndex = c.get(Calendar.MONTH);
            dateStr = monthNames[monthIndex] + " " + c.get(Calendar.DATE) + ", " + c.get(Calendar.YEAR);

        //}
        updatedTime = dateStr + "\n" + timeStr;
        return updatedTime;
    }
    public boolean isCityDefined(String city){
        boolean isCityDefined = false;
        //TBD: Iterate through hashmap of cities and find if queried city is already defined
        for (String cityInHash : citiesAndTimeZones.keySet()){
            if (cityInHash == city) {
                isCityDefined = true;
                System.out.println("citydefined");
                break;
            }
        }
        return isCityDefined;
    }
    ArrayList<JTextArea> textAreas = new ArrayList<JTextArea>();
    public void addNewCityClock(String city2, String updatedTime) {
        JTextArea newCity = new JTextArea();
        newCity.setText(city2 + "\n" + updatedTime);
        textAreas.add(newCity);
        panel.add(newCity);
        frame.add(panel);
        frame.pack();
    }

}
