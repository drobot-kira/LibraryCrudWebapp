package ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.command;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.FrontController;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.validator.Errors;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.validator.LibraryValidator;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Library;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service.LibraryService;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.AttributesHolder;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.PagesHolder;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.PathsHolder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditLibrary implements Command {
    private final LibraryService libraryService = LibraryService.getInstance();
    private final LibraryValidator libraryValidator = new LibraryValidator();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Library library = buildLibrary(request);
        Errors errors = new Errors();

        libraryValidator.validate(library, errors);

        if (errors.hasError()) {
            request.setAttribute(AttributesHolder.LIBRARY, library);
            request.setAttribute(AttributesHolder.ERRORS, errors.getMessages());
            request.setAttribute(AttributesHolder.NEW_MODE, false);

            return PagesHolder.LIBRARY;
        }

        libraryService.update(library);
        response.sendRedirect(request.getContextPath() + PathsHolder.LIBRARIES);

        return FrontController.REDIRECT;
    }

    private Library buildLibrary(HttpServletRequest request) {
        Library library = new Library();

        try {
            String idStr = request.getParameter(AttributesHolder.ID);
            if (idStr != null && !idStr.isEmpty()) {
                library.setId(Integer.parseInt(idStr));
            }

            library.setName(request.getParameter(AttributesHolder.NAME));

            String yearStr = request.getParameter(AttributesHolder.FOUNDATION_YEAR);
            if (yearStr != null && !yearStr.trim().isEmpty()) {
                library.setFoundationYear(Integer.parseInt(yearStr));
            }
        } catch (NumberFormatException e) {
        }

        return library;
    }
}