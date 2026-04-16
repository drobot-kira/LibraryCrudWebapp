package ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.command;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Library;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service.LibraryService;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.AttributesHolder;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.PagesHolder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GetLibrary implements Command {
    private final LibraryService libraryService = LibraryService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String path = request.getRequestURI();

        if (path.contains("edit")) {
            int id = Integer.parseInt(path.substring(path.lastIndexOf("/") + 1));
            Library library = libraryService.findById(id).orElse(null);

            request.setAttribute(AttributesHolder.LIBRARY, library);
            request.setAttribute(AttributesHolder.NEW_MODE, false);
        } else {
            request.setAttribute(AttributesHolder.NEW_MODE, true);
        }

        return PagesHolder.LIBRARY;
    }
}