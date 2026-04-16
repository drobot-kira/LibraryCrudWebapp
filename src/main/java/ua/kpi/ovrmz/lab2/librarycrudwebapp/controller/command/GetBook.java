package ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.command;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Book;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Library;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service.BookService;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service.LibraryService;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.AttributesHolder;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.PagesHolder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class GetBook implements Command {
    private final BookService bookService = BookService.getInstance();
    private final LibraryService libraryService = LibraryService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getRequestURI();

        if (path.contains("edit")) {
            int id = Integer.parseInt(path.substring(path.lastIndexOf("/") + 1));
            Book book = bookService.findById(id).orElse(null);

            request.setAttribute(AttributesHolder.BOOK, book);
            request.setAttribute(AttributesHolder.NEW_MODE, false);
        } else {
            request.setAttribute(AttributesHolder.NEW_MODE, true);
        }

        List<Library> libraries = libraryService.findAll(1000, 0);
        request.setAttribute(AttributesHolder.LIBRARIES, libraries);

        return PagesHolder.BOOK;
    }
}