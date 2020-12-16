package za.co.tmasethe;

import lombok.extern.java.Log;
import za.co.tmasethe.controller.AircraftFactory;
import za.co.tmasethe.controller.WeatherTower;
import za.co.tmasethe.exception.InvalidCoordinatesException;
import za.co.tmasethe.exception.InvalidFileContentException;
import za.co.tmasethe.model.Aircraft;
import za.co.tmasethe.service.Flyable;
import za.co.tmasethe.util.Md5Checker;

import java.io.*;
import java.security.NoSuchAlgorithmException;

@Log
public class Application {

    public static PrintWriter printWriter;
    private static int numberOfSimulations;
    private static final Md5Checker md5Checker = new Md5Checker();

    private static void checkCoordinates(String longitudeString,
                                         String latitudeString,
                                         String heightString) throws InvalidCoordinatesException {
        int height;
        int latitude;
        int longitude;

        try {
            latitude = Integer.parseInt(latitudeString);
            longitude = Integer.parseInt(longitudeString);
            height = Integer.parseInt(heightString);

            if (latitude < 0) {
                throw new InvalidCoordinatesException("Latitude must be positive");
            } else if (longitude < 0) {
                throw new InvalidCoordinatesException("Longitude must be positive");
            } else if (height < 0) {
                throw new InvalidCoordinatesException("Height must be positive");
            }
        } catch (NumberFormatException numberFormatException) {
            throw new InvalidCoordinatesException(numberFormatException.getMessage() + " is not an integer!");
        }

    }

    private static void checkFileContent(String fileName) throws NoSuchAlgorithmException {
        AircraftFactory aircraftFactory = new AircraftFactory();
        WeatherTower weatherTower = new WeatherTower();

        try {
            int lineNumber = 0;
            String lines;
            String[] aircraftDetails;
            InputStreamReader inputStreamReader;
            BufferedReader bufferedReader;

            try (FileInputStream fileInputStream = new FileInputStream(fileName)) {
                inputStreamReader = new InputStreamReader(fileInputStream);
                bufferedReader = new BufferedReader(inputStreamReader);
            }

            while ((lines = bufferedReader.readLine()) != null) {
                if (lineNumber == 0) {
                    checkLine(lines);
                } else {
                    aircraftDetails = lines.split(" ");

                    if (aircraftDetails.length == 1 && aircraftDetails[0].isEmpty()) {
                            continue ;
                    }
                    checkForFiveParameters(lineNumber, aircraftDetails);
                    validateAircraft(aircraftDetails[0]);
                    checkCoordinates(aircraftDetails[2], aircraftDetails[3], aircraftDetails[4]);
                    Flyable flyable = aircraftFactory.newAircraft(aircraftDetails[0],
                            aircraftDetails[1],
                            Integer.parseInt(aircraftDetails[2]),
                            Integer.parseInt(aircraftDetails[3]),
                            Integer.parseInt(aircraftDetails[4]));
                    // register flyable
                    flyable.registerTower(weatherTower);
                }
                lineNumber++;
            }
        } catch (Exception exception) {
            log.severe(exception.getMessage());
        }
        runSimulation(weatherTower);
        printWriter.close();
    }

    private static void validateAircraft(String aircraft) throws InvalidFileContentException {
        if (!aircraft.equalsIgnoreCase(Aircraft.BALLOON)
                && !aircraft.equalsIgnoreCase(Aircraft.JET_PLANE)
                && !aircraft.equalsIgnoreCase(Aircraft.HELICOPTER)) {
            throw new InvalidFileContentException("Aircraft type is invalid");
        }
    }

    private static void runSimulation(WeatherTower weatherTower) throws NoSuchAlgorithmException {
        for (int i = 0; i < numberOfSimulations; i++) {
            weatherTower.changeWeather();
        }
    }

    private static void checkForFiveParameters(int line, String[] aircraftDetails) throws InvalidFileContentException {
        if (aircraftDetails.length != 5) {
            throw new InvalidFileContentException("5 parameters needed, "
                    + aircraftDetails.length
                    + " found at line "
                    + (line + 1));
        }
    }

    private static void checkLine(String lines) throws InvalidFileContentException {
        try {
            numberOfSimulations = Integer.parseInt(lines);
            if (numberOfSimulations < 0) {
                throw new InvalidFileContentException("First line must be a positive integer.");
            }
        } catch (Exception exception) {
            throw new InvalidFileContentException("First line must be a positive integer.");
        }
    }

    public static void main(String[] args) {
        if (args.length == 1) {
            String fileName = args[0];
            File simulationFile = new File("simulation.txt");

            try {
                printWriter = new PrintWriter(simulationFile);
                checkFileContent(fileName);
            } catch (Exception exception) {
                log.severe(exception.getMessage());
            }
        } else if (args.length > 1) {
            try {
                md5Checker.checkMd5Hashing(args[0], args[1]);
            } catch (Exception exception) {
                log.severe(exception.getMessage());
           }
        } else {
            log.severe("File not found");
        }
    }
}
