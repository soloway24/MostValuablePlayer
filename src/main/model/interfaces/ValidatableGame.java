package main.model.interfaces;

import main.validators.interfaces.FileValidator;

public interface ValidatableGame {

    FileValidator getFileFormatValidator();
}
