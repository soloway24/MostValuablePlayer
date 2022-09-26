package model.interfaces;

import validators.interfaces.FileFormatValidator;

public interface ValidatableGame {

    FileFormatValidator getFileFormatValidator();
}
