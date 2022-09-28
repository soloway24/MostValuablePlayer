package validators.implementations;

import validators.interfaces.FileValidator;
import java.util.List;

public class HandBallFileValidator extends FileValidator {


    public HandBallFileValidator() {
        RECORD_LENGTH = 6;
    }

    @Override
    protected void validateFormatSpecificGameType(List<List<String>> records) {

    }

    @Override
    protected void validateDataSpecificGameType(List<List<String>> records) {

    }
}
