package ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.validator;

public class RegExp {
    public static final String NUMBER = "/\\d+";
    public static final String LIBRARY_NAME = "^(?!\\s)(?!.*\\s$)[a-zA-Zа-яА-ЯіІїЇєЄґҐ0-9\"«»' ]+$";
    public static final String BOOK_TITLE = "^[\"«](?!\\s)[a-zA-Zа-яА-ЯіІїЇєЄґҐ0-9' ]+(?<!\\s)[\"»]$";
    public static final String AUTHOR = "^[A-ZА-ЯІЇЄҐ][a-zа-яіїєґ]*(?: [A-ZА-ЯІЇЄҐ][a-zа-яіїєґ]*)*$";
}
