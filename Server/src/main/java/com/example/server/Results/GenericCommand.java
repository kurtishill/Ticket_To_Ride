package com.example.server.Results;

import java.io.Serializable;
import java.lang.reflect.Method;

/**
 * Created by fryti on 1/29/2018.
 */

public class GenericCommand implements ICommand, Serializable {
    private String _className;
    private String _methodName;
    private Class<?>[] _paramTypes;
    private Object[] _paramValues;

    public GenericCommand(String className, String methodName,
                          Class<?>[] paramTypes, Object[] paramValues) {
        _className = className;
        _methodName = methodName;
        _paramTypes = paramTypes;
        _paramValues = paramValues;

    }

    public Object execute() {
        Result result = new Result();
        try {
            Class<?> receiverClass = Class.forName(_className);
            Method method = receiverClass.getDeclaredMethod(_methodName, _paramTypes);
            Object obj = method.invoke(receiverClass.newInstance(), _paramValues);
            return obj;
        }
        catch (Exception ex) {
            result.setErrorMessage(ex.getMessage());
            result.setErrorType("Exception");
            result.setSuccess(false);
            result.setClientCommands(null);
            return result;
        }
    }
}

