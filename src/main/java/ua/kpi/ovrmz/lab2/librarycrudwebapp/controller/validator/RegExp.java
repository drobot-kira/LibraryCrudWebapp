package ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.validator;

public class RegExp {
    public static final String NUMBER = "/\\d+";
    public static final String LIBRARY_NAME = "^(?=.{1,100}$)(?!\\s)(?!.*\\s$)[a-zA-Zа-яА-ЯіІїЇєЄґҐ0-9\"«»' ]+$";
    public static final String BOOK_TITLE = "^(?=.{1,200}$)[\"«](?!\\s)[a-zA-Zа-яА-ЯіІїЇєЄґҐ0-9' ]+(?<!\\s)[\"»]$";
    public static final String AUTHOR = "^(?=.{1,100}$)[A-ZА-ЯІЇЄҐ][a-zа-яіїєґ]*(?: [A-ZА-ЯІЇЄҐ][a-zа-яіїєґ]*)*$";
}