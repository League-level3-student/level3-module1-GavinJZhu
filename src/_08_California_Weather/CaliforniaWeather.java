package _08_California_Weather;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

/*
 * OBJECTIVE:
 * 1. Create a program that allows the user to search for the weather
 * conditions of a given city in California. Use the example program below
 * and the Utilities class inside this project to get the temperature data
 * from a day in December 2020.
 * Example: User: Encinitas
 *          Program: Encinitas is Overcast with a tempeature of 59.01 �F
 * 
 * 2. Create a way for the user to specify the weather condition and then
 * list the cities that have those conditions.
 * Example: User: Mostly Cloudy
 *          Program: Long Beach, Pomona, Oceanside, ...
 * 
 * 3. Create a way for the user to enter a minimum and maximum temperature
 * and then list the cities that have temperatures within that range
 * Example: User: minimum temperature �F = 65.0, max temperature �F = 70.0
 *          Program: Fortana, Glendale, Escondido, Del Mar, ...
 * 
 * EXTRA:
 * Feel free to add pictures for specific weather conditions or a thermometer
 * for the temperature. Also If you want your program to get the current day's
 * temperature, you can get a free API key at: https://openweathermap.org/api
 */

public class CaliforniaWeather {
    JFrame frame = new JFrame();
    JPanel panel = new JPanel();
    JButton citySearch = new JButton();
    JButton weatherSearch = new JButton();
    JButton tempRangeSearch = new JButton();
    void start() {
        HashMap<String, WeatherData> weatherData = Utilities.getWeatherData();
        
        // All city keys have the first letter capitalized of each word
        String cityName = Utilities.capitalizeWords( "National City" );
        WeatherData datum = weatherData.get(cityName);

        frame.setSize(100, 100);
        citySearch.setText("City Search");
        weatherSearch.setText("Weather Search");
        tempRangeSearch.setText("Temperature Range Search");
        panel.add(citySearch);
        panel.add(weatherSearch);
        panel.add(tempRangeSearch);
        frame.add(panel);
        frame.setVisible(true);
        citySearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = JOptionPane.showInputDialog("Input the city you'd like to search.");
                String cityCapitalized = Utilities.capitalizeWords(city);
                String summary = weatherData.get(cityCapitalized).weatherSummary;
                if (summary == null){
                    JOptionPane.showMessageDialog(null, "No city found with that name.");
                }
                else {
                    JOptionPane.showMessageDialog(null, cityCapitalized +" is "+summary+" with a temperature of "+weatherData.get(cityCapitalized).temperatureF+"F");
                }
            }
        });
        weatherSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String city = JOptionPane.showInputDialog("Input the city you'd like to search.");

            }
        });
        tempRangeSearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        if( datum == null ) {
            System.out.println("Unable to find weather data for: " + cityName);
        } else {
            System.out.println(cityName + " is " + datum.weatherSummary + " with a temperature of " + datum.temperatureF + " F");
        }
    }
}
