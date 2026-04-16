package ua.kpi.ovrmz.lab2.librarycrudwebapp.utils;

public class PagesHolder {
    public static final String PREFIX = "/WEB-INF/view";

    public static final String ERROR_FOLDER = "/error";
    public static final String PAGE_NOT_FOUND = PREFIX + ERROR_FOLDER + "/pageNotFound.jsp";

    public static final String LIBRARIES = PREFIX + "/library/libraries.jsp";
    public static final String LIBRARY = PREFIX + "/library/library.jsp";

    public static final String BOOKS = PREFIX + "/book/books.jsp";
    public static final String BOOK = PREFIX + "/book/book.jsp";
}