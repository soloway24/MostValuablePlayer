package main.model.interfaces;

import main.validators.abstractions.FileValidator;

public interface ValidatableGame {

    FileValidator getFileFormatValidator();
}
