package ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.validator;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Book;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.AttributesHolder;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.ErrorsMessages;

import java.util.regex.Pattern;

public class BookValidator implements Validator<Book> {
    private final Pattern titlePattern;
    private final Pattern authorPattern;

    public BookValidator() {
                titlePattern = Pattern.compile(RegExp.BOOK_TITLE);
        authorPattern = Pattern.compile(RegExp.AUTHOR);
    }

    @Override
    public boolean validate(Book book, Errors errors) {
        if (book != null) {
                        if (book.getTitle() == null || !titlePattern.matcher(book.getTitle()).matches()) {
                reject(errors, AttributesHolder.TITLE, ErrorsMessages.TITLE_INVALID);
            }

                        if (book.getAuthor() == null || !authorPattern.matcher(book.getAuthor()).matches()) {
                reject(errors, AttributesHolder.AUTHOR, ErrorsMessages.AUTHOR_INVALID);
            }

                        if (book.getLibrary() == null || book.getLibrary().getId() <= 0) {
                reject(errors, AttributesHolder.LIBRARY_ID, ErrorsMessages.LIBRARY_ID_INVALID);
            }
        } else {
            reject(errors, AttributesHolder.BOOK, ErrorsMessages.INVALID);
        }
        return !errors.hasError();
    }

    @Override
    public boolean validate(Book book) {
        if (book == null) return false;

        boolean isTitleValid = book.getTitle() != null && titlePattern.matcher(book.getTitle()).matches();
        boolean isAuthorValid = book.getAuthor() != null && authorPattern.matcher(book.getAuthor()).matches();
        boolean isLibraryValid = book.getLibrary() != null && book.getLibrary().getId() > 0;

        return isTitleValid && isAuthorValid && isLibraryValid;
    }

    private void reject(Errors errors, String attribute, String message) {
        errors.addMessage(attribute, message);
        errors.setResult(false);
    }
}