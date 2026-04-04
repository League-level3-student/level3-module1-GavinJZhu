package _09_World_Clocks;

import _08_California_Weather.Utilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.TimeZone;

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

    ArrayList<JTextArea> textAreas = new ArrayList<JTextArea>();
    JFrame frame;
    JPanel panel = new JPanel();
    JButton chooseCity;

    String city;
    String dateStr;
    String timeStr;

    HashMap<String, TimeZone> citiesAndTimeZones = new HashMap<>();
    String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    public WorldClocks() {
        clockUtil = new ClockUtilities();

        // The format for the city must be: city, country (all caps)
        frame = new JFrame();
        chooseCity = new JButton();
        frame.setLayout(new BorderLayout());
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        chooseCity.setText("Add city");
        chooseCity.addActionListener(this);
        chooseCity.setSize(100, 20);
        frame.add(panel, BorderLayout.CENTER);
        frame.add(chooseCity, BorderLayout.NORTH);
        frame.pack();
        frame.setVisible(true);
        // This Timer object is set to call the actionPerformed() method every
        // 1000 milliseconds
        timer = new Timer(1000, this);
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == chooseCity) {
            city = getCityFromUser();
            citiesAndTimeZones.put(city, clockUtil.getTimeZoneFromCityName(city));
            // Display time string
            addNewCityClock(city, getUpdatedTimeString(city));
            frame.pack();
        }
        int i = 0;
        for (String cityInKeyset : citiesAndTimeZones.keySet()) {
            String updatedTime = getUpdatedTimeString(cityInKeyset);
            textAreas.get(i).setText(cityInKeyset + "\n" + updatedTime);
            i++;
            frame.pack();
        }
    }

    public String getCityFromUser() {
        String cityTBA = "";
        while (cityTBA.isBlank()) {
            cityTBA = JOptionPane.showInputDialog(null, "What city would you like to add? \n (Format it like 'City, TWO LETTER Country Code'");
        }
        cityTBA = Utilities.capitalizeWords(cityTBA);
        TimeZone newCityZone = clockUtil.getTimeZoneFromCityName(cityTBA);
        if (newCityZone == null || cityTBA.isEmpty() || cityTBA.length() == 1) {
            cityTBA = null;
        }

        if (isCityAdded(cityTBA)) {
            //called if the queried timeZone doesn't exist or the city has already been entered
            cityTBA = null;
            JOptionPane.showMessageDialog(null, "City is already added.");
        }
        return cityTBA;
    }

    public String getUpdatedTimeString(String city) {
        String updatedTime;
        Calendar c = Calendar.getInstance(citiesAndTimeZones.get(city));
        String militaryTime = c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND);
        String twelveHourTime = " [" + c.get(Calendar.HOUR) + ":" + c.get(Calendar.MINUTE) + ":" + c.get(Calendar.SECOND) + "]";
        timeStr = militaryTime + twelveHourTime;
        int monthIndex = c.get(Calendar.MONTH);
        dateStr = monthNames[monthIndex] + " " + c.get(Calendar.DATE) + ", " + c.get(Calendar.YEAR);
        updatedTime = dateStr + "\n" + timeStr;
        return updatedTime;
    }

    public boolean isCityAdded(String city) {
        return citiesAndTimeZones.containsKey(city);
    }

    public void addNewCityClock(String city2, String updatedTime) {
        JTextArea newCity = new JTextArea();
        newCity.setText(city2 + "\n" + updatedTime);
        textAreas.add(newCity);
        panel.add(newCity);
        panel.add(Box.createVerticalStrut(10));
        frame.add(panel);
        frame.repaint();
    }
}
