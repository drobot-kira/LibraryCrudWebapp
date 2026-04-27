package ua.kpi.ovrmz.lab2.librarycrudwebapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.command.Command;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.validator.RegExp;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.exception.ApplicationException;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service.exception.ServiceException;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.AttributesHolder;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.ErrorsMessages;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.PagesHolder;

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

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();

        if (uri.contains(".css")){
            getServletContext().getNamedDispatcher("default").forward(request, response);
            return;
        }

        String path = uri.substring(contextPath.length())
                .replaceAll(RegExp.NUMBER, "")
                .replaceAll("/$", "");

        String commandKey = getMethod(request) + ":" + path;
        Command command = commandHolder.getCommand(commandKey);

        if (command == null) {
            request.getRequestDispatcher("/WEB-INF/view/error/pageNotFound.jsp").forward(request, response);
            return;
        }

        executeCommand(request, response, command);
    }

    private void executeCommand(HttpServletRequest request, HttpServletResponse response, Command command)
            throws ServletException, IOException {
        try {
            String path = command.execute(request, response);
            if (!isRedirected(path)) {
                request.getRequestDispatcher(path).forward(request, response);
            }
        } catch (ApplicationException e) {
            logger.error("Application error: " + e.getMessageKey(), e);
            showError(request, response, e.getMessageKey());
        } catch (Exception e) {
            logger.error("System error", e);
            showError(request, response, ErrorsMessages.NOT_EXCEPTED_ERROR);
        }
    }

    private void showError(HttpServletRequest request, HttpServletResponse response, String message)
            throws ServletException, IOException {
        request.setAttribute(AttributesHolder.ERROR_MESSAGE, message);
        String uri = request.getRequestURI();
        String forwardPath = uri.contains("book") ? PagesHolder.BOOK : PagesHolder.LIBRARY;
        request.getRequestDispatcher(forwardPath).forward(request, response);
    }


    private boolean isRedirected(String path) {
        return REDIRECT.equals(path);
    }


    private String getMethod(HttpServletRequest request) {
        return request.getMethod().toUpperCase();
    }
}