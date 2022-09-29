package main.utils;

import main.exceptions.IncorrectFileDataException;

public class UtilMethods {

    public static Integer tryParseInt(String text) throws IncorrectFileDataException {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            throw new IncorrectFileDataException("Provided field value '" + text + "' cannot be parsed to int.");
        }
    }
}
