package ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.command;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Library;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service.LibraryService;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.AttributesHolder;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.PagesHolder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;

public class GetLibraries implements Command {
    private static final int MAX_ROWS = 20;

    private final LibraryService libraryService = LibraryService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String searchQuery = request.getParameter(AttributesHolder.SEARCH);
        List<Library> libraries;

        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            libraries = libraryService.findByName(searchQuery);

            if (libraries.size() > MAX_ROWS) {
                libraries = libraries.subList(0, MAX_ROWS);
            }
        } else {
            libraries = libraryService.findAll(MAX_ROWS, 0);
        }

        request.setAttribute(AttributesHolder.LIBRARIES, libraries);
        request.setAttribute(AttributesHolder.SEARCH, searchQuery);

        return PagesHolder.LIBRARIES;
    }
}