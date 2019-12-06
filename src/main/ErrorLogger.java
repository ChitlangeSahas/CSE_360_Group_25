package main;

public class ErrorLogger {
    private static String ERROR_LOG_STRING = "+-+-+-+-+-+-+-+-+- ERROR LOG +-+-+-+-+-+-+-+-+-+-+-+\n";

    public void LOG_ERROR(String e)
    {
        ERROR_LOG_STRING += e;
        ERROR_LOG_STRING += "\n";
    }

    public static String getErrorLogString() {
        return ERROR_LOG_STRING;
    }
}
