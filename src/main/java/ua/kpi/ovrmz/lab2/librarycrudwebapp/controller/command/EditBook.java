package ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.command;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.FrontController;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.validator.BookValidator;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Book;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Library;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service.BookService;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service.exception.ServiceException;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.AttributesHolder;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.ErrorsMessages;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.PathsHolder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditBook implements Command {
    private final BookService bookService = BookService.getInstance();
    private final BookValidator bookValidator = new BookValidator();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = buildBook(request);

        if (!bookValidator.validate(book)) {
            throw new ServiceException(ErrorsMessages.TITLE_INVALID);
        }

        bookService.update(book);
        response.sendRedirect(request.getContextPath() + PathsHolder.BOOKS);

        return FrontController.REDIRECT;
    }

    private Book buildBook(HttpServletRequest request) {
        Book book = new Book();

        String idParam = request.getParameter(AttributesHolder.ID);
        if (idParam != null && !idParam.isEmpty()) {
            book.setId(Integer.parseInt(idParam));
        }

        book.setTitle(request.getParameter(AttributesHolder.TITLE));
        book.setAuthor(request.getParameter(AttributesHolder.AUTHOR));

        String libIdParam = request.getParameter(AttributesHolder.LIBRARY_ID);
        if (libIdParam != null && !libIdParam.isEmpty()) {
            Library library = new Library();
            library.setId(Integer.parseInt(libIdParam));
            book.setLibrary(library);
        }

        return book;
    }
}