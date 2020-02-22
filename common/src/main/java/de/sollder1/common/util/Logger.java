package de.sollder1.common.util;

/**
 * Bietet einen Deaktivierbare ausgabe an.
 * Im Rahmen diese Projektes ist bloß die ausgabe auf die Console relevant.
 */
public class Logger {

    private static final boolean LOG_INFORMATION =  false;
    private static final boolean LOG_WARNING =      false;
    private static final boolean LOG_ERROR =        false;

    private enum LoggingType{
        CONSOLE
    }

    private static final LoggingType logtype = LoggingType.CONSOLE;

    //Logs Information
    public static synchronized void logI(String message){

        if(LOG_INFORMATION){
            logger("*****//INFORMATION*****\n"
                    + message
                    +    "\n*****INFORMATION\\\\*****");
        }

    }

    //Logs Warning
    public static synchronized void logW(String message){
        if(LOG_WARNING){
            logger("*****//WARNING*****\n"
                    + message
                    +    "\n*****WARNING\\\\*****");
        }
    }

    //Logs Error
    public static synchronized void logE(String message){
        if(LOG_ERROR){
            logger("*****//ERROR*****\n"
                    + message
                    +    "\n*****ERROR\\\\*****");
        }
    }

    //Logs Entire Stacktrace
    public static synchronized void logE(Throwable t){
        if(LOG_ERROR){

            logger("*****//ERROR_STACKTRACE*****");
            t.printStackTrace();
            logger("*****ERROR_STACKTRACE\\\\*****");
        }
    }


    private static void logger(String msg){

        switch (logtype){

            case CONSOLE: System.out.println(msg);

        }

    }


}
