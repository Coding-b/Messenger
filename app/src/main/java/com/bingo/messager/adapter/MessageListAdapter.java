package com.bingo.messager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bingo.messager.R;
import com.bingo.messager.beans.Message;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by bingo on 14/12/21.
 */
public class MessageListAdapter extends BaseAdapter{

    private List<Message> messageLists = new ArrayList<Message>();
    private Context context;
    private LayoutInflater inflater;

    private int year;
    private int month;
    private int day;

    public MessageListAdapter(List<Message> messageLists ,Context context) {
        this.messageLists = messageLists;
        this.context = context;
        this.inflater = LayoutInflater.from(context);

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTimeInMillis(System.currentTimeMillis());

        year = calendar1.YEAR;
        month = calendar1.MONTH;
        day = calendar1.DAY_OF_MONTH;
    }

    @Override
    public int getCount() {
        return messageLists.size();
    }

    @Override
    public Object getItem(int position) {
        return messageLists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HolderItem messageItem;
        if(convertView == null){
            convertView = inflater.inflate(R.layout.message_item,null);
            messageItem = new HolderItem();
            messageItem.messagePersonImage = (CircleImageView)convertView.findViewById(R.id.message_personImage);
            messageItem.messagePerson = (TextView)convertView.findViewById(R.id.message_person);
            messageItem.messageTime = (TextView)convertView.findViewById(R.id.message_time);
            messageItem.messageContent = (TextView)convertView.findViewById(R.id.message_content);
            convertView.setTag(messageItem);
        }else{
            messageItem = (HolderItem)convertView.getTag();
        }

        Message msg = messageLists.get(position);
        if(msg.getPerson() == null || msg.getPerson().equals("null")){
            messageItem.messagePerson.setText(msg.getAddress());
        }else{
            messageItem.messagePerson.setText(msg.getPerson());
        }

        messageItem.messageContent.setText(msg.getBody());
        messageItem.messageTime.setText(showTime(msg.getDate()));

        return convertView;
    }

    private String showTime(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if(year == calendar.YEAR && month == calendar.MONTH && day == calendar.DAY_OF_MONTH){
            return calendar.HOUR + ":" + calendar.MINUTE;
        }else{
            return calendar.YEAR + "-" + calendar.MONTH + "-" + calendar.DAY_OF_MONTH;
        }
    }

    class HolderItem{
        TextView messagePerson;
        CircleImageView messagePersonImage;
        TextView messageTime;
        TextView messageContent;
    }
}
