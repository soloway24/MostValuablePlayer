package main.exceptions;

public class NoDirectoryFoundException extends Exception {

    public NoDirectoryFoundException(String directoryPath) {
        super("No directory with name '" + directoryPath + "' found.");
    }

}
