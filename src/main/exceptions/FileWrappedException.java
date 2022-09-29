package main.exceptions;

public class FileWrappedException extends Exception {

    public FileWrappedException(String msg, Exception e) {
        super(msg + "\n" + e.getMessage());
        setStackTrace(e.getStackTrace());
    }

}
