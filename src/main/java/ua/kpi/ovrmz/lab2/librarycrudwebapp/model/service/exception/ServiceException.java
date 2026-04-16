package ua.kpi.ovrmz.lab2.librarycrudwebapp.model.service.exception;

import ua.kpi.ovrmz.lab2.librarycrudwebapp.exception.ApplicationException;
import ua.kpi.ovrmz.lab2.librarycrudwebapp.utils.ErrorsMessages;

public class ServiceException extends ApplicationException {
    public ServiceException() {
        super(ErrorsMessages.SERVICE_ERROR);
    }

    public ServiceException(Exception cause) {
        super(ErrorsMessages.SERVICE_ERROR, cause);
    }

    public ServiceException(String messageKey) {
        super(messageKey);
    }

    @Override
    public ServiceException addLogMessage(String logMessage) {
        super.addLogMessage(logMessage);
        return this;
    }
}