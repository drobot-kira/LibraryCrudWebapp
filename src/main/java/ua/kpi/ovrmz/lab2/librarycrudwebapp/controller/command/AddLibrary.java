package ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.command;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.FrontController;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.validator.LibraryValidator;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.entity.Library;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service.LibraryService;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service.exception.ServiceException;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.AttributesHolder;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.ErrorsMessages;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.PathsHolder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddLibrary implements Command {
    private final LibraryService libraryService = LibraryService.getInstance();
    private final LibraryValidator libraryValidator = new LibraryValidator();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Library library = buildLibrary(request);

        if (!libraryValidator.validate(library)) {
            throw new ServiceException(ErrorsMessages.NAME_INVALID);
        }

        libraryService.create(library);
        response.sendRedirect(request.getContextPath() + PathsHolder.LIBRARIES);

        return FrontController.REDIRECT;
    }

    private Library buildLibrary(HttpServletRequest request) {
        Library library = new Library();
        library.setName(request.getParameter(AttributesHolder.NAME));

        try {
            int year = Integer.parseInt(request.getParameter(AttributesHolder.FOUNDATION_YEAR));
            library.setFoundationYear(year);
        } catch (NumberFormatException e) {
            throw new ServiceException(ErrorsMessages.YEAR_INVALID);
        }

        return library;
    }
}