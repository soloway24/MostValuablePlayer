package validators.implementations;

import validators.interfaces.FileFormatValidator;
import java.util.List;

public class HandBallFileFormatValidator extends FileFormatValidator {


    public HandBallFileFormatValidator() {
        RECORD_LENGTH = 6;
    }

    @Override
    protected void doSpecificValidation(List<List<String>> records) {

    }
}
