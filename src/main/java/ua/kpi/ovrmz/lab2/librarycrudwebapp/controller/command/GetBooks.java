package ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.command;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Book;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service.BookService;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.AttributesHolder;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.PagesHolder;

import java.util.List;

public class GetBooks implements Command {
    private static final int MAX_ROWS = 5;
    private final BookService bookService = BookService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String searchQuery = request.getParameter(AttributesHolder.SEARCH);
        List<Book> books;

        int page = 1;
        String pageParam = request.getParameter("page");
        if (pageParam != null && !pageParam.isEmpty()) {
            try {
                page = Integer.parseInt(pageParam);
            } catch (NumberFormatException e) {
                page = 1;
            }
        }

        int offset = (page - 1) * MAX_ROWS;

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            books = bookService.findByTitleOrAuthorContaining(searchQuery);

            request.setAttribute("noOfPages", 1);
        } else {
            books = bookService.findAll(MAX_ROWS, offset);

            long totalRecords = bookService.count();
            int noOfPages = (int) Math.ceil((double) totalRecords / MAX_ROWS);
            request.setAttribute("noOfPages", noOfPages);
        }

        request.setAttribute(AttributesHolder.BOOKS, books);
        request.setAttribute(AttributesHolder.SEARCH, searchQuery);
        request.setAttribute("currentPage", page);

        return PagesHolder.BOOKS;
    }
}