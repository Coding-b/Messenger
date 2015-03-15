package com.bingo.messager.beans;

import java.util.Date;

/**
 * Created by bingo on 14/12/21.
 */
public class Message {
    private long id;//短信序号，如100
    private long thread_id;//对话的序号，如100，与同一个手机号互发的短信，其序号是相同的
    private String person;//发件人，如果发件人在通讯录中则为具体姓名，陌生人为null
    private String address;//发件人地址，即手机号，如+8613811810000
    private Date date;//日期，long型，如1256539465022，可以对日期显示格式进行设置
    private int read;//是否阅读0未读，1已读
    private int protocol;//协议0SMS_RPOTO短信，1MMS_PROTO彩信
    private int status;//短信状态-1接收，0complete,64pending,128failed
    private int type;//短信类型1是接收到的，2是已发出
    private String body;//短信具体内容
    private String service_center;//短信服务中心号码编号
    private Contact contact;

    public void setContact(Contact contact){
        this.contact = contact;
    }
    public Contact getContact(){
        return contact;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public long getThread_id() {
        return thread_id;
    }
    public void setThread_id(long thread_id) {
        this.thread_id = thread_id;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public int getRead() {
        return read;
    }
    public void setRead(int read) {
        this.read = read;
    }
    public int getProtocol() {
        return protocol;
    }
    public void setProtocol(int protocol) {
        this.protocol = protocol;
    }
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public int getType() {
        return type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public String getService_center() {
        return service_center;
    }
    public void setService_center(String service_center) {
        this.service_center = service_center;
    }
    public String getPerson() {
        return person;
    }
    public void setPerson(String person) {
        this.person = person;
    }
}
