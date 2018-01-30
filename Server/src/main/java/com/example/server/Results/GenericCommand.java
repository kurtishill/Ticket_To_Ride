package com.example.server.Results;

import java.lang.reflect.Method;

/**
 * Created by fryti on 1/29/2018.
 */

public class GenericCommand {
    private String _className;
    private String _methodName;

    private Object[] _paramValues;

    public GenericCommand(String className, String methodName,
                          Object[] paramValues) {
        _className = className;
        _methodName = methodName;

        _paramValues = paramValues;

    }

    //fixme need to implement execute
    public Result execute() {
        /*
        Class<?>[] paramTypes = new Class<?>[_paramValues.length];
        for(int i=0; i< _paramValues.length; i++){
            paramTypes[i]=_paramValues[i].getClass();
        }
        try {
            Class<?> receiverClass = Class.forName(_className);
            Object receiver = StringProcessor.instance();
            Method method = receiverClass.getMethod(_methodName, paramTypes);
            return (String) method.invoke(receiver, _paramValues);
        }
        catch (Exception e) {
            e.printStackTrace();
            return "Error: Exception Thrown";
        }
    }
    */
        return null;
    }
}

