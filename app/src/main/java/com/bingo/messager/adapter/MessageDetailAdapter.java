package com.bingo.messager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bingo.messager.Config.Config;
import com.bingo.messager.R;
import com.bingo.messager.beans.Message;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by bingo on 14/12/21.
 */
public class MessageDetailAdapter extends BaseAdapter{
    private List<Message> messages = new ArrayList<Message>();
    private Context context;
    private LayoutInflater inflater;


    public MessageDetailAdapter(List<Message> messages, Context context) {
        this.messages = messages;
        this.context = context;

        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Override
    public Object getItem(int position) {
        return messages.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message msg = messages.get(position);
        HolderView holderView = null;
        if(convertView == null){
            if(this.getItemViewType(position) == 0){
                convertView = inflater.inflate(R.layout.message_item_he,null);
            }else{
                convertView = inflater.inflate(R.layout.message_item_me,null);
            }
            holderView = new HolderView();
            holderView.msgContent = (TextView)convertView.findViewById(R.id.message_item_content);
            holderView.msgTime = (TextView)convertView.findViewById(R.id.message_item_time);

            convertView.setTag(holderView);
        }else{
            holderView = (HolderView)convertView.getTag();
        }

        holderView.msgContent.setText(msg.getBody());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        holderView.msgTime.setText(format.format(msg.getDate()));
        return convertView;
    }

    @Override
    public int getViewTypeCount() {
        return 2;
    }

    @Override
    public int getItemViewType(int position) {
        Message msg = (Message) this.getItem(position);
        if(msg.getType() == Config.SMS_TYPE_RECIVED){
            return 1;
        }else{
            return 0;
        }

    }

    class HolderView{
        TextView msgContent;
        TextView msgTime;
    }

}
