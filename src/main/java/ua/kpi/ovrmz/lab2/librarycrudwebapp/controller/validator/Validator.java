package ua.kpi.ovrmz.lab2.librarycrudwebapp.controller.validator;

public interface Validator<E> {
    boolean validate(E t, Errors errors);
    boolean validate(E t);
}
