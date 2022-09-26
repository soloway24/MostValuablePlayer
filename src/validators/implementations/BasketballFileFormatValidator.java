package validators.implementations;

import validators.interfaces.FileFormatValidator;
import java.util.List;

public class BasketballFileFormatValidator extends FileFormatValidator {


    public BasketballFileFormatValidator() {
        RECORD_LENGTH = 7;
    }

    @Override
    protected void doSpecificValidation(List<List<String>> records) {

    }
}
