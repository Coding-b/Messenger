package com.bingo.messager.tools;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.provider.ContactsContract;
import android.util.Log;

import com.bingo.messager.Config.Config;
import com.bingo.messager.beans.Message;

/**
 * Created by bingo on 14/12/21.
 */
public class MessageTools {
    /**
     * 获取当前所有短信消息
     * @param context
     * @return
     */
    public static ArrayList<Message> getAllMessages(Context context){
        ArrayList<Message> messageList = new ArrayList<Message>();

        try{
            ContentResolver cr = context.getContentResolver();
            String[] projection = new String[]{"_id, thread_id, address, person,body, date, type, read, status, protocol"};
            Uri uri = Uri.parse(Config.SMS_URI_ALL);
            Cursor cur = cr.query(uri, projection, " 1 = 1 ) group by (thread_id", null, "date desc");

            if (cur.moveToFirst()) {
                String name;
                String phoneNumber;
                String smsbody;
                Date date;
                int read;
                int type;
                int status;
                int protocol;
                long thread_id;
                long id;

                int idColumn = cur.getColumnIndex("_id");
                int nameColumn = cur.getColumnIndex("person");
                int phoneNumberColumn = cur.getColumnIndex("address");
                int smsbodyColumn = cur.getColumnIndex("body");
                int dateColumn = cur.getColumnIndex("date");
                int typeColumn = cur.getColumnIndex("type");
                int readColumn = cur.getColumnIndex("read");
                int statusColumn = cur.getColumnIndex("status");
                int protocolColumn = cur.getColumnIndex("protocol");
                int threadIdColumn = cur.getColumnIndex("thread_id");

                do{
                    name = cur.getString(nameColumn);
                    phoneNumber = cur.getString(phoneNumberColumn);
                    smsbody = cur.getString(smsbodyColumn);

                    type = cur.getInt(typeColumn);
                    status = cur.getInt(statusColumn);
                    thread_id = cur.getLong(threadIdColumn);
                    id = cur.getLong(idColumn);
                    protocol = cur.getInt(protocolColumn);
                    read = cur.getInt(readColumn);
                    date = new Date(Long.parseLong(cur.getString(dateColumn)));

                    Message msg = new Message();
                    msg.setDate(date);
                    msg.setAddress(phoneNumber);

                    msg.setPerson(getPeopleNameFromPerson(context ,phoneNumber));
                    msg.setId(id);
                    msg.setRead(read);
                    msg.setStatus(status);
                    msg.setThread_id(thread_id);
                    msg.setType(type);
                    msg.setProtocol(protocol);

                    if(smsbody == null){
                        msg.setBody("");
                    }
                    else{
                        msg.setBody(smsbody);
                    }

                    messageList.add(msg);
                }while(cur.moveToNext());
            } else {
                messageList.clear();
            }


        } catch(SQLiteException ex) {
            Log.d("SQLiteException in getSmsInPhone", ex.getMessage());
        }
        return messageList;
    }

    /**
     * 获取当前手机号码
     * @param context
     * @return
     */
    public static String getSelfPhoneNum(Context context){
        String phoneNumber = null;
        ContentResolver cr = context.getContentResolver();
        String[] projection = new String[]{"address","date"};
        Uri uri = Uri.parse(Config.SMS_URI_SEND);
        Cursor cur = cr.query(uri, projection, null, null, "date desc");
        if (cur.moveToFirst()) {
            int phoneNumberColumn = cur.getColumnIndex("address");
            phoneNumber = cur.getString(phoneNumberColumn);
        }
        return phoneNumber;
    }

    // 通过address手机号关联Contacts联系人的显示名字
    public static String getPeopleNameFromPerson(Context context ,String address){
        if(address == null || address == ""){
            return "( no address )\n";
        }

        String strPerson = "null";
        String[] projection = new String[] {ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME, ContactsContract.CommonDataKinds.Phone.NUMBER};

        Uri uri_Person = Uri.withAppendedPath(ContactsContract.CommonDataKinds.Phone.CONTENT_FILTER_URI, address);	// address 手机号过滤
        Cursor cursor = context.getContentResolver().query(uri_Person, projection, null, null, null);

        if(cursor.moveToFirst()){
            int index_PeopleName = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
            String strPeopleName = cursor.getString(index_PeopleName);
            strPerson = strPeopleName;
        }
        cursor.close();

        return strPerson;
    }

    public static List<Message> getMessagesFromAddress(Context context ,String address){
        List<Message> messages = new ArrayList<>();
        try{
            ContentResolver cr = context.getContentResolver();
            String[] projection = new String[]{"_id, thread_id, address, person,body, date, type, read, status, protocol"};
            Uri uri = Uri.parse(Config.SMS_URI_ALL);
            Cursor cur = cr.query(uri, projection, "address=?", new String[]{address}, "date asc");

            if (cur.moveToFirst()) {
                String name;
                String phoneNumber;
                String smsbody;
                Date date;
                int read;
                int type;
                int status;
                int protocol;
                long thread_id;
                long id;

                int idColumn = cur.getColumnIndex("_id");
                int nameColumn = cur.getColumnIndex("person");
                int phoneNumberColumn = cur.getColumnIndex("address");
                int smsbodyColumn = cur.getColumnIndex("body");
                int dateColumn = cur.getColumnIndex("date");
                int typeColumn = cur.getColumnIndex("type");
                int readColumn = cur.getColumnIndex("read");
                int statusColumn = cur.getColumnIndex("status");
                int protocolColumn = cur.getColumnIndex("protocol");
                int threadIdColumn = cur.getColumnIndex("thread_id");

                do{
                    name = cur.getString(nameColumn);
                    phoneNumber = cur.getString(phoneNumberColumn);
                    smsbody = cur.getString(smsbodyColumn);

                    type = cur.getInt(typeColumn);
                    status = cur.getInt(statusColumn);
                    thread_id = cur.getLong(threadIdColumn);
                    id = cur.getLong(idColumn);
                    protocol = cur.getInt(protocolColumn);
                    read = cur.getInt(readColumn);
                    date = new Date(Long.parseLong(cur.getString(dateColumn)));

                    Message msg = new Message();
                    msg.setDate(date);
                    msg.setAddress(phoneNumber);

                    msg.setPerson(getPeopleNameFromPerson(context ,phoneNumber));
                    msg.setId(id);
                    msg.setRead(read);
                    msg.setStatus(status);
                    msg.setThread_id(thread_id);
                    msg.setType(type);
                    msg.setProtocol(protocol);

                    if(smsbody == null){
                        msg.setBody("");
                    }
                    else{
                        msg.setBody(smsbody);
                    }

                    messages.add(msg);
                }while(cur.moveToNext());
            } else {
                messages.clear();
            }


        } catch(SQLiteException ex) {
            Log.d("SQLiteException in getSmsInPhone", ex.getMessage());
        }
        return messages;
    }
}
