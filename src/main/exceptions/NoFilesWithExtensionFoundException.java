package main.exceptions;

public class NoFilesWithExtensionFoundException extends Exception {

    public NoFilesWithExtensionFoundException(String directoryPath, String extension) {
        super("No files with extension '" + extension + "' were found in the directory '" + directoryPath + "'.");
    }

}
