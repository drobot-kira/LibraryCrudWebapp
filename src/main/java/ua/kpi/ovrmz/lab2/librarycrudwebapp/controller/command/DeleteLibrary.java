package ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.command;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.FrontController;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service.LibraryService;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service.exception.ServiceException;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.ErrorsMessages;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.PathsHolder;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteLibrary implements Command {
    private final LibraryService libraryService = LibraryService.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = request.getRequestURI();

        try {
            int id = Integer.parseInt(path.substring(path.lastIndexOf("/") + 1));
            libraryService.delete(id);
        } catch (NumberFormatException e) {
            throw new ServiceException(ErrorsMessages.INVALID);
        }

        response.sendRedirect(request.getContextPath() + PathsHolder.LIBRARIES);

        return FrontController.REDIRECT;
    }
}