package ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Book;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service.BookService;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.AttributesHolder;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.PagesHolder;

import java.util.List;

public class GetBooks implements Command {
    private static final int MAX_ROWS = 20;

    private final BookService bookService = BookService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String searchQuery = request.getParameter(AttributesHolder.SEARCH);
        List<Book> books;

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            books = bookService.findByTitleOrAuthorContaining(searchQuery);

            if (books.size() > MAX_ROWS) {
                books = books.subList(0, MAX_ROWS);
            }
        } else {
            books = bookService.findAll(MAX_ROWS, 0);
        }

        request.setAttribute(AttributesHolder.BOOKS, books);
        request.setAttribute(AttributesHolder.SEARCH, searchQuery);

        return PagesHolder.BOOKS;
    }
}