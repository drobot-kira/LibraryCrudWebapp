package ua.kpi.ovrmz.lab2.librarycrudwebapp.model.dao.exception;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.exception.ApplicationException;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.ErrorsMessages;

public class DaoException extends ApplicationException {
    public DaoException() {
        super(ErrorsMessages.DAO_ERROR);
    }

    public DaoException(Exception cause) {
        super(ErrorsMessages.DAO_ERROR, cause);
    }
}