package com.example.server.Results;

import java.lang.reflect.Method;

/**
 * Created by fryti on 1/29/2018.
 */


public class GenericCommand implements ICommand {
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

    //fixme need to implement execute
    public Result execute() {

        /*Class<?>[] paramTypes = new Class<?>[_paramValues.length];

        /*
        Class<?>[] paramTypes = new Class<?>[_paramValues.length];
 Integration
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
 origin/client_views_presenters_guifacade_clientmodel

        return null;
    }*/
        Result result = null;
        try {
            Class<?> receiverClass = Class.forName(_className);
            Method method = receiverClass.getMethod(_methodName, _paramTypes);
            Object receiver = receiverClass.newInstance();
            Object obj = method.invoke(receiver, _paramValues);
            if (obj.getClass() == LoginResult.class)
                result = (LoginResult) obj;
            else if (obj.getClass() == RegisterResult.class)
                result = (RegisterResult) obj;
            else if (obj.getClass() == GetGameListResult.class)
                result = (GetGameListResult) obj;
            else if (obj.getClass() == CreateGameResult.class)
                result = (CreateGameResult) obj;
            else if (obj.getClass() == JoinGameResult.class)
                result = (JoinGameResult) obj;
            else
                //should never get here
                assert result == null;

            //result object created and returned by services
        }
        catch (ClassNotFoundException ex) {
            result.setErrorMessage(ex.getMessage());
            result.setErrorType("ClassNotFoundException");
            result.setSuccess(false);
            result.setClientCommands(null);
        }
        finally {
            return result;
        }


    }
}

