package com.au.demo.exception;

public class BusinessException extends Exception{
    final public static String USER_NAME_CODE="1000";
    final public static String USER_NAME_MESSAGE="User name is empty";

    final public static String USER_EMAIL_CODE="2000";
    final public static String USER_EMAIL_MESSAGE="Invalid email";

    final public static String USER_EMAIL_EXISTS_CODE="7000";
    final public static String USER_EMAIL_EXISTS_MESSAGE="Given email is already registered, please use different email";

    final public static String USER_MONTHLY_SALARY_CODE="3000";
    final public static String USER_MONTHLY_SALARY_MESSAGE="Invalid monthly salary";

    final public static String USER_MONTHLY_EXPENSE_CODE="4000";
    final public static String USER_MONTHLY_EXPENSE_MESSAGE="Invalid monthly expense";

    final public static String ACCOUNT_MIN_BALANCE_CODE="5000";
    final public static String ACCOUNT_MIN_BALANCE_MESSAGE="Account can not be open due to minimum savings eligibility";

    final public static String NO_ACCOUNT_FOUND_CODE="6000";
    final public static String NO_ACCOUNT_FOUND_MESSAGE="No accounts found for given user";

    private String errorCode;
    private String errorMessage;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public BusinessException(String code, String message){
        super(message);
        this.errorCode=code;
        this.errorMessage=message;
    }
}
