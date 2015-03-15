package com.bingo.messager;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.bingo.messager.adapter.MessageListAdapter;
import com.bingo.messager.beans.Message;
import com.bingo.messager.tools.MessageTools;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    private ActionBar actionBar = null;
    private ListView messagelists = null;

    private MessageListAdapter adapter = null;

    private List<Message> messages = new ArrayList<Message>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpActionbar();

        initView();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        //搜索视窗，因为showAsAction="ifRoom"，所以图三中出现了搜索按钮
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        return super.onCreateOptionsMenu(menu);
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

    /**
     * 设置当前用户的actionbar
     */
    private void setUpActionbar(){
        actionBar = getSupportActionBar();
        actionBar.setLogo(null);
        actionBar.setBackgroundDrawable(this.getBaseContext().getResources().getDrawable(R.drawable.green_bar));


    }

    private void initView(){
        messagelists = (ListView)findViewById(R.id.messagelists);

        messages = MessageTools.getAllMessages(this);
        adapter = new MessageListAdapter(messages ,this);
        messagelists.setAdapter(adapter);

        initViewListener();
    }

    private void initViewListener(){
        messagelists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this ,MessageDetailActivity.class);
                intent.putExtra("address" ,messages.get(position).getAddress());
                startActivity(intent);
            }
        });
    }
}
