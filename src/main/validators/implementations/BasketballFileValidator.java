package main.validators.implementations;

import main.exceptions.IncorrectFileDataException;
import main.utils.FileFormatConstants;
import main.utils.UtilMethods;
import main.validators.interfaces.FileValidator;

import java.util.List;

public class BasketballFileValidator extends FileValidator {

    public BasketballFileValidator() {
        RECORD_LENGTH = 7;
    }

    @Override
    protected void validateDataGameTypeSpecific(List<List<String>> records) throws IncorrectFileDataException {
        validatePointFields(records);
    }

    @Override
    protected void validatePointFields(List<List<String>> records) throws IncorrectFileDataException {
        for(List<String> record : records) {

            int scored = UtilMethods.tryParseInt(record.get(FileFormatConstants.BASKETBALL_SCORED_POINTS_INDEX));
            int rebounds = UtilMethods.tryParseInt(record.get(FileFormatConstants.BASKETBALL_SCORED_POINTS_INDEX));
            int assists = UtilMethods.tryParseInt(record.get(FileFormatConstants.BASKETBALL_SCORED_POINTS_INDEX));

            if(scored < 0 || rebounds < 0 || assists < 0)
                throw new IncorrectFileDataException("No negative values allowed for point fields.");
        }
    }

}
