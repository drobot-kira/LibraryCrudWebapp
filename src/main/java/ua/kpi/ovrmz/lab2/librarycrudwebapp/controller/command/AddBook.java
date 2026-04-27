package ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.command;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.FrontController;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.validator.BookValidator;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.validator.Errors;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Book;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Library;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service.BookService;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service.LibraryService;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.AttributesHolder;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.PagesHolder;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.PathsHolder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddBook implements Command {
    private final BookService bookService = BookService.getInstance();
    private final LibraryService libraryService = LibraryService.getInstance();
    private final BookValidator bookValidator = new BookValidator();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = buildBook(request);
        Errors errors = new Errors();

        bookValidator.validate(book, errors);

        if (errors.hasError()) {
            request.setAttribute(AttributesHolder.BOOK, book);
            request.setAttribute(AttributesHolder.ERRORS, errors.getMessages());
            request.setAttribute(AttributesHolder.LIBRARIES, libraryService.findAll(1000, 0));
            request.setAttribute(AttributesHolder.NEW_MODE, true);

            return PagesHolder.BOOK;
        }

        bookService.create(book);
        response.sendRedirect(request.getContextPath() + PathsHolder.BOOKS);

        return FrontController.REDIRECT;
    }

    private Book buildBook(HttpServletRequest request) {
        Book book = new Book();
        book.setTitle(request.getParameter(AttributesHolder.TITLE));
        book.setAuthor(request.getParameter(AttributesHolder.AUTHOR));

        try {
            String libIdParam = request.getParameter(AttributesHolder.LIBRARY_ID);
            if (libIdParam != null && !libIdParam.isEmpty()) {
                int libraryId = Integer.parseInt(libIdParam);
                Library library = new Library();
                library.setId(libraryId);
                book.setLibrary(library);
            }
        } catch (NumberFormatException e) {
        }

        return book;
    }
}