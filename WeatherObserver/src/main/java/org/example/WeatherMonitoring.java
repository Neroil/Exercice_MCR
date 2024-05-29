package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

abstract class Subject{

    List<Observer> observers = new LinkedList<Observer>();

    public void attach(Observer o){
        observers.add(o);
    }

    public void detach(Observer o){
        observers.remove(o);
    }

    public void notifying(){
        for(Observer o : observers){
            o.update();
        }
    }

}

abstract class Observer {
    abstract void update();
}

abstract class WeatherObserver extends Observer{
    public WeatherData weatherData;
}

class WeatherData extends Subject {
    float temperature;
    int humidity;
    int pressure;

    void registerObserver(WeatherObserver o){
        o.weatherData = this;
        attach(o);
    }

    void removeObserver(WeatherObserver o){
        o.weatherData = null;
        detach(o);
    }

    void setMeasurements(float temperature,int humidity, int pressure){
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifying();
    }

    float getTemperature(){
        return temperature;
    }

    int getHumidity(){
        return humidity;
    }

    int getPressure(){
        return pressure;
    }


}



class CurrentConditionsDisplay extends WeatherObserver{
    float temperature;
    int humidity;
    int pressure;

    @Override
    void update() {
        if(weatherData != null){
            this.temperature = weatherData.getTemperature();
            this.humidity = weatherData.getHumidity();
            this.pressure = weatherData.getPressure();
        }
    }

    public String toString(){
        String res = "Current Conditions:\n";
        res += "Temperature: " + temperature + "C°\n";
        res += "Humidity: " + humidity + "%\n";
        res += "Pressure: " + pressure + " hPa\n";
        return res;
    }
}

class StatisticsDisplay extends WeatherObserver{
    ArrayList<Float> temperatures = new ArrayList<>();
    @Override
    void update() {
        if(weatherData != null){
            this.temperatures.add(weatherData.getTemperature());
        }
    }

    public String toString(){
        String res = "Statistics:\n";

        if (!temperatures.isEmpty()) {
            float avg = (float) temperatures.stream().mapToDouble(a -> a).average().getAsDouble();
            float max = temperatures.stream().max(Float::compare).get();
            float min = temperatures.stream().min(Float::compare).get();

            res += "Average Temperature: " + avg + "C°\n";
            res += "Maximum Temperature: " + max + "C°\n";
            res += "Minimum Temperature: " + min + "C°\n";
        } else {
            res += "No temperature data available.\n";
        }

        return res;
    }
}




public class WeatherMonitoring {
    public static void main(String[] args) {
        // Create weather observers
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        StatisticsDisplay statisticsDisplay = new StatisticsDisplay();


        // Create the WeatherData subject
        WeatherData weatherData = new WeatherData();

        // Register observers with the WeatherData
        weatherData.registerObserver(currentDisplay);
        weatherData.registerObserver(statisticsDisplay);


        // Update weather data
        weatherData.setMeasurements(25.0f, 60, 1010);
        weatherData.setMeasurements(26.0f, 65, 1012);
        weatherData.setMeasurements(24.0f, 55, 1008);

        System.out.println(currentDisplay);
        System.out.println(statisticsDisplay);


        // Unregister an observer
        //weatherData.removeObserver(statisticsDisplay);

        // Update weather data again
        weatherData.setMeasurements(27.0f, 70, 1015);

        System.out.println(currentDisplay);
        System.out.println(statisticsDisplay);

    }
}