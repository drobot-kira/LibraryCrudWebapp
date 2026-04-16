package ua.kpi.ovrmz.lab2.librarycrudwebapp.controller;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.command.*;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.PagesHolder;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.PathsHolder;

import java.util.HashMap;
import java.util.Map;

public class CommandHolder {
    public static final String DELIMITER = ":";
    private static final String GET = "GET" + DELIMITER;
    private static final String POST = "POST" + DELIMITER;

    private final Map<String, Command> commands = new HashMap<>();

    public CommandHolder() {
        initCommands();
    }

    private void initCommands() {
        commands.put(GET + PathsHolder.LIBRARIES, new GetLibraries());
        commands.put(GET + PathsHolder.ADD_LIBRARY, new GetLibrary());
        commands.put(GET + PathsHolder.EDIT_LIBRARY, new GetLibrary());

        commands.put(POST + PathsHolder.ADD_LIBRARY, new AddLibrary());
        commands.put(POST + PathsHolder.EDIT_LIBRARY, new EditLibrary());
        commands.put(POST + PathsHolder.DELETE_LIBRARY, new DeleteLibrary());

        commands.put(GET + PathsHolder.BOOKS, new GetBooks());
        commands.put(GET + PathsHolder.ADD_BOOK, new GetBook());
        commands.put(GET + PathsHolder.EDIT_BOOK, new GetBook());

        commands.put(POST + PathsHolder.ADD_BOOK, new AddBook());
        commands.put(POST + PathsHolder.EDIT_BOOK, new EditBook());
        commands.put(POST + PathsHolder.DELETE_BOOK, new DeleteBook());
    }

    Command getCommand(String commandKey) {
        return commands.getOrDefault(commandKey, (req, resp) -> PagesHolder.PAGE_NOT_FOUND);
    }
}

