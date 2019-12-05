package main;

public class ERROR_LOG_MESSAGES {
    private static String ERROR_LOG_STRING = "ERROR LOG\n";

    public void LOG_ERROR(String e)
    {
        ERROR_LOG_STRING += e;
    }

    public static String getErrorLogString() {
        return ERROR_LOG_STRING;
    }
}
