package ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.validator;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Library;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.AttributesHolder;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.ErrorsMessages;

import java.time.Year;
import java.util.regex.Pattern;

public class LibraryValidator implements Validator<Library> {
    private final Pattern namePattern;

    public LibraryValidator() {
        namePattern = Pattern.compile(RegExp.LIBRARY_NAME);
    }

    @Override
    public boolean validate(Library library, Errors errors) {
        if (library != null) {

            if (library.getName() == null || !namePattern.matcher(library.getName()).matches()) {
                reject(errors, AttributesHolder.NAME, ErrorsMessages.NAME_INVALID);
            }

            if (library.getFoundationYear() < 0 || library.getFoundationYear() > Year.now().getValue()) {
                reject(errors, AttributesHolder.FOUNDATION_YEAR, ErrorsMessages.YEAR_INVALID);
            }
        } else {
            reject(errors, AttributesHolder.LIBRARY, ErrorsMessages.INVALID);
        }
        return !errors.hasError();
    }

    @Override
    public boolean validate(Library library) {
        if (library == null) return false;
        boolean isNameValid = library.getName() != null && namePattern.matcher(library.getName()).matches();
        boolean isYearValid = library.getFoundationYear() >= 0 && library.getFoundationYear() <= Year.now().getValue();
        return isNameValid && isYearValid;
    }

    private void reject(Errors errors, String attribute, String message) {
        errors.addMessage(attribute, message);
    }
}