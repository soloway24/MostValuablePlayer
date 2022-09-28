package validators.implementations;

import validators.interfaces.FileValidator;
import java.util.List;

public class BasketballFileValidator extends FileValidator {


    public BasketballFileValidator() {
        RECORD_LENGTH = 7;
    }

    @Override
    protected void validateFormatSpecificGameType(List<List<String>> records) {

    }

    @Override
    protected void validateDataSpecificGameType(List<List<String>> records) {

    }


}
