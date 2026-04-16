package ua.kpi.ovrmz.lab2.librarycrudwebapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.command.Command;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.validator.RegExp;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service.exception.ServiceException;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.AttributesHolder;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.ErrorsMessages;

import java.io.IOException;

@WebServlet(name = "FrontController", urlPatterns = "/")
public class FrontController extends HttpServlet {
    public static final String REDIRECT = "redirect";
    private static final Logger logger = Logger.getLogger(FrontController.class);

    private CommandHolder commandHolder;

    @Override
    public void init() {
        commandHolder = new CommandHolder();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String commandKey = getMethod(request) + CommandHolder.DELIMITER + getPath(request);
        logger.debug(commandKey);

        Command command = commandHolder.getCommand(commandKey);

        checkIfErrorIsPresent(request);
        executeCommand(request, response, command);
    }

    private void executeCommand(HttpServletRequest request, HttpServletResponse response, Command command) throws IOException {
        String error;
        try {
            String path = command.execute(request, response);
            logger.info(path);

            if (!isRedirected(path)) {
                request.getRequestDispatcher(path).forward(request, response);
            } else {
                request.removeAttribute(AttributesHolder.ERROR_MESSAGE);
            }
            return;

        } catch (ServiceException e) {
            error = e.getMessage();
            request.getSession().setAttribute(AttributesHolder.ERROR_MESSAGE, error);
            logger.error("Service Exception: " + error, e);
        } catch (Exception e) {
            error = ErrorsMessages.NOT_EXCEPTED_ERROR;
            request.getSession().setAttribute(AttributesHolder.ERROR_MESSAGE, error);
            logger.error("System Error", e);
        }

        String regex = "/" + RegExp.NUMBER;
        response.sendRedirect(request.getRequestURI()
                .replaceAll(regex, "")
                .replaceAll("/delete", "") + "?" + AttributesHolder.ERROR_MESSAGE + "=" + error);
        logger.error(error);
    }

    private void checkIfErrorIsPresent(HttpServletRequest request) {
        request.setAttribute(AttributesHolder.ERROR_MESSAGE,
                request.getParameter(AttributesHolder.ERROR_MESSAGE));
    }

    private boolean isRedirected(String path) {
        return REDIRECT.equals(path);
    }

    private String getPath(HttpServletRequest request) {
        String path = request.getRequestURI();
        return path.replaceAll(RegExp.NUMBER, "");
    }

    private String getMethod(HttpServletRequest request) {
        return request.getMethod().toUpperCase();
    }
}