package com.bingo.messager.Config;

/**
 * Created by bingo on 14/12/21.
 */
public class Config {
    public static final String SMS_URI_ALL   = "content://sms/";
    public static final String SMS_URI_INBOX = "content://sms/inbox";
    public static final String SMS_URI_SEND  = "content://sms/sent";
    public static final String SMS_URI_DRAFT = "content://sms/draft";

    public static final int SMS_TYPE_SEND = 1;
    public static final int SMS_TYPE_RECIVED = 2;

}
