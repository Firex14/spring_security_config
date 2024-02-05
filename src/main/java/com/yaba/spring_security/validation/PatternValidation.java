package com.yaba.spring_security.validation;

public class PatternValidation {
    public static final String EMAIL = "^(?=.{1,256}$)(?=.{1,64}@.{1,255}$)^[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*@([a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z]{2,}$";
    public static final String PHONE_NUMBER = "^(?:\\+1\\d{10}|\\d{10})$|^(?:\\+221\\d{9}|\\d{9})$";
    public static final String PASSWORD = "^[a-zA-Z0-9@/#-~_\\?!.]{4,255}$";

    public static final String DATE = "yyyy-MM-dd HH:mm:ss";
    public static final String CONFIRMATION_CODE = "^[0-9]{6}$";
}
