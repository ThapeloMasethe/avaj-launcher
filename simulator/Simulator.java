package simulator;

import aircrafts.*;
import weather.*;
import java.io.*;

/**
 * Simulator Class
 * 
 * @author  Thapelo Masethe
 * @since   2019-06-20
 * @version 1.0
 */
public class Simulator {
    public static PrintWriter printWriter;
    public static int numberOfSimulaions;
    private static Md5Checker md5Checker = new Md5Checker();

    /**
     * Validate coordinates.
     * 
     * @param lon Longitude string value.
     * @param lat Latitude string value.
     * @param hgt Height string value.
     * @throws InvalidCoordinatesException if coordinates are positive.
     * @throws NumberFormatException if coordinates are not numbers.
     */
    public  static void checkCoordinates(String lon, String lat, String hgt)
    throws InvalidCoordinatesException, NumberFormatException {
        int latitude, longitude, height;

        latitude = longitude = height = 0;
        // Check if coordinates are integers.
        try {
            latitude = Integer.parseInt(lat);
            longitude = Integer.parseInt(lon);
            height = Integer.parseInt(hgt);
        } catch (NumberFormatException numberFormatException) {
           throw new InvalidCoordinatesException(numberFormatException.getMessage()
           + " is not an integer!");
        }
        // Check if Coordinates are positive.
        if (latitude < 0) {
            throw new InvalidCoordinatesException("Latitude must be positive!");
        } else if (longitude < 0) {
            throw new InvalidCoordinatesException("Longitude must be positive!");
        } else if (height < 0) {
            throw new InvalidCoordinatesException("Height must be positive!");
        } 
    }

    /**
     * Error Handling & File parsing.
     * 
     * @param fileName The name of the file.
     * @throws InvalidFileContentException if the first line is not positive integer & 
     * the Aircraft type does not exist.
     * @throws Exception general exception.
     */
    public  static  void checkFileContent(String fileName)
    throws InvalidFileContentException, Exception {
        AircraftFactory aircraftFactory = new AircraftFactory();
        WeatherTower weatherTower = new WeatherTower();

        try {
            int line;
            String lines, type, name;
            String[] aircraftDetails;

            line = 0;
            FileInputStream fileInputStream = new FileInputStream(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            while ((lines = bufferedReader.readLine()) != null) {
                // Check if the first line is an Integer.
                if (line == 0) {
                    try {
                        numberOfSimulaions = Integer.parseInt(lines);
                        if (numberOfSimulaions < 0) {
                            throw new InvalidFileContentException("First line must be a positive integer.");
                        }
                    } catch (Exception exception) {
                        throw new InvalidFileContentException("First line must be a positive integer.");
                    }
                } else {
                    aircraftDetails = lines.split(" ");
                    // Skip the empty lines.
                    if (aircraftDetails.length == 1 &&
                        aircraftDetails[0].isEmpty()) {
                            continue ;
                    }
                    /**
                     * Check if the number of parameters are 5 per line else display
                     *  the current number of parameters and locate the exact line #.
                     */
                    if (aircraftDetails.length != 5) {
                        throw new InvalidFileContentException("5 parameters needed, " +
                        aircraftDetails.length + " found at line " + (int)(line + 1));
                    }
                    // Check if the aircraft type exists.
                    if (aircraftDetails[0].toLowerCase().equals("baloon") 
                        || aircraftDetails[0].toLowerCase().equals("helicopter")
                        || aircraftDetails[0].toLowerCase().equals("jetplane")) {
                        type = aircraftDetails[0];
                        name = aircraftDetails[1];   
                    } else {
                        throw new InvalidFileContentException("Aircraft type does not exist!");
                    }
                    // Check the coordinates before trying to create a new aircraft.
                    checkCoordinates(aircraftDetails[2], aircraftDetails[3], aircraftDetails[4]);
                    try {
                        aircraftFactory.newAircraft(type, name, 
                            Integer.parseInt(aircraftDetails[2]),
                            Integer.parseInt(aircraftDetails[3]),
                            Integer.parseInt(aircraftDetails[4])).registerTower(weatherTower);
                    } catch (Exception exception) {
                        throw new Exception(exception.getMessage() + " Cant Create Aircraft!");
                    }
                }
                line++;
            }
        } catch (Exception exception) {
            System.out.println("ERROR: " + exception.getMessage());
        } 
        // Run the the simulation.
        for (int i = 0; i < numberOfSimulaions; i++) {
            weatherTower.changeWeather();
        }
        printWriter.close();
    }

    /**
     * AVAJ-LAUNCHER main method ;)
     * 
     * @param args an arguement from command line.
     */
    public static void main(String[] args)
    {
        if (args.length == 1) {
            String fileName = args[0];
            File simulationFile = new File("simulation.txt");

            try {
                printWriter = new PrintWriter(simulationFile);
                // Error Handling & File Parsing.
                checkFileContent(fileName);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        } else if (args.length > 1) {
            try {
                // Load the file & MD5 hash.
                md5Checker.checkMd5Hashing(args[0], args[1]);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
           }
        } else if (args[0].isEmpty()) {
            System.out.println("Input file not found!");
        }
    }
}