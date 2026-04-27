package ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.command;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Library;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service.LibraryService;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.AttributesHolder;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.PagesHolder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class GetLibraries implements Command {
    private static final int MAX_ROWS = 5;

    private final LibraryService libraryService = LibraryService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String searchQuery = request.getParameter(AttributesHolder.SEARCH);
        List<Library> libraries;

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
            libraries = libraryService.findByName(searchQuery);
            request.setAttribute("noOfPages", 1);
        } else {
            libraries = libraryService.findAll(MAX_ROWS, offset);

            long totalRecords = libraryService.count();
            int noOfPages = (int) Math.ceil((double) totalRecords / MAX_ROWS);
            request.setAttribute("noOfPages", noOfPages);
        }

        request.setAttribute(AttributesHolder.LIBRARIES, libraries);
        request.setAttribute(AttributesHolder.SEARCH, searchQuery);
        request.setAttribute("currentPage", page);

        return PagesHolder.LIBRARIES;
    }
}