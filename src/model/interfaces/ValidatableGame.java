package model.interfaces;

import validators.interfaces.FileValidator;

public interface ValidatableGame {

    FileValidator getFileFormatValidator();
}
