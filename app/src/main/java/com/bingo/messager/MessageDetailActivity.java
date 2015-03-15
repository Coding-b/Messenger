package com.bingo.messager;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.bingo.messager.adapter.MessageDetailAdapter;
import com.bingo.messager.beans.Message;
import com.bingo.messager.tools.MessageTools;

import java.util.ArrayList;
import java.util.List;


public class MessageDetailActivity extends ActionBarActivity {
    private String address = "";
    private ActionBar actionBar;
    private ListView message_detail_list;
    private MessageDetailAdapter adapter;
    private List<Message> messages = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_detail);

        Intent  intent = getIntent();
        address = intent.getStringExtra("address");

        setUpActionBar();
        init();
        initView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_message_detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setUpActionBar(){
        actionBar = getSupportActionBar();
        String name = MessageTools.getPeopleNameFromPerson(this ,address);
        if(name == null || name.equals("null")){
            actionBar.setTitle(address);
        }else{
            actionBar.setTitle(name);
        }

        actionBar.setBackgroundDrawable(this.getBaseContext().getResources().getDrawable(R.drawable.green_bar));
    }

    public void init(){
        messages = MessageTools.getMessagesFromAddress(this ,address);
    }

    private void initView(){
        message_detail_list = (ListView)findViewById(R.id.message_detail_list);
        adapter = new MessageDetailAdapter(messages ,this);
        message_detail_list.setAdapter(adapter);

    }
}
